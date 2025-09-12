package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPanelPage extends BasePage {
    private final By allUsersWindow = By.xpath("//a[@title='All Users']");
    private final By clientCarsWindow = By.xpath("//a[@title='Client Cars']");
    private final By ordersWindow = By.xpath("//a[@title='Orders']");

    public AdminPanelPage() {
        super("/admin-panel");
    }

    public void clickAllUsersWindow() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(allUsersWindow)).click();
    }

    public void clickClientCarsWindow() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(clientCarsWindow)).click();
    }

    public void clickOrdersWindow() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(ordersWindow)).click();
    }
}
