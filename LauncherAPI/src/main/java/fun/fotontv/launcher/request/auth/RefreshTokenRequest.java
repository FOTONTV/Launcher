package fun.fotontv.launcher.request.auth;

import fun.fotontv.launcher.events.request.RefreshTokenRequestEvent;
import fun.fotontv.launcher.request.Request;

public class RefreshTokenRequest extends Request<RefreshTokenRequestEvent> {
    public String authId;
    public String refreshToken;

    public RefreshTokenRequest(String authId, String refreshToken) {
        this.authId = authId;
        this.refreshToken = refreshToken;
    }

    @Override
    public String getType() {
        return "refreshToken";
    }
}
