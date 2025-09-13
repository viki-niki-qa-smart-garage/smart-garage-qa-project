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
        adminPanelPage.clickClientCarsWindow();
        clientCarsPage.addClientCar("WAUZZZ8P4AA000099", "A1112BC", "testUser", "A3", "1.4 TSI", "1999");

        clientCarsPage.clickThirdPageButton();

        WebElement vin = clientCarsPage.getVin();
        Assertions.assertEquals("WAUZZZ8P4AA000100", vin.getText(), "Client Car is not created");
        //da pitam viktor
    }

    @Test
    public void browseAllVehiclesLinkedToCustomers() {
        homePage.clickAdminPanelButton();
        adminPanelPage.clickClientCarsWindow();
        List<WebElement> cars = clientCarsPage.getCarList();

        Assertions.assertTrue(cars.size() > 0, "Expected at least 1 car on the page.");
    }

    @Test
    public void updateExistingVehicleDetails(){
        homePage.clickAdminPanelButton();
        adminPanelPage.clickClientCarsWindow();
        clientCarsPage.updateCarDetails("WAUZZZ8P4AA000099", "EB4321KK");

    }
}
