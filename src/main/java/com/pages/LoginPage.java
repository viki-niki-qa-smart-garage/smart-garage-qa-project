package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private final By usernameField = By.id("login-username");
    private final By passwordField = By.id("login-password");
    private final By loginButton = By.xpath("//button[@type='submit'and contains(text(), 'Login')]");
    private final By forgotPassword = By.xpath("//a[@class='nounderline'and contains(text(), 'Forgot Password?')]");
    private final By registerTab = By.id("register-tab");
    private final By regUsernameField = By.id("reg-username");
    private final By regEmailField = By.id("reg-email");
    private final By regFirstNameField = By.id("reg-first-name");
    private final By regLastNameField = By.id("reg-last-name");
    private final By regPhoneNumberField = By.id("reg-phone");
    private final By registerButton = By.xpath("//button[@type='submit'and contains(text(), 'Register')]");
    private final By regMessage = By.xpath("//div[@class='registration-success-msg']//strong[contains(text(), 'Registration successful!')]");
    private final By responseUsername = By.xpath("//div[@class='registration-success-mg']//p[contains(text(), 'Your username: ')]//b");
    private final By responsePassword = By.xpath("//div[@class='registration-success-mg']//p[contains(text(), 'Your password: ')]//b");
    private final By myDetails = By.xpath("//div[@class='menu-container clearfix vertical-align-cell']//a[contains(text(), 'My Details')]");
    public final By generatedPassword = By.xpath("//div[@class='forgot-password-success-msg']//p[contains(text(), 'Your new password: ')]/b");
    private final By changePasswordEmail = By.id("email");
    private final By sendNewPassword = By.xpath("//button[@type='submit']");

    public LoginPage() {
        super("/auth/login");
    }

    public void login(String username, String password) {
        WebElement usernameInput = driver().findElement(usernameField);
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver().findElement(passwordField);
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver().findElement(loginButton);
        loginBtn.click();
    }

    public void register(String username, String email, String firstName, String lastName, String phone) {
        WebElement register = driver().findElement(registerTab);
        register.click();

        WebElement usernameInput = driver().findElement(regUsernameField);
        usernameInput.sendKeys(username);

        WebElement emailInput = driver().findElement(regEmailField);
        emailInput.sendKeys(email);

        WebElement firstNameInput = driver().findElement(regFirstNameField);
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver().findElement(regLastNameField);
        lastNameInput.sendKeys(lastName);

        WebElement phoneNumberInput = driver().findElement(regPhoneNumberField);
        phoneNumberInput.sendKeys(phone);

        WebElement registerBtn = driver().findElement(registerButton);
        registerBtn.click();
    }

    public void forgotPassword(String email) {
        driver().findElement(forgotPassword).click();
        WebElement emailInput = driver().findElement(changePasswordEmail);
        emailInput.sendKeys(email);
        driver().findElement(sendNewPassword).click();
    }

    public WebElement getRegistrationMessage() {
        return driver().findElement(regMessage);
    }

    public WebElement getRegistrationUsernameDetails() {
        return driver().findElement(responseUsername);
    }

    public WebElement getRegistrationPasswordDetails() {
        return driver().findElement(responsePassword);
    }

    public WebElement assertSuccessfulLogin() {
        return driverWait().until(ExpectedConditions.visibilityOfElementLocated(myDetails));
    }

    public WebElement getGeneratedPassword() {
        return driver().findElement(generatedPassword);
    }
}