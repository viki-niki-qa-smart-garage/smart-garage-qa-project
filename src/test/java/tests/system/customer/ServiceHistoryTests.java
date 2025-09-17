package tests.system.customer;

import core.SmartGarageBaseWebTest;
import enums.TestData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class ServiceHistoryTests extends SmartGarageBaseWebTest {
    @BeforeEach
    public void setUp() {
        loginPage.login(TestData.CUSTOMER_USERNAME.getValue(), TestData.CUSTOMER_PASSWORD.getValue());
    }

    @Test
    public void customerCanViewListOfTheirServices() {
        homePage.clickMyOrdersButton();
        WebElement allOrders = myOrdersPage.getAllCustomerOrders();
        Assertions.assertTrue(allOrders.isDisplayed());
    }
}
