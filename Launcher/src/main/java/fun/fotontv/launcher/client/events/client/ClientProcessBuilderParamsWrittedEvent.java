package fun.fotontv.launcher.client.events.client;

import fun.fotontv.launcher.client.ClientLauncherProcess;
import fun.fotontv.launcher.modules.LauncherModule;

public class ClientProcessBuilderParamsWrittedEvent extends LauncherModule.Event {
    public final ClientLauncherProcess process;

    public ClientProcessBuilderParamsWrittedEvent(ClientLauncherProcess process) {
        this.process = process;
    }
}
