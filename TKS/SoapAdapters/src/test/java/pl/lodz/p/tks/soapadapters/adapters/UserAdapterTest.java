package pl.lodz.p.tks.soapadapters.adapters;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.lodz.p.tks.soapadapters.client.IUserAdapter;
import pl.lodz.p.tks.soapadapters.client.UserAdapterService;
import pl.lodz.p.tks.soapadapters.client.UserSoap;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

@Testcontainers
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAdapterTest {
    static IUserAdapter userAdapterPort;

    @Container
    private static final GenericContainer app = new GenericContainer(
            new ImageFromDockerfile().withDockerfileFromBuilder(dockerfileBuilder -> dockerfileBuilder
                    .from("payara/server-full:5.2021.1-jdk11")
                    .copy("SoapAdapters.war", "/opt/payara/deployments")
                    .build())
                    .withFileFromPath("SoapAdapters.war", Path.of("target", "SoapAdapters-1.0-SNAPSHOT.war")))
            .withExposedPorts(8080, 4848)
            .waitingFor(Wait.forHttp("/SoapAdapters/UserAdapterService?wsdl").forPort(8080).forStatusCode(200));

    @BeforeClass
    public static void setupClass() throws MalformedURLException {
        app.start();
        URL wsdlURL = new URL("http://desktop-sosn1q8:" + app.getMappedPort(8080) + "/SoapAdapters/UserAdapterService");
        UserAdapterService userAdapterService = new UserAdapterService(wsdlURL);
        userAdapterPort = userAdapterService.getUserAdapterPort();
    }

    @Test
    public void getAllTest() {
        Assert.assertEquals(3, userAdapterPort.getUsers().size());
    }

    @Test
    public void getUserByUsernameTest() {
        Assert.assertEquals("Bartłomiej Małkowski", userAdapterPort.getUserByUsername("Malek").getFullname());
        Assert.assertTrue(userAdapterPort.getUserByUsername("Malek").isEnabled());
    }

    @Test
    public void getUserByIdTest() {
        final UserSoap user = userAdapterPort.getUserByUsername("Malek");
        Assert.assertEquals(user.getFullname(), userAdapterPort.getUserById(user.getId()).getFullname());
        Assert.assertEquals(user.getUsername(), userAdapterPort.getUserById(user.getId()).getUsername());
        Assert.assertEquals(user.isEnabled(), userAdapterPort.getUserById(user.getId()).isEnabled());
    }

    @Test
    public void updateUserTest() {
        UserSoap user = userAdapterPort.getUserByUsername("Malek");
        user.setFullname("Test test");
        userAdapterPort.updateUserById(user.getId(), user);
        Assert.assertEquals("Test test", userAdapterPort.getUserById(user.getId()).getFullname());
        Assert.assertEquals(3, userAdapterPort.getUsers().size());
    }

    @Test
    public void zAddUserTest() {
        Assert.assertEquals(3, userAdapterPort.getUsers().size());
        UserSoap userSoap = new UserSoap();
        userSoap.setEnabled(true);
        userSoap.setUsername("Test");
        userSoap.setFullname("Test test");
        userAdapterPort.addUser(userSoap);
        Assert.assertEquals(4, userAdapterPort.getUsers().size());
        UserSoap newUser = userAdapterPort.getUsers().get(userAdapterPort.getUsers().size() - 1);
        Assert.assertEquals("Test", newUser.getUsername());
        Assert.assertEquals("Test test", newUser.getFullname());
        Assert.assertTrue(newUser.isEnabled());
    }
}