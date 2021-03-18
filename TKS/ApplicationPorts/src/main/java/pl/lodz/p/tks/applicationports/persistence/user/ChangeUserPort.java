package pl.lodz.p.tks.applicationports.persistence.user;

import pl.lodz.p.tks.view.domainmodel.model.user.User;

public interface ChangeUserPort {
    void changeUserActivity(User user);
}
