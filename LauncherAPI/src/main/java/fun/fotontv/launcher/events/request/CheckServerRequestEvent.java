package fun.fotontv.launcher.events.request;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.RequestEvent;
import fun.fotontv.launcher.profiles.PlayerProfile;

import java.util.UUID;

public class CheckServerRequestEvent extends RequestEvent {
    @SuppressWarnings("unused")
    private static final UUID _uuid = UUID.fromString("8801d07c-51ba-4059-b61d-fe1f1510b28a");
    @LauncherNetworkAPI
    public UUID uuid;
    @LauncherNetworkAPI
    public PlayerProfile playerProfile;

    public CheckServerRequestEvent(PlayerProfile playerProfile) {
        this.playerProfile = playerProfile;
    }

    public CheckServerRequestEvent() {
    }

    @Override
    public String getType() {
        return "checkServer";
    }
}
