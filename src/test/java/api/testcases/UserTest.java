package api.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.user;
import io.restassured.response.Response;

public class UserTest {

    Faker faker;
    user userPayload;
    public static final Logger logger = LogManager.getLogger(UserTest.class); // Initialize Logger here

    @BeforeClass
    public void generateFakeData() {

        faker = new Faker();
        userPayload = new user();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().emailAddress());
        userPayload.setPassword(faker.internet().password());
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger.info("User payload generated for testing: " + userPayload.getUsername());
    }

    @Test(priority = 1)
    public void testCreateUser() {
        Response response = userEndPoints.createUser(userPayload);

        // log response
        response.then().log().all();

        // validation
        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println(response.getStatusCode());
        logger.info("User created successfully with username: " + userPayload.getUsername());

    }

    @Test(priority = 2)
    public void testgetUser() {
        Response response = userEndPoints.getUser(this.userPayload.getUsername());

        System.out.println("Read User Data.");
        // log response
        response.then().log().all();

        // validation
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("Fetched user successfully with username: " + userPayload.getUsername());
    }

    @Test(priority = 3)
    public void testUpdateUser() {
        Response response = userEndPoints.updateUser(this.userPayload.getUsername(), userPayload);

        // log response
        response.then().log().all();

        // validation
        Assert.assertEquals(response.getStatusCode(), 200);
        // Read User data to check if first name is updated

        System.out.println(this.userPayload.getUsername());
        Response responsePostUpdate = userEndPoints.getUser(this.userPayload.getUsername());

        System.out.println("After Update User Data.");

        responsePostUpdate.then().log().all();
        logger.info("User updated successfully with username: " + userPayload.getUsername());

    }

    @Test(priority = 4)
    public void testdeletUser() {
        Response response = userEndPoints.deleteUser(this.userPayload.getUsername());

        System.out.println("Read User Data.");
        // log response
        response.then().log().all();

        // validation
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("User deleted successfully with username: " + userPayload.getUsername());
    }
}
