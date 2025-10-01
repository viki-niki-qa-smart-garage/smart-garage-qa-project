package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import utils.TestDataGeneration;

import java.util.List;

public class ManageServicesTests extends SmartGarageBaseWebTest {
    @BeforeEach
    public void setUp() {
        loginPage.login(TestData.EMPLOYEE_USERNAME.getValue(), TestData.EMPLOYEE_PASSWORD.getValue());
        homePage.clickServicesButton();
    }

    @Test
    public void browseAllServices() {
        servicePage.assertAllServicesInfo();
    }

    @Test
    public void browseSpecificService() {
        servicePage.clickEngineDiagnosticsContainer();
        servicePage.assertSpecificServiceInfo();
    }

    @Test
    public void createAndDeleteService() {
        String randomServiceName = TestDataGeneration.randomServiceName();
        String randomPrice = TestDataGeneration.randomPrice();
        servicePage.clickEngineDiagnosticsContainer();
        String serviceId = servicePage.addService(randomServiceName, randomPrice);
        servicePage.assertLastCreatedService(randomServiceName, randomPrice);
        servicePage.deleteService(serviceId);
    }

    @Test
    public void updateServicePrice_when_validInput() {
        String randomPrice = TestDataGeneration.randomPrice();
        int price = Integer.parseInt(randomPrice);
        servicePage.clickEngineDiagnosticsContainer();
        servicePage.updateFirstServicePrice(randomPrice);

        String uiText = servicePage.getUpdatedPrice().getText();
        int actualInt = servicePage.integerPart(uiText);
        Assertions.assertEquals(price, actualInt);
    }
}