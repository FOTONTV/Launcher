package fun.fotontv.launcher.request.auth.password;

import fun.fotontv.launcher.request.auth.AuthPasswordInterface;

public class Auth2FAPassword implements AuthPasswordInterface {
    public AuthPasswordInterface firstPassword;
    public AuthPasswordInterface secondPassword;

    @Override
    public boolean check() {
        return firstPassword != null && firstPassword.check() && secondPassword != null && secondPassword.check();
    }

    @Override
    public boolean isAllowSave() {
        return firstPassword.isAllowSave() && secondPassword.isAllowSave();
    }
}
