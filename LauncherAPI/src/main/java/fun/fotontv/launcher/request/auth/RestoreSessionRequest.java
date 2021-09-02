package fun.fotontv.launcher.request.auth;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.request.RestoreSessionRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;

import java.util.UUID;

public class RestoreSessionRequest extends Request<RestoreSessionRequestEvent> implements WebSocketRequest {
    @LauncherNetworkAPI
    public final UUID session;
    public boolean needUserInfo;

    public RestoreSessionRequest(UUID session) {
        this.session = session;
    }

    public RestoreSessionRequest(UUID session, boolean needUserInfo) {
        this.session = session;
        this.needUserInfo = needUserInfo;
    }

    @Override
    public String getType() {
        return "restoreSession";
    }
}
