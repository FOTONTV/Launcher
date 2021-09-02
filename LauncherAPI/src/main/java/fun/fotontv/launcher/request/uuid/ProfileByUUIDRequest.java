package fun.fotontv.launcher.request.uuid;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.request.ProfileByUUIDRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;

import java.util.Objects;
import java.util.UUID;

public final class ProfileByUUIDRequest extends Request<ProfileByUUIDRequestEvent> implements WebSocketRequest {
    @LauncherNetworkAPI
    public final UUID uuid;


    public ProfileByUUIDRequest(UUID uuid) {
        this.uuid = Objects.requireNonNull(uuid, "uuid");
    }

    @Override
    public String getType() {
        return "profileByUUID";
    }
}
