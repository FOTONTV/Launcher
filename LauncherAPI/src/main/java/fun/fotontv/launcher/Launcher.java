package fun.fotontv.launcher;

import fun.fotontv.launcher.managers.GsonManager;
import fun.fotontv.launcher.profiles.ClientProfile;
import fun.fotontv.launcher.serialize.HInput;
import fun.fotontv.utils.helper.IOHelper;
import fun.fotontv.utils.helper.LogHelper;
import fun.fotontv.utils.helper.SecurityHelper;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

public final class Launcher {
    // Authlib constants

    public static final String SKIN_URL_PROPERTY = "skinURL";

    public static final String SKIN_DIGEST_PROPERTY = "skinDigest";

    public static final String SKIN_METADATA_PROPERTY = "skinMetadata";

    public static final String CLOAK_URL_PROPERTY = "cloakURL";

    public static final String CLOAK_DIGEST_PROPERTY = "cloakDigest";

    public static final String CLOAK_METADATA_PROPERTY = "cloakMetadata";

    // Used to determine from clientside is launched from launcher
    public static final AtomicBoolean LAUNCHED = new AtomicBoolean(false);
    public static final int PROTOCOL_MAGIC_LEGACY = 0x724724_00 + 24;
    public static final int PROTOCOL_MAGIC = 0xA205B064; // e = 2.718281828
    public static final String RUNTIME_DIR = "runtime";

    // Constants
    public static final String GUARD_DIR = "guard";
    private static final AtomicReference<LauncherConfig> CONFIG = new AtomicReference<>();
    private static final Pattern UUID_PATTERN = Pattern.compile("-", Pattern.LITERAL);
    public static ClientProfile profile;
    public static GsonManager gsonManager;

    public static LauncherConfig getConfig() {
        LauncherConfig config = CONFIG.get();
        if (config == null) {
            Map<String, byte[]> runtime = new HashMap<>(256);
            config = new LauncherConfig("ws://foxesworld.ru:9274/api", runtime, "FoxesWorld");
        }
        return config;
    }


    public static void setConfig(LauncherConfig cfg) {
        CONFIG.set(cfg);
    }


    public static URL getResourceURL(String name) throws IOException {
        LauncherConfig config = getConfig();
        byte[] validDigest = config.runtime.get(name);
        if (validDigest == null)
            throw new NoSuchFileException(name);

        // Resolve URL and verify digest
        URL url = IOHelper.getResourceURL(RUNTIME_DIR + '/' + name);
        if (!Arrays.equals(validDigest, SecurityHelper.digest(SecurityHelper.DigestAlgorithm.MD5, url)))
            throw new NoSuchFileException(name); // Digest mismatch

        // Return verified URL
        return url;
    }

    public static URL getResourceURL(String name, String prefix) throws IOException {
        LauncherConfig config = getConfig();
        byte[] validDigest = config.runtime.get(name);
        if (validDigest == null)
            throw new NoSuchFileException(name);

        // Resolve URL and verify digest
        URL url = IOHelper.getResourceURL(prefix + '/' + name);
        if (!Arrays.equals(validDigest, SecurityHelper.digest(SecurityHelper.DigestAlgorithm.MD5, url)))
            throw new NoSuchFileException(name); // Digest mismatch

        // Return verified URL
        return url;
    }

    public static String toHash(UUID uuid) {
        return UUID_PATTERN.matcher(uuid.toString()).replaceAll("");
    }

    public static void applyLauncherEnv(LauncherConfig.LauncherEnvironment env) {
        switch (env) {
            case DEV:
                LogHelper.setDevEnabled(true);
                LogHelper.setStacktraceEnabled(true);
                LogHelper.setDebugEnabled(true);
                break;
            case DEBUG:
                LogHelper.setDebugEnabled(true);
                LogHelper.setStacktraceEnabled(true);
                break;
            case STD:
                break;
            case PROD:
                LogHelper.setStacktraceEnabled(false);
                LogHelper.setDebugEnabled(false);
                LogHelper.setDevEnabled(false);
                break;
        }
    }
}
