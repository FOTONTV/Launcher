package fun.fotontv.launcher.request.auth;

public interface AuthPasswordInterface {
    boolean check();

    default boolean isAllowSave() {
        return false;
    }
}
