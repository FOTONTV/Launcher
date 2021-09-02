package fun.fotontv.launcher.events.request;

import fun.fotontv.launcher.events.RequestEvent;

public class PingServerReportRequestEvent extends RequestEvent {
    @Override
    public String getType() {
        return "pingServerReport";
    }
}
