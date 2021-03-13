package pl.lodz.p.tks.applicationports.output.User;

import pl.lodz.p.tks.applicationcore.domainmodel.model.user.User;

public interface ChangeUserPort {
    void changeUserActivity(User user);
}
