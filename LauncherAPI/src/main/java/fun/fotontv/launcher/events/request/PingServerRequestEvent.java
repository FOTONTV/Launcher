package fun.fotontv.launcher.events.request;

import fun.fotontv.launcher.events.RequestEvent;
import fun.fotontv.launcher.request.management.PingServerReportRequest;

import java.util.Map;

public class PingServerRequestEvent extends RequestEvent {
    public Map<String, PingServerReportRequest.PingServerReport> serverMap;

    public PingServerRequestEvent() {
    }

    public PingServerRequestEvent(Map<String, PingServerReportRequest.PingServerReport> serverMap) {
        this.serverMap = serverMap;
    }

    @Override
    public String getType() {
        return "pingServer";
    }
}
