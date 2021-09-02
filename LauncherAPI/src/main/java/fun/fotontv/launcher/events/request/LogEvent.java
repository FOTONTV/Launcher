package fun.fotontv.launcher.events.request;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.request.WebSocketEvent;

public class LogEvent implements WebSocketEvent {
    @LauncherNetworkAPI
    public final String string;

    public LogEvent(String string) {
        this.string = string;
    }

    @Override
    public String getType() {
        return "log";
    }
}
