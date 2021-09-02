package fun.fotontv.launcher.request.auth.details;

import fun.fotontv.launcher.events.request.GetAvailabilityAuthRequestEvent;

public class AuthPasswordDetails implements GetAvailabilityAuthRequestEvent.AuthAvailabilityDetails {
    @Override
    public String getType() {
        return "password";
    }


}
