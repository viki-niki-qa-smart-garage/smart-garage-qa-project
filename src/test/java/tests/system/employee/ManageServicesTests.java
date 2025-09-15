package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ManageServicesTests extends SmartGarageBaseWebTest {
    @BeforeEach
    public void setUp() {
        loginPage.navigate();
        loginPage.login(TestData.EMPLOYEE_USERNAME_NIKI.getValue(), TestData.EMPLOYEE_PASSWORD_NIKI.getValue());
    }

    @Test
    public void browseAllServices() {
        homePage.clickServicesButton();
        List<WebElement> numberOfServices = servicePage.getServicesList();

        Assertions.assertTrue(numberOfServices.size() > 0, "Expected at least 1 service in the list");
        Assertions.assertTrue(numberOfServices.get(0).isDisplayed(), "First service is not displayed");
    }

    @Test
    public void browseSpecificService() {
        homePage.clickServicesButton();
        servicePage.clickEngineDiagnosticsContainer();
        WebElement overview = servicePage.getServiceOverview();
        List<WebElement> servicesPrice = servicePage.getServicePriceTable();

        Assertions.assertTrue(overview.isDisplayed(), "Service Overview is not displayed");
        Assertions.assertTrue(servicesPrice.size() > 0, "Expected at least 1 service in the list");
        Assertions.assertNotNull(servicesPrice.get(0).getText(), "First service is not displayed");
    }

    @Test
    public void createService_when_validInput() {
        homePage.clickServicesButton();
        servicePage.clickEngineDiagnosticsContainer();
        servicePage.addService("Engine Fault Fixing", "130");
        servicePage.assertLastCreatedService("Engine Fault Fixing", "130");
    }

    @Test
    public void deleteService() {
        homePage.clickServicesButton();
        servicePage.clickEngineDiagnosticsContainer();

        servicePage.createThenDeleteService("Engine Fault Fixing", "130");
    }

    @Test
    public void updateServicePrice_when_validInput() {
        homePage.clickServicesButton();
        servicePage.clickEngineDiagnosticsContainer();
        servicePage.updateFirstServicePrice("110");

        WebElement updatedPrice = servicePage.getUpdatedPrice();
        Assertions.assertTrue(updatedPrice.getText().contains("110"), "Expected updatedPrice to be displayed");
    }
}
