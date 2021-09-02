package fun.fotontv.launcher.request.auth.password;

import fun.fotontv.launcher.request.auth.AuthPasswordInterface;

public class AuthTOTPPassword implements AuthPasswordInterface {
    public String totp;

    @Override
    public boolean check() {
        return true;
    }
}
