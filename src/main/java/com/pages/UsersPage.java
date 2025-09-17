package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class UsersPage extends BasePage {
    Select select;
    private final By usernameInput = By.xpath("//input[@name='username']");
    private final By visitedAfterInput = By.id("visitDateFrom");
    private final By allBrandsDropdown = By.xpath("//select[@name='vehicleBrand']");
    private final By searchButton = By.xpath("//button[@class='modern-button']");
    private final By usersList = By.cssSelector(".user-list > .vehicle-item:not(.row)");
    private final By allUsernames =By.cssSelector(".user-list .vehicle-item.white .column a");
    private final By allUsersBrands = By.xpath("//div[contains(@class,'user-list')]//div[contains(@class,'vehicle-item')]//div[contains(@class,'column-1-6')]//li[contains(text(), 'Porsche')]");
    private final By allDates = By.xpath("//div[contains(@class,'user-list')]//div[contains(@class,'vehicle-item')]//div[contains(@class,'column-1-6')][last()]//li");

    public UsersPage() {
        super("/users");
    }

    public List<WebElement> getUserList() {
        return driverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(usersList));
    }

     public void searchCustomerByName(String username) {
         driverWait().until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(username);
     }

    public void clickSearchButton() {
        driverWait().until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public List<WebElement> getAllUsernamesList() {
        return driverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allUsernames));
    }

    public void searchCustomerByVehicle() {
        WebElement brandSelectField = driverWait().until(ExpectedConditions.visibilityOfElementLocated(allBrandsDropdown));
        select = new Select(brandSelectField);
        select.selectByVisibleText("Porsche");
        driverWait().until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public List<WebElement> getAllUsersBrandsList() {
        return driverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allUsersBrands));
    }

    public void searchCustomerByVisitedAfter(String date) {
        WebElement brandSelectField = driverWait().until(ExpectedConditions.visibilityOfElementLocated(visitedAfterInput));
        brandSelectField.sendKeys(date);
        driverWait().until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public List<WebElement> getAllDatesList() {
        return driverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(allDates));
    }
}