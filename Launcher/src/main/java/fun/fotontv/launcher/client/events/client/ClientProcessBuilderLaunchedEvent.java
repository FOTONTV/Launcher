package fun.fotontv.launcher.client.events.client;

import fun.fotontv.launcher.client.ClientLauncherProcess;
import fun.fotontv.launcher.modules.LauncherModule;

public class ClientProcessBuilderLaunchedEvent extends LauncherModule.Event {
    public final ClientLauncherProcess processBuilder;

    public ClientProcessBuilderLaunchedEvent(ClientLauncherProcess processBuilder) {
        this.processBuilder = processBuilder;
    }
}
