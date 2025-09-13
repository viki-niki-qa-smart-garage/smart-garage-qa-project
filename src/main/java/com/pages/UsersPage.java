package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UsersPage extends BasePage {
    private final By usernameInput = By.xpath("//input[@name='username']");
    private final By emailInput = By.xpath("//input[@name='email']");
    private final By phoneInput = By.xpath("//input[@name='phoneNumber']");
    private final By allBrandsDropdown = By.xpath("//select[@name='vehicleBrand']");
    private final By visitedAfterInput = By.id("visitDateFrom");
    private final By visitedBeforeInput = By.id("visitDateTo");
    private final By sortByDropdown = By.xpath("//select[@name='sortBy']");
    private final By searchButton = By.xpath("//button[@class='modern-button']");
    private final By usersList = By.cssSelector(".user-list > .vehicle-item:not(.row)");


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
}
