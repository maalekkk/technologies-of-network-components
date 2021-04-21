package pl.lodz.p.tks.user.restadapters.adapters;

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
import pl.lodz.p.tks.user.core.domainmodel.user.Role;
import pl.lodz.p.tks.user.core.domainmodel.user.User;
import pl.lodz.p.tks.user.restadapters.data.user.RoleRest;
import pl.lodz.p.tks.user.restadapters.data.user.UserRest;

import java.nio.file.Path;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@Testcontainers
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAdapterTest {
    @Container
    private static final GenericContainer app = new GenericContainer(
            new ImageFromDockerfile().withDockerfileFromBuilder(dockerfileBuilder -> dockerfileBuilder
                    .from("payara/server-full:5.2021.1-jdk11")
                    .copy("RestAdapters.war", "/opt/payara/deployments")
                    .build())
                    .withFileFromPath("RestAdapters.war", Path.of("target", "UserRestAdapters-1.0-SNAPSHOT.war")))
            .withExposedPorts(8080, 4848)
            .waitingFor(Wait.forHttp("/VMRentalUser/api/users").forPort(8080).forStatusCode(200));

    @BeforeClass
    public static void setupClass() {
        app.start();
        RestAssured.baseURI = "http://localhost:" + app.getMappedPort(8080) + "/VMRental/api/users";
    }

    @Test
    public void getAllTest() {
        var usersResponse = RestAssured.given()
                .when()
                .get();
        usersResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var usersJson = new JSONArray(usersResponse.body().asString());
        Assert.assertEquals(usersJson.length(), 3);
    }

    @Test
    public void getUserByIdTest() {
        String userName = "Blazz";

        var userByNameResponse = RestAssured.given()
                .when()
                .queryParam("username", userName)
                .get("/user");
        userByNameResponse.then()
                .contentType("application/json")
                .statusCode(200);
        var userByNameJson = new JSONObject(userByNameResponse.body().asString());

        String userId = userByNameJson.getString("id");

        var userByIdResponse = RestAssured.given()
                .when()
                .get("/" + userId);
        userByIdResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var userByIdJson = new JSONObject(userByIdResponse.body().asString());
        Assert.assertEquals(userByNameJson.getString("username"), userByIdJson.getString("username"));
    }

    @Test
    public void getUserByNameTest() {
        String userName = "Blazz";

        var userResponse = RestAssured.given()
                .when()
                .queryParam("username", userName)
                .get("/user");
        userResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var userJson = new JSONObject(userResponse.body().asString());
        Assert.assertEquals(userJson.get("fullname"), "Maciej Błażewicz");
    }

    @Test
    public void updateUserByIdTest() {
        String userName = "Blazz";

        var userResponse = RestAssured.given()
                .when()
                .queryParam("username", userName)
                .get("/user");
        userResponse.then()
                .contentType("application/json")
                .statusCode(200);
        var userJson = new JSONObject(userResponse.body().asString());
        String userId = userJson.getString("id");

        var updatedUser = new UserRest(
                userJson.getString("username"),
                "Blazz123456",
                userJson.getString("fullname") + "Updated",
                userJson.getBoolean("enabled"),
                userJson.getJSONArray("roles").toList().stream().map(role -> RoleRest.valueOf(role.toString()))
                        .collect(Collectors.toSet()));

        updatedUser.setId(UUID.fromString(userId));
        var updatedUserJSON = new JSONObject(updatedUser);
        updatedUserJSON.remove("rolesAsString");

        userResponse = RestAssured.given()
                .contentType("application/json")
                .body(updatedUserJSON.toString())
                .when()
                .put("/" + userId);
        userResponse.then()
                .statusCode(200);

        userResponse = RestAssured.given()
                .when()
                .queryParam("username", userName)
                .get("/user");
        userResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var userUpdatedJson = new JSONObject(userResponse.body().asString());
        Assert.assertEquals(userUpdatedJson.getString("fullname"), userJson.getString("fullname") + "Updated");
    }

    @Test
    public void insertUserTest() {
        var usersResponse = RestAssured.given()
                .when()
                .get();
        usersResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var usersJsonArray = new JSONArray(usersResponse.body().asString());
        int usersLengthBefore = usersJsonArray.length();

        User user = new User("JTesto", "testo123", "Lukas Zimmerman", true, Collections.singleton(Role.Client));
        var newUserJson = new JSONObject(user);
        newUserJson.remove("rolesAsString");

        usersResponse = RestAssured.given()
                .contentType("application/json")
                .body(newUserJson.toString())
                .when().post();
        usersResponse.then()
                .statusCode(200);

        var userJson = new JSONObject(usersResponse.body().asString());
        Assert.assertEquals(userJson.getString("username"), "JTesto");
        Assert.assertEquals(userJson.getString("fullname"), "Lukas Zimmerman");
        Assert.assertTrue(userJson.getBoolean("enabled"));

        usersResponse = RestAssured.given()
                .when()
                .get();
        usersResponse.then()
                .contentType("application/json")
                .statusCode(200);

        usersJsonArray = new JSONArray(usersResponse.body().asString());
        int usersLengthAfter = usersJsonArray.length();
        Assert.assertEquals(usersLengthAfter, usersLengthBefore + 1);
    }
}