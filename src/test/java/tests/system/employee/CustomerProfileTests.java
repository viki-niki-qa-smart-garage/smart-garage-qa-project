package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CustomerProfileTests extends SmartGarageBaseWebTest {
    @BeforeEach
    public void setUp() {
        loginPage.navigate();
        loginPage.login(TestData.EMPLOYEE_USERNAME_VIKI.getValue(), TestData.EMPLOYEE_PASSWORD_VIKI.getValue());
    }

    @Test
    public void browseAllCustomersProfiles() {
        homePage.clickAdminPanelButton();
        adminPanelPage.clickAllUsersWindow();
        List<WebElement> users = usersPage.getUserList();
        Assertions.assertTrue(users.size() > 0, "Expected at least 1 user on the page.");
    }

    @Test
    public void filterCustomersByName() {
        homePage.clickAdminPanelButton();
        adminPanelPage.clickAllUsersWindow();
        usersPage.searchCustomerByName("Mi");
        usersPage.clickSearchButton();
        List<WebElement> users = usersPage.getUserList();
        Assertions.assertTrue(users.size() > 0, "Expected at least 1 user on the page.");
    }
}
