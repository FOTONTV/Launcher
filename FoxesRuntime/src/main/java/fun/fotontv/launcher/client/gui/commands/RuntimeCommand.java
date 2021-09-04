package fun.fotontv.launcher.client.gui.commands;

import fun.fotontv.launcher.client.gui.JavaFXApplication;
import fun.fotontv.launcher.client.gui.commands.runtime.DialogCommand;
import fun.fotontv.launcher.client.gui.commands.runtime.NotifyCommand;
import fun.fotontv.launcher.client.gui.commands.runtime.ReloadCommand;
import fun.fotontv.launcher.client.gui.commands.runtime.WarpCommand;
import fun.fotontv.utils.command.Command;

public class RuntimeCommand extends Command {
    private final JavaFXApplication application;

    public RuntimeCommand(JavaFXApplication application) {
        this.application = application;
        this.childCommands.put("dialog", new DialogCommand(application.messageManager));
        this.childCommands.put("warp", new WarpCommand(application));
        this.childCommands.put("reload", new ReloadCommand(application));
        this.childCommands.put("notify", new NotifyCommand(application.messageManager));
    }

    @Override
    public String getArgsDescription() {
        return null;
    }

    @Override
    public String getUsageDescription() {
        return null;
    }

    @Override
    public void invoke(String... args) throws Exception {
        invokeSubcommands(args);
    }
}
