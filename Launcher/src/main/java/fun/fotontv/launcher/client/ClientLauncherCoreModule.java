package fun.fotontv.launcher.client;

import fun.fotontv.launcher.modules.LauncherInitContext;
import fun.fotontv.launcher.modules.LauncherModule;
import fun.fotontv.launcher.modules.LauncherModuleInfo;
import fun.fotontv.utils.Version;

public class ClientLauncherCoreModule extends LauncherModule {
    public ClientLauncherCoreModule() {
        super(new LauncherModuleInfo("ClientLauncherCore", Version.getVersion()));
    }

    @Override
    public void init(LauncherInitContext initContext) {

    }
}
