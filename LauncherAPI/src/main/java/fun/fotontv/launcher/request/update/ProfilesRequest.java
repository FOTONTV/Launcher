package fun.fotontv.launcher.request.update;

import fun.fotontv.launcher.events.request.ProfilesRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;

public final class ProfilesRequest extends Request<ProfilesRequestEvent> implements WebSocketRequest {

    @Override
    public String getType() {
        return "profiles";
    }
}
