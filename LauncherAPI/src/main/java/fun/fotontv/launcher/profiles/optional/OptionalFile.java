package fun.fotontv.launcher.profiles.optional;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.profiles.optional.actions.OptionalAction;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class OptionalFile {
    @LauncherNetworkAPI
    public List<OptionalAction> actions;
    @LauncherNetworkAPI
    public boolean mark;
    @LauncherNetworkAPI
    public boolean visible = true;
    @LauncherNetworkAPI
    public String name;
    @LauncherNetworkAPI
    public String info;
    @LauncherNetworkAPI
    public List<fun.fotontv.launcher.profiles.optional.triggers.OptionalTrigger> triggersList;
    @LauncherNetworkAPI
    public OptionalDepend[] dependenciesFile;
    @LauncherNetworkAPI
    public OptionalDepend[] conflictFile;
    @LauncherNetworkAPI
    public transient OptionalFile[] dependencies;
    @LauncherNetworkAPI
    public transient OptionalFile[] conflict;
    @LauncherNetworkAPI
    public int subTreeLevel = 1;
    @LauncherNetworkAPI
    public boolean isPreset;
    private volatile transient Collection<BiConsumer<OptionalFile, Boolean>> watchList = null;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionalFile that = (OptionalFile) o;
        return Objects.equals(name, that.name);
    }

    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isMark() {
        return mark;
    }

    public void registerWatcher(BiConsumer<OptionalFile, Boolean> watcher) {
        if (watchList == null) watchList = ConcurrentHashMap.newKeySet();
        watchList.add(watcher);
    }

    public void removeWatcher(BiConsumer<OptionalFile, Boolean> watcher) {
        if (watchList == null) return;
        watchList.remove(watcher);
    }

    public void clearAllWatchers() {
        if (watchList == null) return;
        watchList.clear();
    }

    public void watchEvent(boolean isMark) {
        if (watchList == null) return;
        watchList.forEach((e) -> e.accept(this, isMark));
    }
}
