package fun.fotontv.launcher.client.events.client;

import fun.fotontv.launcher.client.ClientLauncherProcess;
import fun.fotontv.launcher.modules.LauncherModule;

public class ClientProcessBuilderPreLaunchEvent extends LauncherModule.Event {
    public final ClientLauncherProcess processBuilder;

    public ClientProcessBuilderPreLaunchEvent(ClientLauncherProcess processBuilder) {
        this.processBuilder = processBuilder;
    }
}
