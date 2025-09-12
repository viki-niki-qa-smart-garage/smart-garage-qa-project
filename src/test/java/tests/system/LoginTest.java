package tests.system;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class LoginTest extends SmartGarageBaseWebTest {
    @BeforeEach
    public void setUp() {
        loginPage.navigate();
    }

    @Test
    public void successfulLogin_when_validCredentials() {

        loginPage.login(TestData.CUSTOMER_USERNAME.getValue(), TestData.CUSTOMER_PASSWORD.getValue());

        WebElement myDetails = loginPage.assertSuccessfulLogin();
        Assertions.assertTrue(myDetails.isDisplayed(), "You are not logged in");
    }
}
