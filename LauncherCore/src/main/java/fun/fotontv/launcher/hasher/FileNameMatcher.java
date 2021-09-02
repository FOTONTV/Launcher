package fun.fotontv.launcher.hasher;

import java.util.Collection;

public final class FileNameMatcher {
    private static final String[] NO_ENTRIES = new String[0];
    // Instance
    private final String[] update;
    private final String[] verify;
    private final String[] exclusions;

    public FileNameMatcher(String[] update, String[] verify, String[] exclusions) {
        this.update = update;
        this.verify = verify;
        this.exclusions = exclusions;
    }

    private static boolean anyMatch(String[] entries, Collection<String> path) {
        String jpath = String.join("/", path);
        for (String e : entries) {
            if (jpath.startsWith(e)) return true;
        }
        return false;
    }

    public boolean shouldUpdate(Collection<String> path) {
        return (anyMatch(update, path) || anyMatch(verify, path)) && !anyMatch(exclusions, path);
    }


    public boolean shouldVerify(Collection<String> path) {
        return anyMatch(verify, path) && !anyMatch(exclusions, path);
    }

    public FileNameMatcher verifyOnly() {
        return new FileNameMatcher(NO_ENTRIES, verify, exclusions);
    }
}
