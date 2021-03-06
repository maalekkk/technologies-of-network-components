package pl.lodz.pl.tks.repositoriesadapters.adapters.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.repositoriesadapters.adapters.converters.UserConverter;
import pl.lodz.p.tks.repositoriesadapters.data.user.RoleEnt;
import pl.lodz.p.tks.repositoriesadapters.data.user.UserEnt;
import pl.lodz.p.tks.view.domainmodel.model.user.Role;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.util.Collections;
import java.util.UUID;

import static pl.lodz.pl.tks.repositoriesadapters.adapters.converters.TestUtils.compareUsers;

public class UserConverterTest {

    private User user;
    private UserEnt userEnt;

    @Before
    public void init() {
        user = new User("Bolek", "Bolek", "Bolesław Chrobry", true, Collections.singleton(Role.Client));
        userEnt = new UserEnt("Bolek", "Bolek", "Bolesław Chrobry", true, Collections.singleton(RoleEnt.Client));

        UUID userId = UUID.randomUUID();
        user.setId(userId);
        userEnt.setId(userId);
    }

    @Test
    public void domainToEntConversion() {
        UserEnt userEnt = UserConverter.fromDomainModel(user);
        Assert.assertTrue(compareUsers(userEnt, this.userEnt));
    }

    @Test
    public void entToDomainConversion() {
        User user = UserConverter.toDomainModel(userEnt);
        Assert.assertTrue(compareUsers(user, this.user));
    }
}
