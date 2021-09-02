package fun.fotontv.launcher.modules.events;

import com.google.gson.GsonBuilder;
import fun.fotontv.launcher.modules.LauncherModule;

public class PreGsonPhase extends LauncherModule.Event {
    public final GsonBuilder gsonBuilder;

    public PreGsonPhase(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
    }
}
