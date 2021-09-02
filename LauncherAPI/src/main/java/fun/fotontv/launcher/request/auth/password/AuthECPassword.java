package fun.fotontv.launcher.request.auth.password;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.request.auth.AuthPasswordInterface;

public class AuthECPassword implements AuthPasswordInterface {
    @LauncherNetworkAPI
    public final byte[] password;

    public AuthECPassword(byte[] password) {
        this.password = password;
    }

    @Override
    public boolean check() {
        return true;
    }
}
