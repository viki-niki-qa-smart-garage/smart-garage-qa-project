package tests.api;

import com.api.Endpoints;
import core.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class UserApiSearchTests extends BaseApiTest {

    @Test
    void getAllUsersByUsernameKeyword() {
        String usernameKeyword = "han";
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("username", usernameKeyword)
                        .get(Endpoints.GET_USERS_BY_USERNAME)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        List<String> usernames = response.jsonPath().getList("username");
        for (String username : usernames) {
            Assertions.assertTrue(username.toLowerCase().contains(username.toLowerCase()),
                    "Username '" + username + "' does not contain the keyword '" + usernameKeyword + "'");
        }
    }

    @Test
    void getAllUsersByEmailKeyword() {
        String emailKeyword = "abv";
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("email", emailKeyword)
                        .get(Endpoints.GET_USERS_BY_EMAIL)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        List<String> emails = response.jsonPath().getList("email");

        for (String email : emails) {
            Assertions.assertTrue(email.toLowerCase().contains(email.toLowerCase()),
                    "Username '" + email + "' does not contain the keyword '" + emailKeyword + "'");
        }
    }

    @Test
    void getAllUsersByPartialPhoneNumber() {
        String phoneKeyword = "005";
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("phoneNumber", phoneKeyword)
                        .get(Endpoints.GET_USERS_BY_PHONE_NUMBER)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        List<String> phoneNumbers = response.jsonPath().getList("phoneNumber");

        for (String phoneNumber : phoneNumbers) {
            Assertions.assertTrue(
                    phoneNumber.toLowerCase().contains(phoneKeyword.toLowerCase()),
                    "Phone number '" + phoneNumber + "' does not contain the keyword '" + phoneKeyword + "'"
            );
        }
    }

    @Test
    void getAllUsersByBrand() {
        String brand = "Volkswagen";
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("brand", brand)
                        .get(Endpoints.GET_USERS_BY_BRAND)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();
    }

    @Test
    void verifyUserBrandName() {
        String brand = "Volkswagen";
        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .pathParam("vehicleId", 19)
                        .get(Endpoints.GET_VEHICLE_BY_ID)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();
        String actualBrandName = response.jsonPath().getString("brand.name");
        Assertions.assertEquals(brand, actualBrandName, "Brand name does not match the expected value");
    }
}
