package pl.lodz.p.tks.user.applicationports.persistance.user;

import pl.lodz.p.tks.user.core.domainmodel.user.User;

import javax.validation.Valid;

public interface SaveUserPort {
    User saveUser(@Valid User user);
}
