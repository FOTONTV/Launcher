package fun.fotontv.launcher.events.request;

import fun.fotontv.launcher.events.RequestEvent;

public class RefreshTokenRequestEvent extends RequestEvent {
    public OAuthRequestEvent oauth;

    public RefreshTokenRequestEvent(OAuthRequestEvent oauth) {
        this.oauth = oauth;
    }

    @Override
    public String getType() {
        return "refreshToken";
    }
}
