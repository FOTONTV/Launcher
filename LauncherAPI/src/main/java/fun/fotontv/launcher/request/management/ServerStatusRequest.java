package fun.fotontv.launcher.request.management;

import fun.fotontv.launcher.events.request.ServerStatusRequestEvent;
import fun.fotontv.launcher.request.Request;

public class ServerStatusRequest extends Request<ServerStatusRequestEvent> {
    @Override
    public String getType() {
        return "serverStatus";
    }
}
