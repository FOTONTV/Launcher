package fun.fotontv.launcher.request.secure;

import fun.fotontv.launcher.events.request.VerifySecureLevelKeyRequestEvent;
import fun.fotontv.launcher.request.Request;

public class VerifySecureLevelKeyRequest extends Request<VerifySecureLevelKeyRequestEvent> {
    public final byte[] publicKey;
    public final byte[] signature;

    public VerifySecureLevelKeyRequest(byte[] publicKey, byte[] signature) {
        this.publicKey = publicKey;
        this.signature = signature;
    }

    @Override
    public String getType() {
        return "verifySecureLevelKey";
    }
}
