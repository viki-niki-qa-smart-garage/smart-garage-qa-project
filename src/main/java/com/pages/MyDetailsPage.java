package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyDetailsPage extends BasePage {
    private final By editInfoButton = By.id("edit-info-button");
    private final By changePasswordButton = By.xpath("//a[@class='custom-button' and contains(text(), 'Change Password')]");

    public MyDetailsPage() {
        super("");
    }

    public void clickEditInfoButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(editInfoButton)).click();
    }

    public void clickChangePasswordButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(changePasswordButton)).click();
    }
}

