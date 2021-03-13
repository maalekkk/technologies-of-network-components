package pl.lodz.p.tks.applicationports.output.User;

import pl.lodz.p.tks.view.domainmodel.model.user.User;

public interface ChangeUserPort {
    void changeUserActivity(User user);
}
