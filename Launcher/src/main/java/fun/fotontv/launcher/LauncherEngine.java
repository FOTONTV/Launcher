package fun.fotontv.launcher;

import fun.fotontv.launcher.client.ClientLauncherCoreModule;
import fun.fotontv.launcher.client.ClientModuleManager;
import fun.fotontv.launcher.client.DirBridge;
import fun.fotontv.launcher.client.events.ClientEngineInitPhase;
import fun.fotontv.launcher.client.events.ClientExitPhase;
import fun.fotontv.launcher.client.events.ClientPreGuiPhase;
import fun.fotontv.launcher.guard.LauncherGuardInterface;
import fun.fotontv.launcher.guard.LauncherGuardManager;
import fun.fotontv.launcher.guard.LauncherNoGuard;
import fun.fotontv.launcher.guard.LauncherWrapperGuard;
import fun.fotontv.launcher.gui.NoRuntimeProvider;
import fun.fotontv.launcher.gui.RuntimeProvider;
import fun.fotontv.launcher.managers.ClientGsonManager;
import fun.fotontv.launcher.managers.ConsoleManager;
import fun.fotontv.launcher.modules.events.PreConfigPhase;
import fun.fotontv.launcher.profiles.optional.actions.OptionalAction;
import fun.fotontv.launcher.profiles.optional.triggers.OptionalTrigger;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.RequestException;
import fun.fotontv.launcher.request.auth.AuthRequest;
import fun.fotontv.launcher.request.auth.GetAvailabilityAuthRequest;
import fun.fotontv.launcher.request.websockets.StdWebSocketService;
import fun.fotontv.launcher.utils.NativeJVMHalt;
import fun.fotontv.utils.helper.EnvHelper;
import fun.fotontv.utils.helper.JVMHelper;
import fun.fotontv.utils.helper.LogHelper;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class LauncherEngine {
    public static ClientModuleManager modulesManager;
    public final boolean clientInstance;
    public static LauncherGuardInterface guard;
    public RuntimeProvider runtimeProvider;
    private final AtomicBoolean started = new AtomicBoolean(false);

    private LauncherEngine(boolean clientInstance) {

        this.clientInstance = clientInstance;
    }

    public static void exitLauncher(int code) {
        modulesManager.invokeEvent(new ClientExitPhase(code));
        try {
            System.exit(code);
        } catch (Throwable e) //Forge Security Manager?
        {
            NativeJVMHalt.haltA(code);
        }
    }

    public static void main(String... args) throws Throwable {
        JVMHelper.checkStackTrace(LauncherEngine.class);
        JVMHelper.verifySystemProperties(Launcher.class, true);
        EnvHelper.checkDangerousParams();
        verifyNoAgent();
        LogHelper.printVersion("Launcher");
        LogHelper.printLicense("Launcher");
        LauncherEngine.modulesManager = new ClientModuleManager();
        LauncherEngine.modulesManager.loadModule(new ClientLauncherCoreModule());
        LauncherConfig.initModules(LauncherEngine.modulesManager);
        LauncherEngine.modulesManager.initModules(null);
        initGson(LauncherEngine.modulesManager);
        ConsoleManager.initConsole();
        LauncherEngine.modulesManager.invokeEvent(new PreConfigPhase());
        Launcher.getConfig();
        long startTime = System.currentTimeMillis();
        try {
            new LauncherEngine(false).start(args);
        } catch (Exception e) {
            LogHelper.error(e);
            return;
        }
        long endTime = System.currentTimeMillis();
        LogHelper.debug("Launcher started in %dms", endTime - startTime);
        LauncherEngine.exitLauncher(0);
    }

    public static void initGson(ClientModuleManager modulesManager) {
        AuthRequest.registerProviders();
        GetAvailabilityAuthRequest.registerProviders();
        OptionalAction.registerProviders();
        OptionalTrigger.registerProviders();
        Launcher.gsonManager = new ClientGsonManager(modulesManager);
        Launcher.gsonManager.initGson();
    }

    public void start(String... args) throws Throwable {
        LauncherEngine.guard = tryGetStdGuard();
        ClientPreGuiPhase event = new ClientPreGuiPhase(null);
        LauncherEngine.modulesManager.invokeEvent(event);
        runtimeProvider = event.runtimeProvider;
        if (runtimeProvider == null) runtimeProvider = new NoRuntimeProvider();
        runtimeProvider.init(clientInstance);
        if (Request.service == null) {
            String address = Launcher.getConfig().address;
            LogHelper.debug("Start async connection to %s", address);
            Request.service = StdWebSocketService.initWebSockets(address, true);
            Request.service.reconnectCallback = () ->
            {
                LogHelper.debug("WebSocket connect closed. Try reconnect");
                try {
                    Request.reconnect();
                } catch (Exception e) {
                    LogHelper.error(e);
                    throw new RequestException("Connection failed", e);
                }
            };
            Request.service.registerEventHandler(new BasicLauncherEventHandler());
        }
        Objects.requireNonNull(args, "args");
        if (started.getAndSet(true))
            throw new IllegalStateException("Launcher has been already started");
        LauncherEngine.modulesManager.invokeEvent(new ClientEngineInitPhase(this));
        runtimeProvider.preLoad();
        LauncherGuardManager.initGuard(clientInstance);
        LogHelper.debug("Dir: %s", DirBridge.dir);
        runtimeProvider.run(args);
    }

    public static void verifyNoAgent() {
        if (JVMHelper.RUNTIME_MXBEAN.getInputArguments().stream().filter(e -> e != null && !e.isEmpty()).anyMatch(e -> e.contains("javaagent")))
            throw new SecurityException("JavaAgent found");
    }

    public static LauncherGuardInterface tryGetStdGuard() {
        return switch (Launcher.getConfig().guardType) {
            case "no" -> new LauncherNoGuard();
            case "wrapper" -> new LauncherWrapperGuard();
            default -> null;
        };
    }
}
