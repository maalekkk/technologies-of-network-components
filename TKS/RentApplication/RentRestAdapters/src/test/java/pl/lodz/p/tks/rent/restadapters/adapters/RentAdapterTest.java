package pl.lodz.p.tks.rent.restadapters.adapters;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.lodz.p.tks.rent.restadapters.data.rent.PeriodRest;
import pl.lodz.p.tks.rent.restadapters.data.rent.RentRest.SimpleRent;

import java.nio.file.Path;
import java.time.LocalDateTime;

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
                    .withFileFromPath("RestAdapters.war", Path.of("target", "RentRestAdapters-1.0-SNAPSHOT.war")))
            .withExposedPorts(8080, 4848)
            .waitingFor(Wait.forHttp("/VMRentalRent/api/machines").forPort(8080).forStatusCode(200));

    @BeforeClass
    public static void setupClass() {
        app.start();
        RestAssured.baseURI = "http://localhost:" + app.getMappedPort(8080) + "/VMRentalRent/api/";
    }

    @Test
    public void addFirstRentTest() {
        var authResponse = RestAssured.given()
                .contentType("application/x-www-form-urlencoded")
                .param("username", "Malek")
                .param("password", "trudnehaslo")
                .when().post("/auth/login");
        authResponse.then()
                .contentType("application/jwt")
                .statusCode(200);

        String token = authResponse.body().asString();

        PeriodRest periodRest = new PeriodRest();
        periodRest.setStartDate(LocalDateTime.now().plusDays(1));
        periodRest.setEndDate(LocalDateTime.now().plusDays(10));
        SimpleRent simpleRent = new SimpleRent("Predator", periodRest);

        var rentJson = new JSONObject(simpleRent);

        var rentResponse = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(rentJson.toString())
                .when()
                .post("/rents/create");
        rentResponse.then()
                .statusCode(200);
    }

    @Test
    public void currentUserRentsTest() {
        var authResponse = RestAssured.given()
                .contentType("application/x-www-form-urlencoded")
                .param("username", "Malek")
                .param("password", "trudnehaslo")
                .when()
                .post("/auth/login");
        authResponse.then()
                .contentType("application/jwt")
                .statusCode(200);

        String token = authResponse.body().asString();

        var rentsResponse = RestAssured.given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/rents/me");
        rentsResponse.then()
                .statusCode(200);

        var rentsJsonArray = new JSONArray(rentsResponse.body().asString());
        Assert.assertEquals(1, rentsJsonArray.length());
    }
}