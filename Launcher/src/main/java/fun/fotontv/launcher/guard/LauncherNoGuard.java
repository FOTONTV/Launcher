package fun.fotontv.launcher.guard;

import fun.fotontv.launcher.client.ClientLauncherProcess;

public class LauncherNoGuard implements LauncherGuardInterface {
    @Override
    public String getName() {
        return "noGuard";
    }

    @Override
    public void applyGuardParams(ClientLauncherProcess process) {
        //IGNORED
    }
}
