package tests.system.employee;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class ManageDetailsTests extends SmartGarageBaseWebTest {
    @Epic("SG-1 Login and Entities Tests")
    @Story("SG-14 Customer/Admin Change Password Functionality")
    @Test
    public void changePassword() {
        String username = TestData.EMPLOYEE2_USERNAME.getValue();
        String oldPassword = TestData.EMPLOYEE2_PASSWORD.getValue();
        String newPassword = "Qwertyuiop1#";

        loginPage.login(username, oldPassword);
        homePage.clickMyDetailsButton();
        myDetailsPage.clickChangePasswordButton();
        myDetailsPage.changePassword(oldPassword, newPassword, newPassword);
        myDetailsPage.clickLogoutButton();

        homePage.clickLoginButton();
        loginPage.login(username, newPassword);

        WebElement button = myDetailsPage.getLoginButton();
        Assertions.assertTrue(button.isDisplayed(), "Not successfully logged in");

        homePage.clickMyDetailsButton();
        myDetailsPage.clickChangePasswordButton();
        myDetailsPage.changePassword(newPassword, oldPassword, oldPassword);
        myDetailsPage.clickLogoutButton();
    }

    @Epic("SG-1 Login and Entities Tests")
    @Story("SG-10 Customer/Admin Forgot Password Functionality")
    @Test
    public void forgotPassword() {
        homePage.clickLoginButton();
        loginPage.forgotPassword("diana.smith@gmail.com");
        WebElement newPassword = loginPage.getGeneratedPassword();
        Assertions.assertNotNull(newPassword, "Password is not generated");
    }
}