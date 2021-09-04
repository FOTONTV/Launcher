package fun.fotontv.launcher.client.events.client;

import fun.fotontv.launcher.client.ClientLauncherProcess;
import fun.fotontv.launcher.modules.LauncherModule;

public class ClientProcessBuilderCreateEvent extends LauncherModule.Event {
    public final ClientLauncherProcess processBuilder;

    public ClientProcessBuilderCreateEvent(ClientLauncherProcess processBuilder) {
        this.processBuilder = processBuilder;
    }
}
