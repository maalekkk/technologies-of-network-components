package pl.lodz.p.tks.soapadapters.adapters;

import org.junit.Assert;
import org.junit.Test;
import pl.lodz.p.tks.soapadapters.client.UserAdapterService;

public class UserAdapterTest {

    @Test
    public void getTest() {
        UserAdapterService userAdapterService = new UserAdapterService();
        Assert.assertEquals(3, userAdapterService.getUserAdapterPort().getUsers().size());
    }
}
