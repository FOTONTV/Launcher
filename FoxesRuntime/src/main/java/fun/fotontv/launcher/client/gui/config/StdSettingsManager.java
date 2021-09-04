package fun.fotontv.launcher.client.gui.config;

import fun.fotontv.launcher.NewLauncherSettings;
import fun.fotontv.launcher.client.JavaRuntimeModule;
import fun.fotontv.launcher.managers.SettingsManager;

public class StdSettingsManager extends SettingsManager {

    @Override
    public NewLauncherSettings getDefaultConfig() {
        NewLauncherSettings newLauncherSettings = new NewLauncherSettings();
        newLauncherSettings.userSettings.put(JavaRuntimeModule.RUNTIME_NAME, RuntimeSettings.getDefault());
        return newLauncherSettings;
    }
}
