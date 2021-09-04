package fun.fotontv.launcher.client.gui.commands.runtime;

import fun.fotontv.launcher.client.gui.JavaFXApplication;
import fun.fotontv.utils.command.Command;

public class ReloadCommand extends Command {
    private final JavaFXApplication application;

    public ReloadCommand(JavaFXApplication application) {
        this.application = application;
    }

    @Override
    public String getArgsDescription() {
        return "[]";
    }

    @Override
    public String getUsageDescription() {
        return "reload ui";
    }

    @Override
    public void invoke(String... args) throws Exception {
        application.gui.reload();
    }
}
