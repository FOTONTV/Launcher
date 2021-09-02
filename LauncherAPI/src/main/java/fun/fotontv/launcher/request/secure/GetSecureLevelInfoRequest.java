package fun.fotontv.launcher.request.secure;

import fun.fotontv.launcher.events.request.GetSecureLevelInfoRequestEvent;
import fun.fotontv.launcher.request.Request;

public class GetSecureLevelInfoRequest extends Request<GetSecureLevelInfoRequestEvent> {
    @Override
    public String getType() {
        return "getSecureLevelInfo";
    }
}
