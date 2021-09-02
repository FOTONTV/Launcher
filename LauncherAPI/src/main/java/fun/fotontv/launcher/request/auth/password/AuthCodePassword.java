package fun.fotontv.launcher.request.auth.password;

import fun.fotontv.launcher.request.auth.AuthPasswordInterface;

public class AuthCodePassword implements AuthPasswordInterface {
    public final String code;

    public AuthCodePassword(String code) {
        this.code = code;
    }

    @Override
    public boolean check() {
        return true;
    }
}
