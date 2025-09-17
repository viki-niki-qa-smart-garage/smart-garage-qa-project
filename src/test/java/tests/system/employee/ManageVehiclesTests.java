package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import io.restassured.internal.common.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ManageVehiclesTests extends SmartGarageBaseWebTest {
    @BeforeEach
    public void setUp() {
        loginPage.login(TestData.EMPLOYEE_USERNAME.getValue(), TestData.EMPLOYEE_PASSWORD.getValue());
        homePage.clickAdminPanelButton();
        adminPanelPage.clickClientCarsContainer();
    }

    @Test
    public void successfullyClientCarCreation_when_loginWithValidCredentials() {
        clientCarsPage.addClientCar("WAUZZZ8P4AA000104", "A0332BC", "diana_smith", "A3", "1.4 TSI", "1999");
        clientCarsPage.navigateToLastPage();

        WebElement vin = clientCarsPage.getVin();
        Assertions.assertEquals("WAUZZZ8P4AA000104", vin.getText(), "Client Car is not created");
        clientCarsPage.deleteCreatedClientCar();
    }

    @Test
    public void browseAllVehiclesLinkedToCustomers() {
        List<WebElement> cars = clientCarsPage.getCarList();

        Assertions.assertTrue(cars.size() > 0, "Expected at least 1 car on the page.");
        // da premestq assertions v carspage
    }

    @Test
    public void updateExistingVehicleDetails(){
        clientCarsPage.updateCarDetails("WAUZZZ8P4AA000078", "EB8234KK");
        List<WebElement> cars = clientCarsPage.getCarList();

        String vin = clientCarsPage.getCarVin(cars.get(0));
        String plate = clientCarsPage.getCarPlate(cars.get(0));

        Assertions.assertEquals("WAUZZZ8P4AA000078", vin, "VIN not updated");
        Assertions.assertEquals("EB8234KK", plate, "License Plate not updated");
    }

    @Test
    public void filterVehiclesByOwnerFirstName() {
        clientCarsPage.filterByFirstName("Alex");
        WebElement owner = clientCarsPage.getOwner();

        Assertions.assertTrue(
                owner.getText().toLowerCase().contains("gosho"),
                "Owner name does not match the expected result. Actual: " + owner.getText()
        );
    }
}
