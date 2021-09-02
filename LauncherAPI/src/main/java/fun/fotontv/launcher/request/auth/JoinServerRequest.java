package fun.fotontv.launcher.request.auth;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.request.JoinServerRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;
import fun.fotontv.utils.helper.VerifyHelper;

public final class JoinServerRequest extends Request<JoinServerRequestEvent> implements WebSocketRequest {

    // Instance
    @LauncherNetworkAPI
    public final String username;
    @LauncherNetworkAPI
    public final String accessToken;
    @LauncherNetworkAPI
    public final String serverID;


    public JoinServerRequest(String username, String accessToken, String serverID) {
        this.username = VerifyHelper.verifyUsername(username);
        this.accessToken = accessToken;
        this.serverID = VerifyHelper.verifyServerID(serverID);
    }

    @Override
    public String getType() {
        return "joinServer";
    }
}
