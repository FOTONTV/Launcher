package fun.fotontv.launcher.request.management;

import fun.fotontv.launcher.events.request.FeaturesRequestEvent;
import fun.fotontv.launcher.request.Request;

public class FeaturesRequest extends Request<FeaturesRequestEvent> {
    @Override
    public String getType() {
        return "features";
    }
}
