package fun.fotontv.launcher.client.gui.impl;

import fun.fotontv.launcher.client.gui.overlays.AbstractOverlay;
import fun.fotontv.launcher.client.gui.overlays.ProcessingOverlay;
import fun.fotontv.launcher.client.gui.scenes.AbstractScene;
import fun.fotontv.launcher.client.gui.JavaFXApplication;
import fun.fotontv.launcher.client.gui.overlays.*;
import fun.fotontv.launcher.client.gui.scenes.*;
import fun.fotontv.launcher.client.gui.scenes.console.ConsoleScene;
import fun.fotontv.launcher.client.gui.scenes.debug.DebugScene;
import fun.fotontv.launcher.client.gui.scenes.login.LoginScene;
import fun.fotontv.launcher.client.gui.scenes.options.OptionsScene;
import fun.fotontv.launcher.client.gui.scenes.servermenu.ServerMenuScene;
import fun.fotontv.launcher.client.gui.scenes.serverinfo.ServerInfoScene;
import fun.fotontv.launcher.client.gui.scenes.settings.SettingsScene;
import fun.fotontv.launcher.client.gui.scenes.update.UpdateScene;
import fun.fotontv.launcher.client.gui.stage.ConsoleStage;
import fun.fotontv.utils.helper.LogHelper;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashSet;
import java.util.Set;

public class GuiObjectsContainer {
    private final JavaFXApplication application;
    private final Set<AbstractOverlay> overlays = new HashSet<>();
    private final Set<AbstractScene> scenes = new HashSet<>();
    public ProcessingOverlay processingOverlay;
    public UpdateScene updateScene;
    public DebugScene debugScene;

    public ServerMenuScene serverMenuScene;
    public ServerInfoScene serverInfoScene;
    public LoginScene loginScene;
    public OptionsScene optionsScene;
    public SettingsScene settingsScene;
    public ConsoleScene consoleScene;

    public ConsoleStage consoleStage;

    public GuiObjectsContainer(JavaFXApplication application) {
        this.application = application;
    }

    public void init() {
        loginScene = registerScene(LoginScene.class);
        processingOverlay = registerOverlay(ProcessingOverlay.class);

        serverMenuScene = registerScene(ServerMenuScene.class);
        serverInfoScene = registerScene(ServerInfoScene.class);
        optionsScene = registerScene(OptionsScene.class);
        settingsScene = registerScene(SettingsScene.class);
        consoleScene = registerScene(ConsoleScene.class);

        updateScene = registerScene(UpdateScene.class);
        debugScene = registerScene(DebugScene.class);
    }

    public void reload() throws Exception {
        Class<? extends AbstractScene> scene = application.getCurrentScene().getClass();
        ContextHelper.runInFxThreadStatic(() -> {
            application.getMainStage().stage.setScene(null);
            overlays.clear();
            scenes.clear();
            init();
            for(AbstractScene s : scenes) {
                if(s.getClass() == scene) {
                    application.getMainStage().setScene(s);
                }
            }
        }).get();
    }

    public AbstractScene getSceneByName(String name) {
        for(AbstractScene scene : scenes) {
            if(name.equals(scene.getName())) {
                return scene;
            }
        }
        return null;
    }

    public AbstractOverlay getOverlayByName(String name) {
        for(AbstractOverlay overlay : overlays) {
            if(name.equals(overlay.getName())) {
                return overlay;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractOverlay> T registerOverlay(Class<T> clazz) {
        try {
            T instance = (T) MethodHandles.publicLookup().findConstructor(clazz, MethodType.methodType(void.class, JavaFXApplication.class)).invokeWithArguments(application);
            overlays.add(instance);
            return instance;
        } catch (Throwable e) {
            LogHelper.error(e);
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractScene> T registerScene(Class<T> clazz) {
        try {
            T instance = (T) MethodHandles.publicLookup().findConstructor(clazz, MethodType.methodType(void.class, JavaFXApplication.class)).invokeWithArguments(application);
            scenes.add(instance);
            return instance;
        } catch (Throwable e) {
            LogHelper.error(e);
            throw new RuntimeException(e);
        }
    }
}
