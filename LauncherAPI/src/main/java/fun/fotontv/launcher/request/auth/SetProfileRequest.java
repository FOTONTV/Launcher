package fun.fotontv.launcher.request.auth;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.request.SetProfileRequestEvent;
import fun.fotontv.launcher.profiles.ClientProfile;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;

public class SetProfileRequest extends Request<SetProfileRequestEvent> implements WebSocketRequest {
    @LauncherNetworkAPI
    public final String client;

    public SetProfileRequest(ClientProfile profile) {
        this.client = profile.getTitle();
    }

    @Override
    public String getType() {
        return "setProfile";
    }
}
