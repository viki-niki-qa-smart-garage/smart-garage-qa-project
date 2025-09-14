package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminPanelPage extends BasePage {
    private final By allUsersContainer = By.xpath("//a[@title='All Users']");
    private final By clientCarsContainer = By.xpath("//a[@title='Client Cars']");
    private final By ordersContainer = By.xpath("//a[@title='Orders']");

    public AdminPanelPage() {
        super("/admin-panel");
    }

    public void clickAllUsersContainer() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(allUsersContainer)).click();
    }

    public void clickClientCarsContainer() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(clientCarsContainer)).click();
    }

    public void clickOrdersContainer() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(ordersContainer)).click();
    }
}
