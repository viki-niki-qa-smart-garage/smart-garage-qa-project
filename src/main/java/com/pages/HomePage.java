package com.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {
    public final By loginButton = By.xpath("//a[@class='social-login']");
    public final By homeButton = By.xpath("//ul[@class='sf-menu']//a[@title='Home']");
    public final By servicesButton = By.xpath("//ul[@class='sf-menu']//a[@title='Services']");
    public final By vehiclesButton = By.xpath("//ul[@class='sf-menu']//a[@title='Vehicles']");

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
}
