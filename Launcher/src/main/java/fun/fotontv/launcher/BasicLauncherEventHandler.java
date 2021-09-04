package fun.fotontv.launcher;

import fun.fotontv.launcher.api.DialogService;
import fun.fotontv.launcher.events.ExtendedTokenRequestEvent;
import fun.fotontv.launcher.events.NotificationEvent;
import fun.fotontv.launcher.events.request.SecurityReportRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.WebSocketEvent;
import fun.fotontv.launcher.request.websockets.ClientWebSocketService;
import fun.fotontv.utils.helper.LogHelper;

public class BasicLauncherEventHandler implements ClientWebSocketService.EventHandler {

    @Override
    public <T extends WebSocketEvent> boolean eventHandle(T event) {
        if (event instanceof SecurityReportRequestEvent event1) {
            if (event1.action == SecurityReportRequestEvent.ReportAction.CRASH) {
                LauncherEngine.exitLauncher(80);
            } else if (event1.action == SecurityReportRequestEvent.ReportAction.TOKEN_EXPIRED) {
                try {
                    Request.restore();
                } catch (Exception e) {
                    LogHelper.error(e);
                }
            }
        } else if (event instanceof ExtendedTokenRequestEvent event1) {
            String token = event1.getExtendedToken();
            if (token != null) {
                Request.addExtendedToken(event1.getExtendedTokenName(), token);
            }
        } else if (event instanceof NotificationEvent n) {
            if (DialogService.isNotificationsAvailable()) {
                DialogService.createNotification(n.icon, n.head, n.message);
            }
        }
        return false;
    }
}
