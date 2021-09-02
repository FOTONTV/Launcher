package fun.fotontv.launcher.events.request;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.RequestEvent;
import fun.fotontv.launcher.profiles.ClientProfile;

import java.util.UUID;

public class SetProfileRequestEvent extends RequestEvent {
    @SuppressWarnings("unused")
    private static final UUID uuid = UUID.fromString("08c0de9e-4364-4152-9066-8354a3a48541");
    @LauncherNetworkAPI
    public final ClientProfile newProfile;

    public SetProfileRequestEvent(ClientProfile newProfile) {
        this.newProfile = newProfile;
    }

    @Override
    public String getType() {
        return "setProfile";
    }
}
