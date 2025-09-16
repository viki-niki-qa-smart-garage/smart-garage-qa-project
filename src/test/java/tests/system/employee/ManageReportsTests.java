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
        loginPage.navigate();
        loginPage.login(TestData.EMPLOYEE_USERNAME_VIKI.getValue(), TestData.EMPLOYEE_PASSWORD_VIKI.getValue());
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
