package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ManageServicesTests extends SmartGarageBaseWebTest {
    @BeforeEach
    public void setUp() {
        loginPage.login(TestData.EMPLOYEE_USERNAME.getValue(), TestData.EMPLOYEE_PASSWORD.getValue());
        homePage.clickServicesButton();
    }

    @Test
    public void browseAllServices() {
        List<WebElement> numberOfServices = servicePage.getServicesList();

        Assertions.assertTrue(numberOfServices.size() > 0, "Expected at least 1 service in the list");
        Assertions.assertTrue(numberOfServices.get(0).isDisplayed(), "First service is not displayed");
    }

    @Test
    public void browseSpecificService() {
        servicePage.clickEngineDiagnosticsContainer();
        WebElement overview = servicePage.getServiceOverview();
        List<WebElement> servicesPrice = servicePage.getServicePriceTable();

        Assertions.assertTrue(overview.isDisplayed(), "Service Overview is not displayed");
        Assertions.assertTrue(servicesPrice.size() > 0, "Expected at least 1 service in the list");
        Assertions.assertNotNull(servicesPrice.get(0).getText(), "First service is not displayed");
    }

    @Test
    public void createAndDeleteService() {
        String randomServiceName = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        servicePage.clickEngineDiagnosticsContainer();
        String serviceId = servicePage.addService(randomServiceName, "130");
        servicePage.assertLastCreatedService(randomServiceName, "130");
        servicePage.deleteService(serviceId);
    }

    @Test
    public void updateServicePrice_when_validInput() {
        servicePage.clickEngineDiagnosticsContainer();
        servicePage.updateFirstServicePrice("130");

        String uiText = servicePage.getUpdatedPrice().getText();
        int actualInt = servicePage.integerPart(uiText);
        Assertions.assertEquals(130, actualInt);
    }
}