package pl.lodz.p.tks.restadapters.adapters.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.restadapters.data.user.UserRest;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.util.UUID;

import static pl.lodz.p.tks.restadapters.adapters.converters.TestUtils.compareUsers;

public class UserConverterTest {

    private User user;
    private UserRest userRest;

    @Before
    public void init() {
        user = new User("Bolek", "Bolesław Chrobry", true);
        userRest = new UserRest("Bolek", "Bolesław Chrobry", true);

        UUID userId = UUID.randomUUID();
        user.setId(userId);
        userRest.setId(userId);
    }

    @Test
    public void domainToRestConversion() {
        UserRest userRest = UserConverter.fromDomainModel(user);
        Assert.assertTrue(compareUsers(userRest, this.userRest));
    }

    @Test
    public void entToDomainConversion() {
        User user = UserConverter.toDomainModel(userRest);
        Assert.assertTrue(compareUsers(user, this.user));
    }
}
