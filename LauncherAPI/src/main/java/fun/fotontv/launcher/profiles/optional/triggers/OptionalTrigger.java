package fun.fotontv.launcher.profiles.optional.triggers;

import fun.fotontv.launcher.profiles.optional.OptionalFile;
import fun.fotontv.utils.ProviderMap;

public abstract class OptionalTrigger {
    public static ProviderMap<OptionalTrigger> providers = new ProviderMap<>("OptionalTriggers");
    private static boolean isRegisteredProviders = false;
    public boolean required;
    public boolean inverted;

    public static void registerProviders() {
        if (!isRegisteredProviders) {
            providers.register("java", JavaTrigger.class);
            providers.register("os", OSTrigger.class);
            isRegisteredProviders = true;
        }
    }

    protected abstract boolean isTriggered(OptionalFile optional, OptionalTriggerContext context);

    public boolean check(OptionalFile optional, OptionalTriggerContext context) {
        boolean result = isTriggered(optional, context);
        if (inverted) result = !result;
        return result;
    }
}
