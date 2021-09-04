package fun.fotontv.launcher.client;

import fun.fotontv.launcher.ClientLauncherWrapper;
import fun.fotontv.launcher.Launcher;
import fun.fotontv.launcher.LauncherTrustManager;
import fun.fotontv.launcher.modules.LauncherModule;
import fun.fotontv.launcher.modules.impl.SimpleModuleManager;

import java.nio.file.Path;

public final class ClientModuleManager extends SimpleModuleManager {
    public ClientModuleManager() {
        super(null, null, Launcher.getConfig().trustManager);
    }

    @Override
    public void autoload() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void autoload(Path dir) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LauncherModule loadModule(LauncherModule module) {
        return super.loadModule(module);
    }

    @Override
    public boolean verifyClassCheckResult(LauncherTrustManager.CheckClassResult result) {
        return result.type == LauncherTrustManager.CheckClassResultType.SUCCESS;
    }

    public void callWrapper(ClientLauncherWrapper.ClientLauncherWrapperContext context) {
        for (LauncherModule module : modules) {
            if (module instanceof ClientWrapperModule) {
                ((ClientWrapperModule) module).wrapperPhase(context);
            }
        }
    }
}
