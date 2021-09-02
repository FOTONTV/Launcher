package fun.fotontv.launcher.request.auth.password;

import fun.fotontv.launcher.request.auth.AuthPasswordInterface;

public class AuthRSAPassword implements AuthPasswordInterface {
    public final byte[] password;

    public AuthRSAPassword(byte[] rsaEncryptedPassword) {
        this.password = rsaEncryptedPassword;
    }

    @Override
    public boolean check() {
        return true;
    }
}
