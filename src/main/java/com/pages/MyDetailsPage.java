package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyDetailsPage extends BasePage {
    private final By editInfoButton = By.id("edit-info-button");
    private final By changePasswordButton = By.xpath("//a[@class='custom-button' and contains(text(), 'Change Password')]");
    private final By oldPasswordField = By.id("old-password");
    private final By newPasswordField = By.id("new-password");
    private final By confirmPasswordField = By.id("confirm-password");
    private final By saveButton = By.xpath("//button[@class='more']");
    private final By logoutButton = By.xpath("//a[@title='Logout']");
    public final By loginButton = By.xpath("//a[@class='social-login']");

    public MyDetailsPage() {
        super("");
    }

    public void clickEditInfoButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(editInfoButton)).click();
    }

    public void clickChangePasswordButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(changePasswordButton)).click();
    }

    public void changePassword(String oldPassword, String newPassword, String confirmPassword) {
        WebElement oldPasswordInput = driver().findElement(oldPasswordField);
        oldPasswordInput.sendKeys(oldPassword);

        WebElement newPasswordInput = driver().findElement(newPasswordField);
        newPasswordInput.sendKeys(newPassword);

        WebElement confirmPasswordInput = driver().findElement(confirmPasswordField);
        confirmPasswordInput.sendKeys(confirmPassword);

        WebElement saveBtn = driver().findElement(saveButton);
        saveBtn.click();
    }

    public void clickLogoutButton() {
        driverWait().until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).click();
    }

    public WebElement getLoginButton() {
        return driver().findElement(loginButton);
    }
}

