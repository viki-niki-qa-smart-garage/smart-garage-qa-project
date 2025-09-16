package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
    public final By loginButton = By.xpath("//a[@class='social-login']");
    public final By homeButton = By.xpath("//ul[@class='sf-menu']//a[@title='Home']");
    public final By servicesButton = By.xpath("//ul[@class='sf-menu']//a[@title='Services']");
    public final By vehiclesButton = By.xpath("//ul[@class='sf-menu']//a[@title='Vehicles']");
    private final By myDetailsButton = By.xpath("//div[@class='menu-container clearfix vertical-align-cell']//a[contains(text(), 'My Details')]");
    private final By adminPanelButton = By.xpath("//div[@class='menu-container clearfix vertical-align-cell']//a[contains(text(), 'Admin Panel')]");
    private final By myOrdersButton = By.xpath("//div[@class='menu-container clearfix vertical-align-cell']//a[contains(text(), 'My Orders')]");


    public HomePage() {
        super("");
    }

    public void clickLoginButton() {
        driver().findElement(loginButton).click();
    }

    public void clickHomeButton() {
        driver().findElement(homeButton).click();
    }

    public void clickServicesButton() {
        driver().findElement(servicesButton).click();
    }

    public void clickVehiclesButton() {
        driver().findElement(vehiclesButton).click();
    }

    public void clickMyDetailsButton() {
        driver().findElement(myDetailsButton).click();
    }

    public void clickAdminPanelButton() {
        driver().findElement(adminPanelButton).click();
    }

    public void clickMyOrdersButton() {
        driver().findElement(myOrdersButton).click();
    }
}
