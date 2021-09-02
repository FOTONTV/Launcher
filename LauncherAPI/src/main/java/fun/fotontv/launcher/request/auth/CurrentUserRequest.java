package fun.fotontv.launcher.request.auth;

import fun.fotontv.launcher.events.request.CurrentUserRequestEvent;
import fun.fotontv.launcher.request.Request;

public class CurrentUserRequest extends Request<CurrentUserRequestEvent> {
    @Override
    public String getType() {
        return "currentUser";
    }
}
