package tests.system;

import core.SmartGarageBaseWebTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class RegistrationTest extends SmartGarageBaseWebTest {

    @Test
    public void successfulRegistration_when_validCredentials() {
        String randomUsername = RandomStringUtils.randomAlphabetic(7).toLowerCase();
        String randomEmail = RandomStringUtils.randomAlphabetic(10).toLowerCase() + "@abv.bg";
        String randomFirstName = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        String randomLastName = RandomStringUtils.randomAlphabetic(10).toLowerCase();
        String randomNumber = RandomStringUtils.randomNumeric(10);
        
        loginPage.register(randomUsername, randomEmail, randomFirstName, randomLastName, randomNumber);

        WebElement message = loginPage.getRegistrationMessage();
        Assertions.assertEquals("Registration successful!", message.getText(), "Not Successfully Registered!");

        WebElement username = loginPage.getRegistrationUsernameDetails();
        Assertions.assertNotNull(username.getText(), "Username is null");

        WebElement password = loginPage.getRegistrationPasswordDetails();
        Assertions.assertNotNull(password.getText(), "Password is not generated");
    }
}
