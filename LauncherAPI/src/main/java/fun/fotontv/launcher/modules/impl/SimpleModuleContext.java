package fun.fotontv.launcher.modules.impl;

import fun.fotontv.launcher.modules.LauncherModulesContext;
import fun.fotontv.launcher.modules.LauncherModulesManager;
import fun.fotontv.launcher.modules.ModulesConfigManager;

public class SimpleModuleContext implements LauncherModulesContext {
    public final LauncherModulesManager modulesManager;
    public final ModulesConfigManager configManager;

    public SimpleModuleContext(LauncherModulesManager modulesManager, ModulesConfigManager configManager) {
        this.modulesManager = modulesManager;
        this.configManager = configManager;
    }

    @Override
    public LauncherModulesManager getModulesManager() {
        return modulesManager;
    }

    @Override
    public ModulesConfigManager getModulesConfigManager() {
        return configManager;
    }
}
