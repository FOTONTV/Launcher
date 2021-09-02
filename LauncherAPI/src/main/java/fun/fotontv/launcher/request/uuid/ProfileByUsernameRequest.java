package fun.fotontv.launcher.request.uuid;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.request.ProfileByUsernameRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;
import fun.fotontv.utils.helper.VerifyHelper;

public final class ProfileByUsernameRequest extends Request<ProfileByUsernameRequestEvent> implements WebSocketRequest {
    @LauncherNetworkAPI
    public final String username;


    public ProfileByUsernameRequest(String username) {
        this.username = VerifyHelper.verifyUsername(username);
    }

    @Override
    public String getType() {
        return "profileByUsername";
    }
}
