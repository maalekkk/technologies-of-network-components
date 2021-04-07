package pl.lodz.p.tks.soapadapters.adapters;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.lodz.p.tks.soapadapters.client.IUserAdapter;
import pl.lodz.p.tks.soapadapters.client.UserAdapterService;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

@Testcontainers
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
            .waitingFor(Wait.forHttp("/Soap/UserAdapterService?wsdl").forPort(8080).forStatusCode(200));

    @BeforeClass
    public static void setupClass() {
        app.start();
    }

    @Test
    public void getTest() throws MalformedURLException {
        URL wsdlURL = new URL("http://localhost:" + app.getMappedPort(8080) + "/Soap/UserAdapterService");
        UserAdapterService userAdapterService = new UserAdapterService(wsdlURL);
        userAdapterPort = userAdapterService.getUserAdapterPort();
        Assert.assertEquals(3, userAdapterPort.getUsers().size());
    }
}
