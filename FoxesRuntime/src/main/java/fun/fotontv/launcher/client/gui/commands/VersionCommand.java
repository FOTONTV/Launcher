package fun.fotontv.launcher.client.gui.commands;

import fun.fotontv.launcher.client.gui.scenes.console.ConsoleScene;
import fun.fotontv.utils.command.Command;
import fun.fotontv.utils.helper.LogHelper;

public class VersionCommand extends Command {
    @Override
    public String getArgsDescription() {
        return "print version information";
    }

    @Override
    public String getUsageDescription() {
        return "[]";
    }

    @Override
    public void invoke(String... args) {
        LogHelper.info(ConsoleScene.getLauncherInfo());
        LogHelper.info("JDK Path: %s", System.getProperty("java.home", "UNKNOWN"));
    }
}
