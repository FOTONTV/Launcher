package fun.fotontv.launcher.events.request;

import fun.fotontv.launcher.events.RequestEvent;

public class GetSecureLevelInfoRequestEvent extends RequestEvent {
    public final byte[] verifySecureKey;
    public boolean enabled;

    public GetSecureLevelInfoRequestEvent(byte[] verifySecureKey) {
        this.verifySecureKey = verifySecureKey;
    }

    @Override
    public String getType() {
        return "getSecureLevelInfo";
    }
}
