package fun.fotontv.launcher.request.auth;

import fun.fotontv.launcher.LauncherNetworkAPI;
import fun.fotontv.launcher.events.request.RegisterRequestEvent;
import fun.fotontv.launcher.request.Request;
import fun.fotontv.launcher.request.auth.password.*;
import fun.fotontv.launcher.request.websockets.WebSocketRequest;
import fun.fotontv.utils.ProviderMap;
import fun.fotontv.utils.helper.VerifyHelper;

public final class RegisterRequest extends Request<RegisterRequestEvent> implements WebSocketRequest {
    public static final ProviderMap<AuthPasswordInterface> providers = new ProviderMap<>();
    private static boolean registerProviders = false;
    @LauncherNetworkAPI
    private final String login;
    @LauncherNetworkAPI
    private final String email;
    @LauncherNetworkAPI
    private final AuthPasswordInterface password;
    @LauncherNetworkAPI
    private final String auth_id;
    @LauncherNetworkAPI
    private final boolean getSession;
    @LauncherNetworkAPI
    private final ConnectTypes authType;

    public RegisterRequest(String login, String email, byte[] password) {
        this.login = VerifyHelper.verify(login, VerifyHelper.NOT_EMPTY, "Login can't be empty");
        this.email = VerifyHelper.verify(email, VerifyHelper.NOT_EMPTY, "Email can't be empty");
        this.password = new AuthECPassword(password.clone());
        auth_id = "";
        getSession = true;
        authType = ConnectTypes.CLIENT;
    }

    public RegisterRequest(String login, String email, byte[] password, String auth_id) {
        this.login = VerifyHelper.verify(login, VerifyHelper.NOT_EMPTY, "Login can't be empty");
        this.email = VerifyHelper.verify(email, VerifyHelper.NOT_EMPTY, "Email can't be empty");
        this.password = new AuthECPassword(password.clone());
        this.auth_id = auth_id;
        getSession = true;
        authType = ConnectTypes.CLIENT;
    }

    public RegisterRequest(String login, String email, byte[] encryptedPassword, String auth_id, ConnectTypes authType) {
        this.login = login;
        this.email = email;
        this.password = new AuthECPassword(encryptedPassword.clone());
        this.auth_id = auth_id;
        this.authType = authType;
        this.getSession = false;
    }

    public static void registerProviders() {
        if (!registerProviders) {
            providers.register("plain", AuthPlainPassword.class);
            providers.register("rsa2", AuthRSAPassword.class);
            providers.register("rsa", AuthECPassword.class);
            providers.register("aes", AuthAESPassword.class);
            providers.register("2fa", Auth2FAPassword.class);
            providers.register("multi", AuthMultiPassword.class);
            providers.register("signature", AuthSignaturePassword.class);
            providers.register("totp", AuthTOTPPassword.class);
            providers.register("oauth", AuthOAuthPassword.class);
            providers.register("code", AuthCodePassword.class);
            registerProviders = true;
        }
    }

    @Override
    public String getType() {
        return "register";
    }

    public enum ConnectTypes {
        @LauncherNetworkAPI
        SERVER,
        @LauncherNetworkAPI
        CLIENT,
        @LauncherNetworkAPI
        API
    }
}
