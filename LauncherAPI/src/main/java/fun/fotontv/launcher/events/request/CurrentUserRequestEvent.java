package fun.fotontv.launcher.events.request;

import fun.fotontv.launcher.ClientPermissions;
import fun.fotontv.launcher.events.RequestEvent;
import fun.fotontv.launcher.profiles.PlayerProfile;

public class CurrentUserRequestEvent extends RequestEvent {
    public final UserInfo userInfo;

    public CurrentUserRequestEvent(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String getType() {
        return "currentUser";
    }

    public static class UserInfo {
        public ClientPermissions permissions;
        public String accessToken;
        public PlayerProfile playerProfile;
    }
}
