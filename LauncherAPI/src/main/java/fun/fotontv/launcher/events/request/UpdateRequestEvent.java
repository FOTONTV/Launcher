package fun.fotontv.launcher.events.request;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.RequestEvent;
import fun.fotontv.launcher.hasher.HashedDir;

public class UpdateRequestEvent extends RequestEvent {
    @LauncherNetworkAPI
    public final HashedDir hdir;
    @LauncherNetworkAPI
    public final boolean zip;
    @LauncherNetworkAPI
    public String url;
    @LauncherNetworkAPI
    public boolean fullDownload;

    public UpdateRequestEvent(HashedDir hdir) {
        this.hdir = hdir;
        this.zip = false;
    }

    public UpdateRequestEvent(HashedDir hdir, String url) {
        this.hdir = hdir;
        this.url = url;
        this.zip = false;
    }

    public UpdateRequestEvent(HashedDir hdir, String url, boolean zip) {
        this.hdir = hdir;
        this.url = url;
        this.zip = zip;
    }

    @Override
    public String getType() {
        return "update";
    }
}
