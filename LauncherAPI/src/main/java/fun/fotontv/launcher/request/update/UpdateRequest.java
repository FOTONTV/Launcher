package fun.fotontv.launcher.request.update;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.request.UpdateRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;

public final class UpdateRequest extends Request<UpdateRequestEvent> implements WebSocketRequest {

    // Instance
    @LauncherNetworkAPI
    public final String dirName;

    public UpdateRequest(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public String getType() {
        return "update";
    }
}
