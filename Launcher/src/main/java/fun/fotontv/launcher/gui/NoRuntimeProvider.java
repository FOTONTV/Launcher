package fun.fotontv.launcher.gui;

import javax.swing.*;

public class NoRuntimeProvider implements RuntimeProvider {
    @Override
    public void run(String[] args) throws Exception {
        JOptionPane.showMessageDialog(null, "GUI часть лаунчера не найдена.");
    }

    @Override
    public void preLoad() throws Exception {

    }

    @Override
    public void init(boolean clientInstance) {

    }
}
