package fun.fotontv.launcher.request.auth.password;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.request.auth.AuthPasswordInterface;

public class AuthAESPassword implements AuthPasswordInterface {
    @LauncherNetworkAPI
    public final byte[] password;

    public AuthAESPassword(byte[] aesEncryptedPassword) {
        this.password = aesEncryptedPassword;
    }

    @Override
    public boolean check() {
        return true;
    }
}
