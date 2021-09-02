package fun.fotontv.launcher.request.auth;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.request.CheckServerRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;
import fun.fotontv.utils.helper.VerifyHelper;

public final class CheckServerRequest extends Request<CheckServerRequestEvent> implements WebSocketRequest {
    @LauncherNetworkAPI
    public final String username;
    @LauncherNetworkAPI
    public final String serverID;


    public CheckServerRequest(String username, String serverID) {
        this.username = VerifyHelper.verifyUsername(username);
        this.serverID = VerifyHelper.verifyServerID(serverID);
    }

    @Override
    public String getType() {
        return "checkServer";
    }
}
