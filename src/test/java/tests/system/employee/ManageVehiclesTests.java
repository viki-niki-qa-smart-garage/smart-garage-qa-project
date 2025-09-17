package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import io.restassured.internal.common.assertion.Assertion;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ManageVehiclesTests extends SmartGarageBaseWebTest {
    @BeforeEach
    public void setUp() {
        loginPage.login(TestData.EMPLOYEE_USERNAME.getValue(), TestData.EMPLOYEE_PASSWORD.getValue());
        homePage.clickAdminPanelButton();
        adminPanelPage.clickClientCarsContainer();
    }

    @Test
    public void successfullyClientCarCreation_when_loginWithValidCredentials() {
        String[] regionPrefixes = { "A","B","CH","Y","TX","H","CC","PP","T","P",
                "BT","EB","CT","X","K","CM","PB","OB","EH","PA",
                "E","KH","PK","CA","C","CB","CO","BP","M","BH" };
        String bgLetters = "ABEKMHOPCTYX";
        String randomPlate =
                regionPrefixes[ThreadLocalRandom.current().nextInt(regionPrefixes.length)] +
                        RandomStringUtils.randomNumeric(4) +
                        RandomStringUtils.random(2, bgLetters);
        String randomVin = RandomStringUtils.randomAlphabetic(10).toUpperCase() + RandomStringUtils.randomNumeric(7);
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
        // da premestq assertions v carspage
    }

    @Test
    public void updateExistingVehicleDetails(){
        String[] regionPrefixes = { "A","B","CH","Y","TX","H","CC","PP","T","P",
                "BT","EB","CT","X","K","CM","PB","OB","EH","PA",
                "E","KH","PK","CA","C","CB","CO","BP","M","BH" };
        String bgLetters = "ABEKMHOPCTYX";
        String randomPlate =
                regionPrefixes[ThreadLocalRandom.current().nextInt(regionPrefixes.length)] +
                        RandomStringUtils.randomNumeric(4) +
                        RandomStringUtils.random(2, bgLetters);
        String randomVin = RandomStringUtils.randomAlphabetic(10).toUpperCase() + RandomStringUtils.randomNumeric(7);
        clientCarsPage.updateCarDetails(randomVin, randomPlate);
        List<WebElement> cars = clientCarsPage.getCarList();

        String vin = clientCarsPage.getCarVin(cars.get(0));
        String plate = clientCarsPage.getCarPlate(cars.get(0));

        Assertions.assertEquals(randomVin, vin, "VIN not updated");
        Assertions.assertEquals(randomPlate, plate, "License Plate not updated");
    }

    @Test
    public void filterVehiclesByOwnerFirstName() {
        clientCarsPage.filterByFirstName("alex");
        WebElement owner = clientCarsPage.getOwner();

        Assertions.assertTrue(
                owner.getText().toLowerCase().contains("alex"),
                "Owner name does not match the expected result. Actual: " + owner.getText()
        );
    }
}
