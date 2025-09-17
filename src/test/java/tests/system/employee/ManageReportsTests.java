package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;


public class ManageReportsTests extends SmartGarageBaseWebTest {
    @BeforeEach
    public void setUp() {
        loginPage.login(TestData.EMPLOYEE_USERNAME.getValue(), TestData.EMPLOYEE_PASSWORD.getValue());
    }

    @Test
    public void viewDetailedReportOfACustomer() {
        homePage.clickAdminPanelButton();
        adminPanelPage.clickOrdersContainer();
        myOrdersPage.selectFirstOrder();
        WebElement info = myOrdersPage.getEmployeeOrderInformation();

        Assertions.assertNotNull(info.getText(), "Detailed Repost is not visible");
    }
}
