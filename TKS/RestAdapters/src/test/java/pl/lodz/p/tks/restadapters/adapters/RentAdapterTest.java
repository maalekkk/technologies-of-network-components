package pl.lodz.p.tks.restadapters.adapters;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.primefaces.shaded.json.JSONObject;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.nio.file.Path;

import static io.restassured.RestAssured.given;

@Testcontainers
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RentAdapterTest {

    @Container
    private static final GenericContainer app = new GenericContainer(
            new ImageFromDockerfile().withDockerfileFromBuilder(dockerfileBuilder -> dockerfileBuilder
                    .from("payara/server-full:5.2021.1-jdk11")
                    .copy("RestAdapters.war", "/opt/payara/deployments")
                    .build())
                    .withFileFromPath("RestAdapters.war", Path.of("target", "RestAdapters-1.0-SNAPSHOT.war")))
            .withExposedPorts(8080, 4848)
            .waitingFor(Wait.forHttp("/VMRental/api/users").forPort(8080).forStatusCode(200));

    @BeforeClass
    public static void setupClass() {
        app.start();
        RestAssured.baseURI = "http://localhost:" + app.getMappedPort(8080) + "/VMRental/api/";
    }

    @Test
    public void addFirstRentTest() {

        var res = given()
                .contentType("application/x-www-form-urlencoded")
                .param("username", "Malek")
                .param("password", "trudnehaslo3")
                .when().post("/auth/login");
        res.then()
                .contentType("text/html")
                .statusCode(200);

        var jsonObj = new JSONObject(res.body().asString());
        System.out.println(jsonObj.toString());
    }

    @Test
    public void addSecondRentTest() {

    }

    @Test
    public void currentUserRentsTest() {

    }
}