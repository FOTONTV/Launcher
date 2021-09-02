package fun.fotontv.launcher.request.auth.password;

import fun.fotontv.launcher.request.auth.AuthPasswordInterface;

public class AuthSignaturePassword implements AuthPasswordInterface {
    public byte[] signature;
    public byte[] publicKey;
    public byte[] salt;

    public AuthSignaturePassword(byte[] signature, byte[] publicKey, byte[] salt) {
        this.signature = signature;
        this.publicKey = publicKey;
        this.salt = salt;
    }

    @Override
    public boolean check() {
        return true;
    }
}
