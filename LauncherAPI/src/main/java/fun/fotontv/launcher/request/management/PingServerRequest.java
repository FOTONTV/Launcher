package fun.fotontv.launcher.request.management;

import fun.fotontv.launcher.events.request.PingServerRequestEvent;
import fun.fotontv.launcher.request.Request;

import java.util.ArrayList;
import java.util.List;

public class PingServerRequest extends Request<PingServerRequestEvent> {
    public List<String> serverNames; //May be null

    public PingServerRequest() {
    }

    public PingServerRequest(List<String> serverNames) {
        this.serverNames = serverNames;
    }

    public PingServerRequest(String serverName) {
        this.serverNames = new ArrayList<>();
        serverNames.add(serverName);
    }

    @Override
    public String getType() {
        return "pingServer";
    }
}
