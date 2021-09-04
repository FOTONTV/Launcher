package fun.fotontv.launcher.client.gui.service;

import fun.fotontv.launcher.api.DialogService;
import fun.fotontv.launcher.client.gui.impl.MessageManager;
import fun.fotontv.launcher.events.NotificationEvent;

import java.util.function.Consumer;

public class RuntimeDialogService implements DialogService.DialogServiceNotificationImplementation, DialogService.DialogServiceImplementation {
    private final MessageManager messageManager;

    public RuntimeDialogService(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    @Override
    public void showDialog(String header, String text, Runnable onApplyCallback, Runnable onCloseCallback) {
        messageManager.showDialog(header, text, onApplyCallback, onCloseCallback, false);
    }

    @Override
    public void showApplyDialog(String header, String text, Runnable onApplyCallback, Runnable onDenyCallback) {
        messageManager.showApplyDialog(header, text, onApplyCallback, onDenyCallback, false);
    }

    @Override
    public void showApplyDialog(String header, String text, Runnable onApplyCallback, Runnable onDenyCallback, Runnable onCloseCallback) {
        messageManager.showApplyDialog(header, text, onApplyCallback, onDenyCallback, onCloseCallback, false);
    }

    @Override
    public void showTextDialog(String header, Consumer<String> onApplyCallback, Runnable onCloseCallback) {
        messageManager.showTextDialog(header, onApplyCallback, onCloseCallback, false);
    }

    @Override
    public void createNotification(NotificationEvent.NotificationType type, String head, String message) {
        messageManager.createNotification(head, message, false);
    }
}
