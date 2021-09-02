package fun.fotontv.launcher.request.auth;

import fun.fotontv.launcher.events.request.GetAvailabilityAuthRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.auth.details.AuthLoginOnlyDetails;
import fun.fotontv.launcher.request.auth.details.AuthPasswordDetails;
import fun.fotontv.launcher.request.auth.details.AuthTotpDetails;
import fun.fotontv.launcher.request.auth.details.AuthWebViewDetails;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;
import fun.fotontv.utils.ProviderMap;

public class GetAvailabilityAuthRequest extends Request<GetAvailabilityAuthRequestEvent> implements WebSocketRequest {

    public static final ProviderMap<GetAvailabilityAuthRequestEvent.AuthAvailabilityDetails> providers = new ProviderMap<>();
    private static boolean registeredProviders = false;

    public static void registerProviders() {
        if (!registeredProviders) {
            providers.register("password", AuthPasswordDetails.class);
            providers.register("webview", AuthWebViewDetails.class);
            providers.register("totp", AuthTotpDetails.class);
            providers.register("loginonly", AuthLoginOnlyDetails.class);
            registeredProviders = true;
        }
    }

    @Override
    public String getType() {
        return "getAvailabilityAuth";
    }
}
