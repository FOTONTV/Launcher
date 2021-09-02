package fun.fotontv.launcher.request.update;

import fun.fotontv.launcher.events.request.UpdateListRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;

public final class UpdateListRequest extends Request<UpdateListRequestEvent> implements WebSocketRequest {

    @Override
    public String getType() {
        return "updateList";
    }
}
