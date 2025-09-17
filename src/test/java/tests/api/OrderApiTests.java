package tests.api;

import com.api.Endpoints;
import core.BaseApiTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

public class OrderApiTests extends BaseApiTest {

    @Test
    void getAllOrders() {

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .get(Endpoints.GET_ALL_ORDERS)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();


        List<?> items = response.jsonPath().getList("$");
        Assertions.assertNotNull(items, "Response is not an array");
        Assertions.assertFalse(items.isEmpty(), "Expected at least one order");


        int firstOrderId = response.jsonPath().getInt("[0].id");
        String clientCar = response.jsonPath().getString("[0].clientCar");
        String firstStatus = response.jsonPath().getString("[0].status");
        String firstOrderDate = response.jsonPath().getString("[0].orderDate");


        Assertions.assertAll("first order details",
                () -> Assertions.assertNotNull(firstOrderId, "Order id must not be null"),
                () -> Assertions.assertNotNull(clientCar, "ClientCar must not be null"),
                () -> Assertions.assertNotNull(firstStatus, "Order status must not be null"),
                () -> Assertions.assertNotNull(firstOrderDate, "Order date must not be null")
        );
    }

    @Test
    void getOrderById() {
        int orderId = 1;

        Response response =
                given()
                        .contentType("application/json")
                        .when()
                        .get(Endpoints.GET_ORDER_BY_ID, orderId)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();


        int id = response.jsonPath().getInt("id");
        Map<String, Object> clientCar = response.jsonPath().getMap("clientCar");
        String status = response.jsonPath().getString("status");
        String orderDate = response.jsonPath().getString("orderDate");

        Assertions.assertAll("order details",
                () -> Assertions.assertEquals(orderId, id, "Order id mismatch"),
                () -> Assertions.assertNotNull(clientCar, "ClientCar must not be null"),
                () -> Assertions.assertNotNull(status, "Order status must not be null"),
                () -> Assertions.assertNotNull(orderDate, "Order date must not be null")

        );
    }

    @Test
    void updateOrderStatus() {
        int orderId = 1;
        String newStatus = "IN_PROGRESS";

        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("orderId", orderId)
                        .queryParam("newStatus", newStatus)
                        .when()
                        .put(Endpoints.UPDATE_ORDER_STATUS)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        String body = response.asString().trim();

        Assertions.assertAll("update order status response",
                () -> Assertions.assertTrue(
                        body.equals("1") || body.isEmpty(),
                        "Expected body to be \"1\" or empty, but was: \"" + body + "\""
                )
        );
    }

    @Test
    void getOrderTotalPrice() {
        int orderId = 1;
        String currency = "BGN"; // or "EUR"

        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("orderId", orderId)
                        .queryParam("currency", currency)
                        .when()
                        .get(Endpoints.GET_ORDER_TOTAL_PRICE)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        double total = response.as(Double.class);

        Assertions.assertAll("total price validation",
                () -> Assertions.assertTrue(total >= 0, "Total price must be non-negative")
        );
    }

    @Test
    void downloadOrderPdf() {
        int orderId = 1;
        String currency = "BGN"; // or "EUR"

        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("orderId", orderId)
                        .queryParam("currency", currency)
                        .when()
                        .get(Endpoints.GET_ORDER_DOWNLOAD_PDF)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        String contentType = response.getHeader("Content-Type");
        String contentDisposition = response.getHeader("Content-Disposition");
        byte[] pdfBytes = response.asByteArray();

        Assertions.assertAll("basic pdf checks",
                () -> Assertions.assertNotNull(contentType, "Content-Type missing"),
                () -> Assertions.assertTrue(contentType.toLowerCase().contains("application/pdf"),
                        "Content-Type must be application/pdf"),

                () -> Assertions.assertNotNull(contentDisposition, "Content-Disposition missing"),
                () -> Assertions.assertTrue(contentDisposition.matches(".*filename=.*\\.pdf.*"),
                        "Content-Disposition must contain a .pdf filename"),

                () -> Assertions.assertTrue(pdfBytes.length > 0, "PDF content must not be empty")
        );
    }


    @Test
    void getUserOrders() {
        int userId = 1;

        Response response =
                given()
                        .contentType("application/json")
                        .pathParam("userId", userId)
                        .when()
                        .get(Endpoints.GET_USER_ORDERS)
                        .then()
                        .log().all()
                        .statusCode(SC_OK)
                        .extract().response();

        List<?> items = response.jsonPath().getList("$");
        Assertions.assertNotNull(items, "Response is not an array");
        Assertions.assertFalse(items.isEmpty(), "Expected at least one order for the user");

        int orderId = response.jsonPath().getInt("[0].id");
        String status = response.jsonPath().getString("[0].status");
        String orderDate = response.jsonPath().getString("[0].orderDate");

        Integer clientCarId = response.jsonPath().getInt("[0].clientCar.id");
        String vin = response.jsonPath().getString("[0].clientCar.vin");
        String licensePlate = response.jsonPath().getString("[0].clientCar.licensePlate");

        Integer vehicleId = response.jsonPath().getInt("[0].clientCar.vehicle.id");
        String brandName = response.jsonPath().getString("[0].clientCar.vehicle.brand.name");
        String modelName = response.jsonPath().getString("[0].clientCar.vehicle.model.name");
        Integer yearValue = response.jsonPath().getInt("[0].clientCar.vehicle.year.year");
        String engineTypeName = response.jsonPath().getString("[0].clientCar.vehicle.engineType.name");

        Assertions.assertAll("first user order details",
                () -> Assertions.assertNotNull(orderId, "Order id must not be null"),
                () -> Assertions.assertNotNull(status, "Order status must not be null"),
                () -> Assertions.assertNotNull(orderDate, "Order date must not be null"),

                () -> Assertions.assertNotNull(clientCarId, "ClientCar id must not be null"),
                () -> Assertions.assertNotNull(vin, "VIN must not be null"),
                () -> Assertions.assertNotNull(licensePlate, "License plate must not be null"),

                () -> Assertions.assertNotNull(vehicleId, "Vehicle id must not be null"),
                () -> Assertions.assertNotNull(brandName, "Brand name must not be null"),
                () -> Assertions.assertNotNull(modelName, "Model name must not be null"),
                () -> Assertions.assertNotNull(yearValue, "Year must not be null"),
                () -> Assertions.assertNotNull(engineTypeName, "Engine type must not be null")
        );
    }
}







