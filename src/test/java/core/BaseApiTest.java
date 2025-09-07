package core;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static com.api.Endpoints.BASE_URL;

public class BaseApiTest {

    @BeforeAll
    static void setup() {

        RestAssured.baseURI = BASE_URL;

 }
}
