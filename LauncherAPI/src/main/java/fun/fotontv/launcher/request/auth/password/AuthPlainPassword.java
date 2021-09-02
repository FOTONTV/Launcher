package fun.fotontv.launcher.request.auth.password;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.request.auth.AuthPasswordInterface;

public class AuthPlainPassword implements AuthPasswordInterface {
    @LauncherNetworkAPI
    public final String password;

    public AuthPlainPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean check() {
        return true;
    }
}
