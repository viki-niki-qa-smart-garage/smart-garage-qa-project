package tests.system;

import core.SmartGarageBaseWebTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class RegistrationTest extends SmartGarageBaseWebTest {
    @BeforeEach
    public void setUp() {
        homePage.navigate();
    }

    @Test
    public void successfulRegistration_when_validCredentials() {

        homePage.clickLoginButton();
        loginPage.register("testUser", "testUser@mail.com", "Ivan", "Ivanov", "1234567899");

        WebElement message = loginPage.getRegistrationMessage();
        Assertions.assertEquals("Registration successful!", message.getText(), "Not Successfully Registered!");

        WebElement username = loginPage.getRegistrationUsernameDetails();
        Assertions.assertNotNull(username.getText(), "Username is null");

        WebElement password = loginPage.getRegistrationPasswordDetails();
        Assertions.assertNotNull(password.getText(), "Password is not generated");
    }
}
