package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class ManageDetailsTests extends SmartGarageBaseWebTest {

    @Test
    public void changePassword() {
        loginPage.login(TestData.EMPLOYEE2_USERNAME.getValue(), TestData.EMPLOYEE2_PASSWORD.getValue());
        homePage.clickMyDetailsButton();
        myDetailsPage.clickChangePasswordButton();
        myDetailsPage.changePassword("Qwertyuiop1@", "Qwertyuiop1#", "Qwertyuiop1#");
        myDetailsPage.clickLogoutButton();
        homePage.clickLoginButton();
        loginPage.login(TestData.EMPLOYEE2_USERNAME.getValue(), TestData.EMPLOYEE2_PASSWORD.getValue());

        WebElement button = myDetailsPage.getLoginButton();
        Assertions.assertTrue(button.isDisplayed(), "Not successfully logged in");
    }

    @Test
    public void forgotPassword() {
        homePage.clickLoginButton();
        loginPage.forgotPassword("diana.smith@gmail.com");
        WebElement newPassword = loginPage.getGeneratedPassword();
        Assertions.assertNotNull(newPassword, "Password is not generated");
    }
}
