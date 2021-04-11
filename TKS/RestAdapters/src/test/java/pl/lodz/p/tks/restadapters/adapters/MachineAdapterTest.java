package pl.lodz.p.tks.restadapters.adapters;

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
import pl.lodz.p.tks.restadapters.data.machine.MachineGamingRest;
import pl.lodz.p.tks.restadapters.data.machine.MachineWorkstationRest;
import pl.lodz.p.tks.view.domainmodel.model.machine.MachineWorkstation;

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
                    .withFileFromPath("RestAdapters.war", Path.of("target", "RestAdapters-1.0-SNAPSHOT.war")))
            .withExposedPorts(8080, 4848)
            .waitingFor(Wait.forHttp("/VMRental/api/machines").forPort(8080).forStatusCode(200));


    @BeforeClass
    public static void setupClass() {
        app.start();

        RestAssured.baseURI = "http://localhost:" + app.getMappedPort(8080) + "/VMRental/api/machines";
    }

    @Test
    public void getMachinesTest() {
        var res = given()
                .when()
                .get();
        res.then()
                .contentType("application/json")
                .statusCode(200);

        var jsonArray = new JSONArray(res.body().asString());
        Assert.assertEquals(jsonArray.length(), 4);
    }

    @Test
    public void getMachineByIdTest() {
        String name = "Predator";

        var res = given()
                .when()
                .queryParam("machinename", name)
                .get("/machine");
        res.then()
                .contentType("application/json")
                .statusCode(200);
        var jsonObj = new JSONObject(res.body().asString());

        String machineId = jsonObj.getString("id");

        var res2 = given()
                .when()
                .get("/" + machineId);
        res2.then()
                .contentType("application/json")
                .statusCode(200);

        var jsonObj2 = new JSONObject(res.body().asString());

        Assert.assertEquals(jsonObj.getString("name"), jsonObj2.getString("name"));
    }

    @Test
    public void getMachineByNameTest() {
        String machineName = "Predator";

        var res = given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        res.then()
                .contentType("application/json")
                .statusCode(200);

        var jsonObj = new JSONObject(res.body().asString());
        Assert.assertEquals(jsonObj.get("name"), "Predator");
    }

    @Test
    public void insertMachineGamingTest() {
        var res = given()
                .when()
                .get();
        res.then()
                .contentType("application/json")
                .statusCode(200);

        var jsonArray = new JSONArray(res.body().asString());
        var jsonArrLenBefore = jsonArray.length();

        MachineGamingRest machineGamingRest = new MachineGamingRest("Nowa Maszynka", 1, 1024, 128, 3, 5);

        res = given()
                .contentType("application/json")
                .body(new JSONObject(machineGamingRest).toString())
                .when().post("/gaming");
        res.then()
                .statusCode(200);

        var jsonObj = new JSONObject(res.body().asString());
        Assert.assertEquals(jsonObj.getString("name"), "Nowa Maszynka");
        Assert.assertEquals(jsonObj.getInt("cores"), 1);
        Assert.assertEquals(jsonObj.getInt("ramSize"), 1024);
        Assert.assertEquals(jsonObj.getInt("hddSize"), 128);
        Assert.assertEquals(jsonObj.getInt("gpuPower"), 3);
        Assert.assertEquals(jsonObj.getInt("gpuVram"), 5);

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
    public void insertMachineWorkstationTest() {
        var res = given()
                .when()
                .get();
        res.then()
                .contentType("application/json")
                .statusCode(200);

        var jsonArray = new JSONArray(res.body().asString());
        var jsonArrLenBefore = jsonArray.length();

        MachineWorkstation machineWorkstation = new MachineWorkstation("Nowa Maszynka", 1, 1024, 128, 128, 1, true);

        res = given()
                .contentType("application/json")
                .body(new JSONObject(machineWorkstation).toString())
                .when().post("/workstation");
        res.then()
                .statusCode(200);

        var jsonObj = new JSONObject(res.body().asString());
        Assert.assertEquals(jsonObj.getString("name"), "Nowa Maszynka");
        Assert.assertEquals(jsonObj.getInt("cores"), 1);
        Assert.assertEquals(jsonObj.getInt("ramSize"), 1024);
        Assert.assertEquals(jsonObj.getInt("hddSize"), 128);
        Assert.assertEquals(jsonObj.getInt("ssdSize"), 128);
        Assert.assertEquals(jsonObj.getInt("netCards"), 1);
        Assert.assertTrue(jsonObj.getBoolean("raidSupport"));

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
    public void modifyMachineGamingTest() {
        String machineName = "Predator";

        var res = given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        res.then()
                .contentType("application/json")
                .statusCode(200);
        var jsonObj = new JSONObject(res.body().asString());
        String machineId = jsonObj.getString("id");

        var updatedMachine = new MachineGamingRest(jsonObj.getString("name"), jsonObj.getInt("cores") + 1, jsonObj.getInt("ramSize"), jsonObj.getInt("hddSize"), jsonObj.getInt("gpuPower"), jsonObj.getInt("gpuVram"));
        updatedMachine.setId(UUID.fromString(machineId));
        var updatedMachineJSON = new JSONObject(updatedMachine);

//        System.out.println(updatedMachineJSON.toString()); // DEBUG
        res = given()
                .contentType("application/json")
                .body(updatedMachineJSON.toString())
                .when()
                .put("/gaming/" + machineId);
        res.then()
                .statusCode(200);

        res = given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        res.then()
                .contentType("application/json")
                .statusCode(200);
        var jsonObjUpdated = new JSONObject(res.body().asString());

        Assert.assertEquals(jsonObjUpdated.getInt("cores"), jsonObj.getInt("cores") + 1);
    }

    @Test
    public void modifyMachineWorkstationTest() {
        String machineName = "Developex";

        var res = given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        res.then()
                .contentType("application/json")
                .statusCode(200);
        var jsonObj = new JSONObject(res.body().asString());
        String machineId = jsonObj.getString("id");

        var updatedMachine = new MachineWorkstationRest(jsonObj.getString("name"), jsonObj.getInt("cores") + 1, jsonObj.getInt("ramSize"), jsonObj.getInt("hddSize"), jsonObj.getInt("ssdSize"), jsonObj.getInt("netCards"),jsonObj.getBoolean("raidSupport"));
        updatedMachine.setId(UUID.fromString(machineId));
        var updatedMachineJSON = new JSONObject(updatedMachine);

//        System.out.println(updatedMachineJSON.toString()); // DEBUG
        res = given()
                .contentType("application/json")
                .body(updatedMachineJSON.toString())
                .when()
                .put("/workstation/" + machineId);
        res.then()
                .statusCode(200);

        res = given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        res.then()
                .contentType("application/json")
                .statusCode(200);
        var jsonObjUpdated = new JSONObject(res.body().asString());

        Assert.assertEquals(jsonObjUpdated.getInt("cores"), jsonObj.getInt("cores") + 1);
    }

    @Test
    public void removeMachineByIdTest() {
        var res = given()
                .when()
                .get();
        res.then()
                .contentType("application/json")
                .statusCode(200);

        var jsonArr = new JSONArray(res.body().asString());
        var lengthBefore = jsonArr.length();

        String machineName = "Developex";

        res = given()
                .when()
                .queryParam("machinename", machineName)
                .get("/machine");
        res.then()
                .contentType("application/json")
                .statusCode(200);
        var jsonObj = new JSONObject(res.body().asString());
        String machineId = jsonObj.getString("id");

        res = given()
                .when()
                .delete("/" + machineId);
        res.then()
                .statusCode(200);

        res = given()
                .when()
                .get();
        res.then()
                .contentType("application/json")
                .statusCode(200);

        jsonArr = new JSONArray(res.body().asString());
        var lengthAfter = jsonArr.length();

        Assert.assertEquals(lengthBefore, lengthAfter + 1);
    }
}
