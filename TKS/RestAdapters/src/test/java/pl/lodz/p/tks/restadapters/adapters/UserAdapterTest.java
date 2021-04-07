package pl.lodz.p.tks.restadapters.adapters;

import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.primefaces.shaded.json.JSONArray;
import org.primefaces.shaded.json.JSONObject;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.images.builder.ImageFromDockerfile;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.lodz.p.tks.restadapters.data.user.UserRest;
import pl.lodz.p.tks.view.domainmodel.model.user.User;

import java.nio.file.Path;
import java.util.UUID;

import static io.restassured.RestAssured.given;

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


    @BeforeClass
    public static void setupClass() {
        app.start();

        RestAssured.baseURI = "http://localhost:" + app.getMappedPort(8080) + "/VMRental/api/users";
    }


    @Test
    public void getAllTest() {
        var res = given()
                .when()
                .get();
        res.then()
                .contentType("application/json")
                .statusCode(200);

        var jsonArray = new JSONArray(res.body().asString());
        Assert.assertEquals(jsonArray.length(), 3);
    }

    @Test
    public void getUserByIdTest() {
        String userName = "Blazz";

        var res = given()
                .when()
                .queryParam("username", userName)
                .get("/user");
        res.then()
                .contentType("application/json")
                .statusCode(200);
        var jsonObj = new JSONObject(res.body().asString());

        String userId = jsonObj.getString("id");

        var res2 = given()
                .when()
                .get("/" + userId);
        res2.then()
                .contentType("application/json")
                .statusCode(200);

        var jsonObj2 = new JSONObject(res.body().asString());

        Assert.assertEquals(jsonObj.getString("username"), jsonObj2.getString("username"));
    }

    @Test
    public void getUserByNameTest() {
        String userName = "Blazz";

        var res = given()
                .when()
                .queryParam("username", userName)
                .get("/user");
        res.then()
                .contentType("application/json")
                .statusCode(200);

        var jsonObj = new JSONObject(res.body().asString());
        Assert.assertEquals(jsonObj.get("fullname"), "Maciej Błażewicz");
    }

    @Test
    public void addUserTest() {
        var res = given()
                .when()
                .get();
        res.then()
                .contentType("application/json")
                .statusCode(200);

        var jsonArray = new JSONArray(res.body().asString());
        var jsonArrLenBefore = jsonArray.length();

        User newUser = new User("testo", "Lukas Zimmerman", true);

        res = given()
                .contentType("application/json")
                .body(new JSONObject(newUser).toString())
                .when().post();
        res.then()
                .statusCode(200);

        var jsonObj = new JSONObject(res.body().asString());
        Assert.assertEquals(jsonObj.getString("username"), "testo");
        Assert.assertEquals(jsonObj.getString("fullname"), "Lukas Zimmerman");
        Assert.assertTrue(jsonObj.getBoolean("enabled"));

        res = given()
                .when()
                .get();
        res.then()
                .contentType("application/json")
                .statusCode(200);

        jsonArray = new JSONArray(res.body().asString());
        var jsonArrLenAfter = jsonArray.length();

        Assert.assertEquals(jsonArrLenAfter, jsonArrLenBefore + 1);
    }

    @Test
    public void updateUserByIdTest() {
        String userName = "Blazz";

        var res = given()
                .when()
                .queryParam("username", userName)
                .get("/user");
        res.then()
                .contentType("application/json")
                .statusCode(200);
        var jsonObj = new JSONObject(res.body().asString());
        String userId = jsonObj.getString("id");

        var updatedUser = new UserRest(jsonObj.getString("username"), jsonObj.getString("fullname") + "Updated", jsonObj.getBoolean("enabled"));
        updatedUser.setId(UUID.fromString(userId));
        var updatedUserJSON = new JSONObject(updatedUser);

        System.out.println(updatedUserJSON.toString()); // DEBUG
        res = given()
                .contentType("application/json")
                .body(updatedUserJSON.toString())
                .when()
                .put("/" + userId);
        res.then()
                .statusCode(200);

        res = given()
                .when()
                .queryParam("username", userName)
                .get("/user");
        res.then()
                .contentType("application/json")
                .statusCode(200);
        var jsonObjUpdated = new JSONObject(res.body().asString());

        Assert.assertEquals(jsonObjUpdated.getString("fullname"), jsonObj.getString("fullname") + "Updated");
    }
}