package pl.lodz.p.tks.applicationports.output.User;

import pl.lodz.p.tks.view.domainmodel.model.user.User;

import javax.validation.Valid;

public interface SaveUserPort {
    User saveUser(@Valid User user);
}
