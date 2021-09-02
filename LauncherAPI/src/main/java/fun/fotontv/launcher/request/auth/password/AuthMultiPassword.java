package fun.fotontv.launcher.request.auth.password;

import fun.fotontv.launcher.request.auth.AuthPasswordInterface;

import java.util.List;

public class AuthMultiPassword implements AuthPasswordInterface {
    public List<AuthPasswordInterface> list;

    @Override
    public boolean check() {
        return list != null && list.stream().allMatch(l -> l != null && l.check());
    }

    @Override
    public boolean isAllowSave() {
        return list != null && list.stream().allMatch(l -> l != null && l.isAllowSave());
    }
}
