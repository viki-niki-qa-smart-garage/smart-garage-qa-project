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
        loginPage.navigate();
        loginPage.login(TestData.EMPLOYEE_USERNAME_NIKI.getValue(), TestData.EMPLOYEE_PASSWORD_NIKI.getValue());
    }

    @Test
    public void successfullyClientCarCreation_when_loginWithValidCredentials() {
        homePage.clickAdminPanelButton();
        adminPanelPage.clickClientCarsContainer();
        clientCarsPage.addClientCar("WAUZZZ8P4AA000099", "A1112BC", "testUser", "A3", "1.4 TSI", "1999");

        clientCarsPage.clickThirdPageButton();

        WebElement vin = clientCarsPage.getVin();
        Assertions.assertEquals("WAUZZZ8P4AA000100", vin.getText(), "Client Car is not created");
        //da pitam viktor
    }

    @Test
    public void browseAllVehiclesLinkedToCustomers() {
        homePage.clickAdminPanelButton();
        adminPanelPage.clickClientCarsContainer();
        List<WebElement> cars = clientCarsPage.getCarList();

        Assertions.assertTrue(cars.size() > 0, "Expected at least 1 car on the page.");
    }

    @Test
    public void updateExistingVehicleDetails(){
        homePage.clickAdminPanelButton();
        adminPanelPage.clickClientCarsContainer();
        clientCarsPage.updateCarDetails("WAUZZZ8P4AA000077", "EB1234KK");

        List<WebElement> cars = clientCarsPage.getCarList();

        String vin = clientCarsPage.getCarVin(cars.get(0));
        String plate = clientCarsPage.getCarPlate(cars.get(0));

        Assertions.assertEquals("WAUZZZ8P4AA000077", vin, "VIN not updated");
        Assertions.assertEquals("EB1234KK", plate, "License Plate not updated");
    }

    @Test
    public void filterVehiclesByOwnerFirstName() {
        homePage.clickAdminPanelButton();
        adminPanelPage.clickClientCarsContainer();
        clientCarsPage.filterByFirstName("Alex");
        WebElement owner = clientCarsPage.getOwner();

        Assertions.assertTrue(
                owner.getText().toLowerCase().contains("gosho"),
                "Owner name does not match the expected result. Actual: " + owner.getText()
        );
    }
}
