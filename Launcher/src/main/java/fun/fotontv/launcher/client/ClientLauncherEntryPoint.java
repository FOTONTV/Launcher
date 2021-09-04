package fun.fotontv.launcher.client;

import fun.fotontv.launcher.profiles.ClientProfile;
import fun.fotontv.launcher.profiles.optional.actions.OptionalAction;
import fun.fotontv.launcher.profiles.optional.actions.OptionalActionClassPath;
import fun.fotontv.utils.helper.IOHelper;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.util.stream.Stream;

public class ClientLauncherEntryPoint {

    public static void main(String[] args) throws Throwable {

    }

    public static Stream<Path> resolveClassPath(Path clientDir, Set<OptionalAction> actions, ClientProfile profile) throws IOException {
        Stream<Path> result = resolveClassPathStream(clientDir, profile.getClassPath());
        for (OptionalAction a : actions) {
            if (a instanceof OptionalActionClassPath)
                result = Stream.concat(result, resolveClassPathStream(clientDir, ((OptionalActionClassPath) a).args));
        }
        return result;
    }

    private static Stream<Path> resolveClassPathStream(Path clientDir, String... classPath) throws IOException {
        Stream.Builder<Path> builder = Stream.builder();
        for (String classPathEntry : classPath) {
            Path path = clientDir.resolve(IOHelper.toPath(classPathEntry.replace(IOHelper.CROSS_SEPARATOR, IOHelper.PLATFORM_SEPARATOR)));
            if (IOHelper.isDir(path)) { // Recursive walking and adding
                IOHelper.walk(path, new ClassPathFileVisitor(builder), false);
                continue;
            }
            builder.accept(path);
        }
        return builder.build();
    }

    private static final class ClassPathFileVisitor extends SimpleFileVisitor<Path> {
        private final Stream.Builder<Path> result;

        private ClassPathFileVisitor(Stream.Builder<Path> result) {
            this.result = result;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (IOHelper.hasExtension(file, "jar") || IOHelper.hasExtension(file, "zip"))
                result.accept(file);
            return super.visitFile(file, attrs);
        }
    }
}
