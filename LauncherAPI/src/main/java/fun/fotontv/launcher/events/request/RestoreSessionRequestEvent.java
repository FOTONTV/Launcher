package fun.fotontv.launcher.events.request;

import fun.fotontv.launcher.events.RequestEvent;

public class RestoreSessionRequestEvent extends RequestEvent {
    public CurrentUserRequestEvent.UserInfo userInfo;

    public RestoreSessionRequestEvent() {
    }

    public RestoreSessionRequestEvent(CurrentUserRequestEvent.UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String getType() {
        return "restoreSession";
    }
}
