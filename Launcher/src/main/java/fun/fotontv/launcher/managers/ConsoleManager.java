package fun.fotontv.launcher.managers;

import fun.fotontv.launcher.Launcher;
import fun.fotontv.launcher.LauncherEngine;
import fun.fotontv.launcher.client.events.ClientUnlockConsoleEvent;
import fun.fotontv.launcher.console.UnlockCommand;
import fun.fotontv.launcher.console.test.PrintHardwareInfoCommand;
import fun.fotontv.utils.command.CommandHandler;
import fun.fotontv.utils.command.JLineCommandHandler;
import fun.fotontv.utils.command.StdCommandHandler;
import fun.fotontv.utils.command.basic.ClearCommand;
import fun.fotontv.utils.command.basic.DebugCommand;
import fun.fotontv.utils.command.basic.GCCommand;
import fun.fotontv.utils.command.basic.HelpCommand;
import fun.fotontv.utils.helper.CommonHelper;
import fun.fotontv.utils.helper.LogHelper;

import java.io.IOException;

public class ConsoleManager {
    public static CommandHandler handler;
    public static Thread thread;
    public static boolean isConsoleUnlock = false;

    public static void initConsole() throws IOException {
        CommandHandler localCommandHandler;
        try {
            Class.forName("org.jline.terminal.Terminal");

            // JLine2 available
            localCommandHandler = new JLineCommandHandler();
            LogHelper.info("JLine2 terminal enabled");
        } catch (ClassNotFoundException ignored) {
            localCommandHandler = new StdCommandHandler(true);
            LogHelper.warning("JLine2 isn't in classpath, using std");
        }
        handler = localCommandHandler;
        registerCommands();
        thread = CommonHelper.newThread("Launcher Console", true, handler);
        thread.start();
    }

    public static void registerCommands() {
        handler.registerCommand("help", new HelpCommand(handler));
        handler.registerCommand("gc", new GCCommand());
        handler.registerCommand("clear", new ClearCommand(handler));
        handler.registerCommand("unlock", new UnlockCommand());
        handler.registerCommand("printhardware", new PrintHardwareInfoCommand());
    }

    public static boolean checkUnlockKey(String key) {
        return key.equals(Launcher.getConfig().oemUnlockKey);
    }

    public static boolean unlock() {
        if (isConsoleUnlock) return true;
        ClientUnlockConsoleEvent event = new ClientUnlockConsoleEvent(handler);
        LauncherEngine.modulesManager.invokeEvent(event);
        if (event.isCancel()) return false;
        handler.registerCommand("debug", new DebugCommand());
        handler.unregisterCommand("unlock");
        isConsoleUnlock = true;
        return true;
    }
}
