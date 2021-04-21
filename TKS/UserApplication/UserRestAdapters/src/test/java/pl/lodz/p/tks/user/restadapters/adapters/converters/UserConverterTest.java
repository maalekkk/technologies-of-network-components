package pl.lodz.p.tks.user.restadapters.adapters.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.user.core.domainmodel.user.Role;
import pl.lodz.p.tks.user.core.domainmodel.user.User;
import pl.lodz.p.tks.user.restadapters.data.user.RoleRest;
import pl.lodz.p.tks.user.restadapters.data.user.UserRest;

import java.util.Collections;
import java.util.UUID;

public class UserConverterTest {

    private User user;
    private UserRest userRest;

    @Before
    public void init() {
        user = new User("Bolek", "Bolek", "Bolesław Chrobry", true, Collections.singleton(Role.Client));
        userRest = new UserRest("Bolek", "Bolek", "Bolesław Chrobry", true, Collections.singleton(RoleRest.Client));

        UUID userId = UUID.randomUUID();
        user.setId(userId);
        userRest.setId(userId);
    }

    @Test
    public void domainToRestConversion() {
        UserRest userRest = UserConverter.fromDomainModel(user);
        Assert.assertTrue(TestUtils.compareUsers(userRest, this.userRest));
    }

    @Test
    public void entToDomainConversion() {
        User user = UserConverter.toDomainModel(userRest);
        Assert.assertTrue(TestUtils.compareUsers(user, this.user));
    }
}
