package fun.fotontv.launcher.client.gui.service;

import fun.fotontv.launcher.Launcher;
import fun.fotontv.launcher.LauncherConfig;
import fun.fotontv.launcher.client.gui.JavaFXApplication;
import fun.fotontv.launcher.request.auth.AuthRequest;
import fun.fotontv.launcher.request.auth.password.Auth2FAPassword;
import fun.fotontv.launcher.request.auth.password.AuthAESPassword;
import fun.fotontv.launcher.request.auth.password.AuthPlainPassword;
import fun.fotontv.launcher.request.auth.password.AuthTOTPPassword;
import fun.fotontv.launcher.utils.HWIDProvider;
import fun.fotontv.utils.helper.SecurityHelper;

public class AuthService {
    private final LauncherConfig config = Launcher.getConfig();
    private final JavaFXApplication application;

    public AuthService(JavaFXApplication application) {
        this.application = application;
    }

    public AuthRequest.AuthPasswordInterface makePassword(String plainPassword) {
        if(config.passwordEncryptKey != null) {
            try {
                return new AuthAESPassword(encryptAESPassword(plainPassword));
            } catch (Exception ignored) {
            }
        }
        return new AuthPlainPassword(plainPassword);
    }
    public AuthRequest.AuthPasswordInterface make2FAPassword(AuthRequest.AuthPasswordInterface firstPassword, String totp) {
        Auth2FAPassword auth2FAPassword = new Auth2FAPassword();
        auth2FAPassword.firstPassword = firstPassword;
        auth2FAPassword.secondPassword = new AuthTOTPPassword();
        ((AuthTOTPPassword) auth2FAPassword.secondPassword).totp = totp;
        return auth2FAPassword;
    }
    public AuthRequest makeAuthRequest(String login, AuthRequest.AuthPasswordInterface password, String authId) {
        HWIDProvider hwidProvider = new HWIDProvider();
        return new AuthRequest(login, password, authId, true, application.isDebugMode() ? AuthRequest.ConnectTypes.API : AuthRequest.ConnectTypes.CLIENT, hwidProvider.getHardwareId());
    }
    private byte[] encryptAESPassword(String password) throws Exception {
        return SecurityHelper.encrypt(Launcher.getConfig().passwordEncryptKey, password);
    }
}
