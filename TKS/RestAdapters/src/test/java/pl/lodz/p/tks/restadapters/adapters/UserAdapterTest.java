package pl.lodz.p.tks.restadapters.adapters;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.nio.file.Path;

@Testcontainers
public class UserAdapterTest {
    @Container
    private static final GenericContainer app = new GenericContainer(
            new ImageFromDockerfile().withDockerfileFromBuilder(dockerfileBuilder -> dockerfileBuilder
                    .from("payara/server-full:5.2021.1-jdk11")
                    .copy("RestAdapters.war", "/opt/payara/deployments")
                    .build())
                    .withFileFromPath("RestAdapters.war", Path.of("target", "RestAdapters-1.0-SNAPSHOT.war")))
            .withExposedPorts(8080, 4848)
            .waitingFor(Wait.forHttp("/VMRental/api/users").forPort(8080).forStatusCode(200));

    static HttpClient client;
    static String baseUrl;

    @BeforeClass
    public static void setupClass() {
        app.start();
        client = HttpClientBuilder.create().build();
        baseUrl = "http://localhost:" + app.getMappedPort(8080) + "/VMRental/api/users";
    }

    @Test
    public void getAllTest() throws IOException {
        HttpGet getRequest = new HttpGet(baseUrl);
        HttpResponse response = client.execute(getRequest);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }
}