package tests.system;

import core.SmartGarageBaseWebTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import utils.TestDataGeneration;

public class RegistrationTest extends SmartGarageBaseWebTest {

    @Test
    public void successfulRegistration_when_validCredentials() {
        String randomUsername = TestDataGeneration.randomUsername();
        String randomEmail = TestDataGeneration.randomEmail();
        String randomFirstName = TestDataGeneration.randomFirstName();
        String randomLastName = TestDataGeneration.randomLastName();
        long randomNumber = TestDataGeneration.randomNumber();
        
        loginPage.register(randomUsername, randomEmail, randomFirstName, randomLastName, String.valueOf(randomNumber));

        WebElement message = loginPage.getRegistrationMessage();
        Assertions.assertEquals("Registration successful!",
                message.getText(),
                "Not Successfully Registered!");

        WebElement username = loginPage.getRegistrationUsernameDetails();
        Assertions.assertNotNull(username.getText(), "Username is null");

        WebElement password = loginPage.getRegistrationPasswordDetails();
        Assertions.assertNotNull(password.getText(), "Password is not generated");
    }
}