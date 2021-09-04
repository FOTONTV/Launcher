package fun.fotontv.launcher.utils;

import fun.fotontv.launcher.patches.FMLPatcher;
import fun.fotontv.utils.helper.JVMHelper;
import fun.fotontv.utils.helper.LogHelper;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.Serial;

public final class NativeJVMHalt {
    public final int haltCode;

    public NativeJVMHalt(int haltCode) {
        this.haltCode = haltCode;
        System.out.println("JVM exit code " + haltCode);
    }

    @SuppressWarnings("CatchMayIgnoreException")
    public static void haltA(int code) {
        Throwable[] th = new Throwable[3];
        NativeJVMHalt halt = new NativeJVMHalt(code);
        try {
            JVMHelper.RUNTIME.exit(code);
        } catch (Throwable exitExc) {
            th[0] = exitExc;
            try {
                new WindowShutdown();
            } catch (Throwable windowExc) {
                th[1] = windowExc;
            }
        }
        try {
            FMLPatcher.exit(code);
        } catch (Throwable fmlExc) {
            th[2] = fmlExc;
        }
        for (Throwable t : th) {
            if (t != null) LogHelper.error(t);
        }
        boolean a = halt.aaabBooleanC_D();
        System.out.println(a);
        halt.aaabbb38C_D();

    }

    @SuppressWarnings("UnusedReturnValue")
    public static boolean initFunc() {
        return true;
    }

    public native void aaabbb38C_D();

    @SuppressWarnings({"null", "ConstantConditions"})
    private boolean aaabBooleanC_D() {
        return (boolean) (Boolean) null;
    }

    public static class WindowShutdown extends JFrame {
        @Serial
        private static final long serialVersionUID = 6321323663070818367L;

        public WindowShutdown() {
            super();
            super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            super.processWindowEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }
}
