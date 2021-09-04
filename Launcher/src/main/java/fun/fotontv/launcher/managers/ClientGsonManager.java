package fun.fotontv.launcher.managers;

import com.google.gson.GsonBuilder;
import fun.fotontv.launcher.client.ClientModuleManager;
import fun.fotontv.launcher.client.UserSettings;
import fun.fotontv.launcher.modules.events.PreGsonPhase;
import fun.fotontv.launcher.request.websockets.ClientWebSocketService;
import fun.fotontv.utils.UniversalJsonAdapter;

public class ClientGsonManager extends GsonManager {
    private final ClientModuleManager moduleManager;

    public ClientGsonManager(ClientModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    @Override
    public void registerAdapters(GsonBuilder builder) {
        super.registerAdapters(builder);
        builder.registerTypeAdapter(UserSettings.class, new UniversalJsonAdapter<>(UserSettings.providers));
        ClientWebSocketService.appendTypeAdapters(builder);
        moduleManager.invokeEvent(new PreGsonPhase(builder));
    }
}
