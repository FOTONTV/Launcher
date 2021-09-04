package fun.fotontv.launcher.guard;

import fun.fotontv.launcher.client.ClientLauncherProcess;

public interface LauncherGuardInterface {
    String getName();

    void applyGuardParams(ClientLauncherProcess process);
}
