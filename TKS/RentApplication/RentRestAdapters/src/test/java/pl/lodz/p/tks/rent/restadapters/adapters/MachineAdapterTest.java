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
import pl.lodz.p.tks.rent.restadapters.data.machine.MachineGamingRest;
import pl.lodz.p.tks.rent.restadapters.data.machine.MachineWorkstationRest;
import pl.lodz.p.tks.rent.core.domainmodel.machine.MachineWorkstation;

import java.nio.file.Path;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@Testcontainers
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MachineAdapterTest {
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
        RestAssured.baseURI = "http://localhost:" + app.getMappedPort(8080) + "/VMRentalRent/api/machines";
    }

    @Test
    public void getMachinesTest() {
        var machinesResponse = RestAssured.given()
                .when()
                .get();
        machinesResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var machinesJsonArray = new JSONArray(machinesResponse.body().asString());
        Assert.assertEquals(machinesJsonArray.length(), 4);
    }

    @Test
    public void getMachineByIdTest() {
        String name = "Predator";

        var machineByNameResponse = RestAssured.given()
                .when()
                .queryParam("machinename", name)
                .get("/machine");
        machineByNameResponse.then()
                .contentType("application/json")
                .statusCode(200);
        var machineByNameJson = new JSONObject(machineByNameResponse.body().asString());

        String machineId = machineByNameJson.getString("id");

        var machineByIdResponse = RestAssured.given()
                .when()
                .get("/" + machineId);
        machineByIdResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var machineByIdJson = new JSONObject(machineByIdResponse.body().asString());
        Assert.assertEquals(machineByNameJson.getString("name"), machineByIdJson.getString("name"));
    }

    @Test
    public void getMachineByNameTest() {
        String machineName = "Predator";

        var machineResponse = RestAssured.given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        machineResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var machineJson = new JSONObject(machineResponse.body().asString());
        Assert.assertEquals(machineJson.get("name"), "Predator");
    }

    @Test
    public void insertMachineGamingTest() {
        var machinesResponse = RestAssured.given()
                .when()
                .get();
        machinesResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var machinesJsonArray = new JSONArray(machinesResponse.body().asString());
        int machinesLengthBefore = machinesJsonArray.length();

        MachineGamingRest machineGamingRest = new MachineGamingRest("Nowa Maszynka", 1, 1024, 128, 3, 5);

        var machineResponse = RestAssured.given()
                .contentType("application/json")
                .body(new JSONObject(machineGamingRest).toString())
                .when().post("/gaming");
        machineResponse.then()
                .statusCode(200);

        var newMachineJson = new JSONObject(machineResponse.body().asString());
        Assert.assertEquals(newMachineJson.getString("name"), "Nowa Maszynka");
        Assert.assertEquals(newMachineJson.getInt("cores"), 1);
        Assert.assertEquals(newMachineJson.getInt("ramSize"), 1024);
        Assert.assertEquals(newMachineJson.getInt("hddSize"), 128);
        Assert.assertEquals(newMachineJson.getInt("gpuPower"), 3);
        Assert.assertEquals(newMachineJson.getInt("gpuVram"), 5);

        machinesResponse = RestAssured.given()
                .when()
                .get();
        machinesResponse.then()
                .contentType("application/json")
                .statusCode(200);

        machinesJsonArray = new JSONArray(machinesResponse.body().asString());
        int machinesLengthAfter = machinesJsonArray.length();
        Assert.assertEquals(machinesLengthAfter, machinesLengthBefore + 1);
    }

    @Test
    public void insertMachineWorkstationTest() {
        var machinesResponse = RestAssured.given()
                .when()
                .get();
        machinesResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var machinesJsonArray = new JSONArray(machinesResponse.body().asString());
        int machinesLengthBefore = machinesJsonArray.length();

        MachineWorkstation machineWorkstation = new MachineWorkstation("Nowa Maszynka", 1, 1024, 128, 128, 1, true);

        var machineResponse = RestAssured.given()
                .contentType("application/json")
                .body(new JSONObject(machineWorkstation).toString())
                .when().post("/workstation");
        machineResponse.then()
                .statusCode(200);

        var newMachineJson = new JSONObject(machineResponse.body().asString());
        Assert.assertEquals(newMachineJson.getString("name"), "Nowa Maszynka");
        Assert.assertEquals(newMachineJson.getInt("cores"), 1);
        Assert.assertEquals(newMachineJson.getInt("ramSize"), 1024);
        Assert.assertEquals(newMachineJson.getInt("hddSize"), 128);
        Assert.assertEquals(newMachineJson.getInt("ssdSize"), 128);
        Assert.assertEquals(newMachineJson.getInt("netCards"), 1);
        Assert.assertTrue(newMachineJson.getBoolean("raidSupport"));

        machinesResponse = RestAssured.given()
                .when()
                .get();
        machinesResponse.then()
                .contentType("application/json")
                .statusCode(200);

        machinesJsonArray = new JSONArray(machinesResponse.body().asString());
        int machinesLengthAfter = machinesJsonArray.length();
        Assert.assertEquals(machinesLengthAfter, machinesLengthBefore + 1);
    }

    @Test
    public void modifyMachineGamingTest() {
        String machineName = "Predator";

        var machineResponse = RestAssured.given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        machineResponse.then()
                .contentType("application/json")
                .statusCode(200);
        var machineJson = new JSONObject(machineResponse.body().asString());
        String machineId = machineJson.getString("id");

        var updatedMachine = new MachineGamingRest(
                machineJson.getString("name"),
                machineJson.getInt("cores") + 1,
                machineJson.getInt("ramSize"),
                machineJson.getInt("hddSize"),
                machineJson.getInt("gpuPower"),
                machineJson.getInt("gpuVram"));

        updatedMachine.setId(UUID.fromString(machineId));
        var updatedMachineJSON = new JSONObject(updatedMachine);

        machineResponse = RestAssured.given()
                .contentType("application/json")
                .body(updatedMachineJSON.toString())
                .when()
                .put("/gaming/" + machineId);
        machineResponse.then()
                .statusCode(200);

        machineResponse = RestAssured.given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        machineResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var machineUpdatedJson = new JSONObject(machineResponse.body().asString());
        Assert.assertEquals(machineUpdatedJson.getInt("cores"), machineJson.getInt("cores") + 1);
    }

    @Test
    public void modifyMachineWorkstationTest() {
        String machineName = "Developex";

        var machineResponse = RestAssured.given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        machineResponse.then()
                .contentType("application/json")
                .statusCode(200);
        var machineJson = new JSONObject(machineResponse.body().asString());
        String machineId = machineJson.getString("id");

        var updatedMachine = new MachineWorkstationRest(
                machineJson.getString("name"),
                machineJson.getInt("cores") + 1,
                machineJson.getInt("ramSize"),
                machineJson.getInt("hddSize"),
                machineJson.getInt("ssdSize"),
                machineJson.getInt("netCards"),
                machineJson.getBoolean("raidSupport"));

        updatedMachine.setId(UUID.fromString(machineId));
        var updatedMachineJSON = new JSONObject(updatedMachine);

        machineResponse = RestAssured.given()
                .contentType("application/json")
                .body(updatedMachineJSON.toString())
                .when()
                .put("/workstation/" + machineId);
        machineResponse.then()
                .statusCode(200);

        machineResponse = RestAssured.given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        machineResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var machineUpdatedJson = new JSONObject(machineResponse.body().asString());
        Assert.assertEquals(machineUpdatedJson.getInt("cores"), machineJson.getInt("cores") + 1);
    }

    @Test
    public void removeMachineByIdTest() {
        var machinesResponse = RestAssured.given()
                .when()
                .get();
        machinesResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var machinesJsonArray = new JSONArray(machinesResponse.body().asString());
        int machinesLengthBefore = machinesJsonArray.length();

        String machineName = "Developex";

        machinesResponse = RestAssured.given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        machinesResponse.then()
                .contentType("application/json")
                .statusCode(200);

        var machineJson = new JSONObject(machinesResponse.body().asString());
        String machineId = machineJson.getString("id");

        machinesResponse = RestAssured.given()
                .when()
                .delete("/" + machineId);
        machinesResponse.then()
                .statusCode(200);

        machinesResponse = RestAssured.given()
                .when()
                .get();
        machinesResponse.then()
                .contentType("application/json")
                .statusCode(200);

        machinesJsonArray = new JSONArray(machinesResponse.body().asString());
        int machinesLengthAfter = machinesJsonArray.length();
        Assert.assertEquals(machinesLengthBefore, machinesLengthAfter + 1);
    }
}
