package fun.fotontv.launcher.client.events;

import fun.fotontv.launcher.LauncherEngine;
import fun.fotontv.launcher.modules.events.InitPhase;

public class ClientEngineInitPhase extends InitPhase {
    public final LauncherEngine engine;

    public ClientEngineInitPhase(LauncherEngine engine) {
        this.engine = engine;
    }
}
