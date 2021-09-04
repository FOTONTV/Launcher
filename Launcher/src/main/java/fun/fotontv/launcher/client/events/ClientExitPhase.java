package fun.fotontv.launcher.client.events;

import fun.fotontv.launcher.modules.events.ClosePhase;

public class ClientExitPhase extends ClosePhase {
    public final int code;

    public ClientExitPhase(int code) {
        this.code = code;
    }
}
