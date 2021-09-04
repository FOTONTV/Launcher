package fun.fotontv.launcher.guard;

import fun.fotontv.launcher.Launcher;
import fun.fotontv.launcher.client.ClientLauncherProcess;
import fun.fotontv.launcher.client.DirBridge;
import fun.fotontv.utils.helper.JVMHelper;
import fun.fotontv.utils.helper.UnpackHelper;

import java.io.IOException;

public class LauncherWrapperGuard implements LauncherGuardInterface {
    public LauncherWrapperGuard() {
        try {
            String wrapperName = JVMHelper.JVM_BITS == 64 ? "wrapper64.exe" : "wrapper32.exe";
            String projectName = Launcher.getConfig().projectName;
            String wrapperUnpackName = JVMHelper.JVM_BITS == 64 ? projectName.concat("64.exe") : projectName.concat("32.exe");
            String antiInjectName = JVMHelper.JVM_BITS == 64 ? "AntiInject64.dll" : "AntiInject32.dll";
            UnpackHelper.unpack(Launcher.getResourceURL(wrapperName, "guard"), DirBridge.getGuardDir().resolve(wrapperUnpackName));
            UnpackHelper.unpack(Launcher.getResourceURL(antiInjectName, "guard"), DirBridge.getGuardDir().resolve(antiInjectName));
        } catch (IOException e) {
            throw new SecurityException(e);
        }
    }

    @Override
    public String getName() {
        return "wrapper";
    }

    @Override
    public void applyGuardParams(ClientLauncherProcess process) {
        if (JVMHelper.OS_TYPE == JVMHelper.OS.MUSTDIE) {
            String projectName = Launcher.getConfig().projectName;
            String wrapperUnpackName = JVMHelper.JVM_BITS == 64 ? projectName.concat("64.exe") : projectName.concat("32.exe");
            process.executeFile = DirBridge.getGuardDir().resolve(wrapperUnpackName);
            process.useLegacyJavaClassPathProperty = true;
        }
    }
}
