package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ServicePage extends BasePage {
    private final By engineDiagnosticsContainer = By.xpath("//h4[@class='box-header']//a[@title='Engine Diagnostics']");
    private final By lubeOilAndFiltersContainer = By.xpath("//h4[@class='box-header']//a[@title='Lube, Oil and Filters']");
    private final By beltsAndHosesContainer = By.xpath("//h4[@class='box-header']//a[@title='Belts and Hoses']");
    private final By airConditioningContainer = By.xpath("//h4[@class='box-header']//a[@title='Air Conditioning']");
    private final By brakeRepairContainer = By.xpath("//h4[@class='box-header']//a[@title='Brake Repair']");
    private final By tireAndWheelServicesContainer = By.xpath("//h4[@class='box-header']//a[@title='Tire and Wheel Services']");
    private final By servicesList = By.xpath("//div[@class='clearfix']//ul[@class='services-list clearfix padding-top-70']//h4");
    private final By overviewService = By.xpath("//h3[@class='box-header']");
    private final By servicePriceTable = By.cssSelector("#servicesTable tbody tr");

    public ServicePage() {
        super("/services");
    }

    public void clickEngineDiagnosticsContainer() {
        driver().findElement(engineDiagnosticsContainer).click();
    }

    public void clickLubeOilAndFiltersContainer() {
        driver().findElement(lubeOilAndFiltersContainer).click();
    }

    public void clickBeltsAndHosesContainer() {
        driver().findElement(beltsAndHosesContainer).click();
    }

    public void clickAirConditioningContainer() {
        driver().findElement(airConditioningContainer).click();
    }

    public void clickBrakeRepairContainer() {
        driver().findElement(brakeRepairContainer).click();
    }

    public void clickTireAndWheelServicesContainer() {
        driver().findElement(tireAndWheelServicesContainer).click();
    }

    public List<WebElement> getServicesList() {
        return driverWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(servicesList));
    }

    public WebElement getServiceOverview() {
        return driver().findElement(overviewService);
    }

    public List<WebElement> getServicePriceTable() {
        return driver().findElements(servicePriceTable);
//        TEXT
    }
}
