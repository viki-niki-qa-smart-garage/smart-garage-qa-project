package tests.api;

import com.api.Endpoints;
import com.api.User;
import core.BaseApiTest;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
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
        final int user = 15;
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
        String randomFirstName = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        String randomLastName = RandomStringUtils.randomAlphabetic(15).toLowerCase();
        String randomEmail = RandomStringUtils.randomAlphabetic(10).toLowerCase() + "@abv.bg";
        String randomNumber = RandomStringUtils.randomNumeric(10);
        user = new User(randomFirstName, randomLastName, randomEmail, randomNumber);
        final int userID = 8;
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
                () -> assertEquals(randomFirstName, firstName, "First name was not updated"),
                () -> assertEquals(randomLastName, lastName, "Last name was not updated"),
                () -> assertEquals(randomEmail, email, "Email was not updated"),
                () -> assertEquals(randomNumber, phoneNumber, "Phone number was not updated")
        );
    }

    @Test
    void deleteUser() {
        final int userId = 37;
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
            Assertions.assertEquals("User with id 37 not found.", message, "User is not deleted");
        }
    }

    @Test
    void createCustomer() {
        String randomUsername = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        String randomEmail = RandomStringUtils.randomAlphabetic(10).toLowerCase() + "@abv.bg";
        String randomNumber = RandomStringUtils.randomNumeric(10);
        user = new User(randomUsername, "Anna", "Petrova", randomEmail, randomNumber);
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

        Assertions.assertEquals(randomUsername, username, "Username does not match");
        Assertions.assertEquals(randomEmail, email, "Email does not match");
        Assertions.assertEquals(randomNumber, phoneNumber, "Phone number does not match");

        String password = response.jsonPath().getString("password");
        assertNotNull(password, "Password should be generated");
    }
}
