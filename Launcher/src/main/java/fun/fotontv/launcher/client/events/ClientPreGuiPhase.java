package fun.fotontv.launcher.client.events;

import fun.fotontv.launcher.gui.RuntimeProvider;
import fun.fotontv.launcher.modules.LauncherModule;

public class ClientPreGuiPhase extends LauncherModule.Event {
    public RuntimeProvider runtimeProvider;

    public ClientPreGuiPhase(RuntimeProvider runtimeProvider) {
        this.runtimeProvider = runtimeProvider;
    }
}
