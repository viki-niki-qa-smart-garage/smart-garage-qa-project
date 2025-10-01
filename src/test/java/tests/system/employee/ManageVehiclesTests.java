package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import utils.TestDataGeneration;
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
        String randomVin = TestDataGeneration.randomVin();
        String randomPlate = TestDataGeneration.randomPlate();
        clientCarsPage.addClientCar(randomVin, randomPlate, "diana_smith", "A3", "1.4 TSI", "1999");
        clientCarsPage.navigateToLastPage();

        WebElement vin = clientCarsPage.getVin();
        Assertions.assertEquals(randomVin, vin.getText(), "Client Car is not created");
        clientCarsPage.deleteCreatedClientCar();
    }

    @Test
    public void browseAllVehiclesLinkedToCustomers() {
        List<WebElement> cars = clientCarsPage.getCarList();
        Assertions.assertTrue(cars.size() > 0, "Expected at least 1 car on the page.");
    }

    @Test
    public void updateExistingVehicleDetails(){
        String randomVin = TestDataGeneration.randomVin();
        String randomPlate = TestDataGeneration.randomPlate();
        clientCarsPage.updateCarDetails(randomVin, randomPlate);
        List<WebElement> cars = clientCarsPage.getCarList();

        String vin = clientCarsPage.getCarVin(cars.get(0));
        String plate = clientCarsPage.getCarPlate(cars.get(0));

        Assertions.assertEquals(randomVin, vin, "VIN not updated");
        Assertions.assertEquals(randomPlate, plate, "License Plate not updated");
    }

    @Test
    public void filterVehiclesByOwnerFirstName() {
        String name = "alex";
        clientCarsPage.filterByFirstName(name);
        WebElement owner = clientCarsPage.getOwner();

        Assertions.assertTrue(
                owner.getText().toLowerCase().contains(name),
                "Owner name does not match the expected result. Actual: " + owner.getText()
        );
    }
}