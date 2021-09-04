package fun.fotontv.launcher.client.events;

import fun.fotontv.launcher.modules.LauncherModule;
import fun.fotontv.utils.command.CommandHandler;

public class ClientUnlockConsoleEvent extends LauncherModule.Event {
    public final CommandHandler handler;

    public ClientUnlockConsoleEvent(CommandHandler handler) {
        this.handler = handler;
    }
}
