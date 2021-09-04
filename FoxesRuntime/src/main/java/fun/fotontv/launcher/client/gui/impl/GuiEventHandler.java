package fun.fotontv.launcher.client.gui.impl;

import fun.fotontv.launcher.client.gui.JavaFXApplication;
import fun.fotontv.launcher.client.gui.scenes.login.LoginScene;
import fun.fotontv.launcher.events.RequestEvent;
import fun.fotontv.launcher.events.request.AuthRequestEvent;
import fun.fotontv.launcher.request.WebSocketEvent;
import fun.fotontv.launcher.request.websockets.ClientWebSocketService;
import fun.fotontv.utils.helper.LogHelper;

public class GuiEventHandler implements ClientWebSocketService.EventHandler {
    private final JavaFXApplication application;

    public GuiEventHandler(JavaFXApplication application) {
        this.application = application;
    }

    @Override
    public <T extends WebSocketEvent> boolean eventHandle(T event) {
        LogHelper.dev("Processing event %s", event.getType());
        if (event instanceof RequestEvent) {
            if (!((RequestEvent) event).requestUUID.equals(RequestEvent.eventUUID))
                return false;
        }
        try {
            if (event instanceof AuthRequestEvent) {
                boolean isNextScene = application.getCurrentScene() instanceof LoginScene;
                ((LoginScene) application.getCurrentScene()).isLoginStarted = true;
                LogHelper.dev("Receive auth event. Send next scene %s", isNextScene ? "true" : "false");
                application.stateService.setAuthResult(null, (AuthRequestEvent) event);
                if (isNextScene && ((LoginScene) application.getCurrentScene()).isLoginStarted)
                    ((LoginScene) application.getCurrentScene()).onGetProfiles();
            }
        } catch (Throwable e) {
            LogHelper.error(e);
        }
        return false;
    }
}
