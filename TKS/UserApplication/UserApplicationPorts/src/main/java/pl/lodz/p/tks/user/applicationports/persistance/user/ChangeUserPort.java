package pl.lodz.p.tks.user.applicationports.persistance.user;

import pl.lodz.p.tks.user.core.domainmodel.user.User;

public interface ChangeUserPort {
    void changeUserActivity(User user);
}
