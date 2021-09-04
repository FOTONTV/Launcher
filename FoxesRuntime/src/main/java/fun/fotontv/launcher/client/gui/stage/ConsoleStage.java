package fun.fotontv.launcher.client.gui.stage;

import fun.fotontv.launcher.client.gui.impl.AbstractStage;
import fun.fotontv.launcher.client.gui.JavaFXApplication;

public class ConsoleStage extends AbstractStage {
    public ConsoleStage(JavaFXApplication application) {
        super(application.newStage());
        stage.setTitle(String.format("%s Launcher Console", application.config.projectName));
        stage.setResizable(false);
    }
}
