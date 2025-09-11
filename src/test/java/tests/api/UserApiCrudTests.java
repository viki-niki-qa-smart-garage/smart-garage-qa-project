package tests.api;

import com.api.Endpoints;
import com.api.User;
import core.BaseApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserApiCrudTests extends BaseApiTest {
    User user;
    @BeforeEach
    void setUp() {
        RestAssured.authentication = RestAssured.preemptive().basic("test", "Testing1@");
    }

    @Test
    void getAllUsers() {
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .get(Endpoints.GET_ALL_USERS)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        int firstId = response.jsonPath().getInt("[0].id");
        String username = response.jsonPath().getString("username");
        String firstName = response.jsonPath().getString("firstName");
        String lastName = response.jsonPath().getString("lastName");
        String email = response.jsonPath().getString("email");
        String phoneNumber = response.jsonPath().getString("phoneNumber");

        Assertions.assertAll("first user details",
                () -> Assertions.assertTrue(firstId == 1, "id must be 1"),
                () -> assertNotNull(username, "Incorrect username"),
                () -> assertNotNull(firstName, "Incorrect first name"),
                () -> assertNotNull(lastName, "Incorrect last name"),
                () -> assertNotNull(email, "Incorrect email"),
                () -> assertNotNull(phoneNumber, "Incorrect phone number")
        );
    }

    @Test
    void getConcreteUser() {
        final int user = 30;
        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("id", user)
                        .when()
                        .get(Endpoints.GET_USER_BY_ID)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        int userId = response.jsonPath().getInt("id");
        String username = response.jsonPath().getString("username");
        String firstName = response.jsonPath().getString("firstName");
        String lastName = response.jsonPath().getString("lastName");
        String email = response.jsonPath().getString("email");
        String phoneNumber = response.jsonPath().getString("phoneNumber");

        Assertions.assertEquals(user, userId, "Incorrect user id");
        Assertions.assertAll("first user details",
                () -> assertNotNull(username, "Incorrect username"),
                () -> assertNotNull(firstName, "Incorrect first name"),
                () -> assertNotNull(lastName, "Incorrect last name"),
                () -> assertNotNull(email, "Incorrect email"),
                () -> assertNotNull(phoneNumber, "Incorrect phone number")
        );
    }

    @Test
    void updateUser() {
        user = new User("Tom", "Smith", "tom@abv.bg", "0877998844");
        final int userID = 30;
        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("id", userID)
                        .body(user)
                        .when()
                        .put(Endpoints.UPDATE_USER)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();
        String firstName = response.jsonPath().getString("firstName");
        String lastName = response.jsonPath().getString("lastName");
        String email = response.jsonPath().getString("email");
        String phoneNumber = response.jsonPath().getString("phoneNumber");

        Assertions.assertAll("user details changed",
                () -> assertEquals("Tom", firstName, "First name was not updated"),
                () -> assertEquals("Smith", lastName, "Last name was not updated"),
                () -> assertEquals("tom@abv.bg", email, "Email was not updated"),
                () -> assertEquals("0877998844", phoneNumber, "Phone number was not updated")
        );
    }

    @Test
    void deleteUser() {
        final int userId = 60;
        Response response =
                given()
                        .pathParam("id", userId)
                        .when()
                        .delete(Endpoints.DELETE_USER)
                        .then()
                        .log().all()
                        .statusCode(anyOf(is(SC_OK),is(SC_NOT_FOUND)))
                        .extract().response();

        int statusCode = response.statusCode();
        if (statusCode == SC_OK) {
            String message = response.asString();
            Assertions.assertEquals("User is deleted successfully.", message, "User is not deleted");
        } else if (statusCode == SC_NOT_FOUND) {
            String message = response.jsonPath().getString("message");
            Assertions.assertEquals("User with id 60 not found.", message, "User is not deleted");
        }
    }

    @Test
    void createCustomer() {
        user = new User("afgd123", "Anna", "Petrova", "anafgaab@abv.bg", "8008532211");
        Response response =
                given()
                        .contentType("application/json")
                        .body(user)
                        .when()
                        .post(Endpoints.CREATE_CUSTOMER)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        String username = response.jsonPath().getString("username");
        String email = response.jsonPath().getString("email");
        String phoneNumber = response.jsonPath().getString("phoneNumber");

        Assertions.assertEquals("afgd123", username, "Username does not match");
        Assertions.assertEquals("anafgaab@abv.bg", email, "Email does not match");
        Assertions.assertEquals("8888532211", phoneNumber, "Phone number does not match");

        String password = response.jsonPath().getString("password");
        assertNotNull(password, "Password should be generated");
    }
}
