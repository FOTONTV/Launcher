package fun.fotontv.launcher.profiles.optional.triggers;

import fun.fotontv.launcher.ClientPermissions;
import fun.fotontv.launcher.profiles.ClientProfile;
import fun.fotontv.launcher.profiles.PlayerProfile;
import fun.fotontv.utils.helper.JavaHelper;

public interface OptionalTriggerContext {
    ClientProfile getProfile();

    String getUsername();

    JavaHelper.JavaVersion getJavaVersion();

    default ClientPermissions getPermissions() {
        return ClientPermissions.DEFAULT;
    }

    default PlayerProfile getPlayerProfile() {
        return null;
    }
}
