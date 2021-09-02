package fun.fotontv.launcher.events.request;

public class OAuthRequestEvent {
    public final String accessToken;
    public final String refreshToken;
    public final long expire;

    public OAuthRequestEvent(String accessToken, String refreshToken, long expire) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expire = expire;
    }
}
