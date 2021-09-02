package fun.fotontv.launcher.request.auth.details;

import fun.fotontv.launcher.events.request.GetAvailabilityAuthRequestEvent;

public class AuthLoginOnlyDetails implements GetAvailabilityAuthRequestEvent.AuthAvailabilityDetails {
    @Override
    public String getType() {
        return "loginonly";
    }
}
