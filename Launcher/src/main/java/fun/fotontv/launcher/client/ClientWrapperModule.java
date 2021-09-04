package fun.fotontv.launcher.client;

import fun.fotontv.launcher.ClientLauncherWrapper;

public interface ClientWrapperModule {
    void wrapperPhase(ClientLauncherWrapper.ClientLauncherWrapperContext context);
}
