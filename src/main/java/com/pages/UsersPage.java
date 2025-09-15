package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UsersPage extends BasePage {
    Select select;
    private final By usernameInput = By.xpath("//input[@name='username']");
    private final By emailInput = By.xpath("//input[@name='email']");
    private final By phoneInput = By.xpath("//input[@name='phoneNumber']");
    private final By allBrandsDropdown = By.xpath("//select[@name='vehicleBrand']");
    private final By visitedAfterInput = By.id("visitDateFrom");
    private final By sortByDropdown = By.xpath("//select[@name='sortBy']");
    private final By searchButton = By.xpath("//button[@class='modern-button']");
    private final By usersList = By.cssSelector(".user-list > .vehicle-item:not(.row)");
    private final By allUsernames = By.xpath("//div[contains(@class,'user-list')]//div[contains(@class,'vehicle-item')]//div[contains(@class,'column-1-6')]/a");
    private final By allUsersBrands = By.xpath("//div[contains(@class,'user-list')]//div[contains(@class,'vehicle-item')]//div[contains(@class,'column-1-6')]//li[contains(text(), 'Porsche')]");
    private final By allDates = By.xpath("//div[contains(@class,'user-list')]//div[contains(@class,'vehicle-item')]//div[contains(@class,'column-1-6')][last()]//li");



    public UsersPage() {
        super("/users");
    }

    public List<WebElement> getUserList() {
        return driver().findElements(usersList);
    }

     public void searchCustomerByName(String username) {
        driver().findElement(usernameInput).sendKeys(username);
     }

    public void clickSearchButton() {
        driver().findElement(searchButton).click();
    }

    public List<WebElement> getAllUsernamesList() {
        return driver().findElements(allUsernames);
    }

    public void searchCustomerByVehicle() {
        WebElement brandSelectField = driver().findElement(allBrandsDropdown);
        select = new Select(brandSelectField);
        select.selectByVisibleText("Porsche");
        driver().findElement(searchButton).click();
    }

    public List<WebElement> getAllUsersBrandsList() {
        return driver().findElements(allUsersBrands);
    }

    public void searchCustomerByVisitedAfter(String date) {
        WebElement brandSelectField = driver().findElement(visitedAfterInput);
        brandSelectField.sendKeys(date);
        driver().findElement(searchButton).click();
    }

    public List<WebElement> getAllDatesList() {
        return driver().findElements(allDates);
    }
}
