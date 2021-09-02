package fun.fotontv.launcher.request.auth.password;

import fun.fotontv.launcher.request.auth.AuthPasswordInterface;

public class AuthOAuthPassword implements AuthPasswordInterface {
    public final String accessToken;
    public final String refreshToken;
    public final int expire;

    public AuthOAuthPassword(String accessToken, String refreshToken, int expire) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expire = expire;
    }

    public AuthOAuthPassword(String accessToken, int expire) {
        this.accessToken = accessToken;
        this.refreshToken = null;
        this.expire = expire;
    }

    public AuthOAuthPassword(String accessToken) {
        this.accessToken = accessToken;
        this.refreshToken = null;
        this.expire = 0;
    }

    @Override
    public boolean check() {
        return true;
    }
}
