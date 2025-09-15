package com.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
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
//    private final By addServiceButton = By.id("addServiceButton");
//    private final By serviceNameInput = By.xpath("//input[@name='serviceName']");
//    private final By servicePriceInput = By.xpath("//input[@name='servicePrice']");
//    private final By saveServiceButton = By.xpath("//div[@class='row page-margin-top']//a[contains(text(), 'Save Service')]");
//    private final By lastCreatedService = By.cssSelector("#servicesTable tbody tr:last-child");

    private final By rows = By.cssSelector("#servicesTable tbody tr");
    private final By tbody = By.cssSelector("#servicesTable tbody");
    private final By lastCreatedService = By.cssSelector("#servicesTable tbody tr:last-child");
    private final By firstServiceRow = By.cssSelector("#servicesTable tbody tr:first-child");
    private final By addServiceButton = By.id("addServiceButton");
    private final By serviceNameInput = By.xpath("//input[@name='serviceName']");
    private final By servicePriceInput = By.xpath("//input[@name='servicePrice']");
    private final By saveServiceButton = By.xpath("//div[@class='row page-margin-top']//a[contains(text(), 'Save Service')]");
    private final By assertUpdatedPrice = By.id("service-price-2");

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

    }

    // helper
    private int getRowsCount() {
        return driver().findElements(rows).size();
    }

    // Create service and wait for DOM update
    public String addService(String serviceName, String servicePrice) {
        // Current number of rows
        int before = getRowsCount();
        WebElement oldTbody = driver().findElement(tbody);

        // Fill the form
        driver().findElement(addServiceButton).click();
        driver().findElement(serviceNameInput).sendKeys(serviceName);
        driver().findElement(servicePriceInput).sendKeys(servicePrice);

        // Click Save
        WebElement saveButton = driver().findElement(saveServiceButton);
        saveButton.click();

        driverWait().until(ExpectedConditions.stalenessOf(oldTbody));

        // Wait until table size increases
        driverWait().until(d -> getRowsCount() > before);
        WebElement lastRow = driver().findElement(lastCreatedService);
        return lastRow.getAttribute("data-service-id");
    }

    // Read last row cells
    private List<WebElement> getLastRowCells() {
        driverWait().withTimeout(Duration.ofSeconds(10));
        WebElement lastRow = driverWait().until(ExpectedConditions.visibilityOfElementLocated(lastCreatedService));
        return lastRow.findElements(By.tagName("td"));
    }

    // Assertion for the last created service
    public void assertLastCreatedService(String expectedName, String expectedPrice) {
        List<WebElement> cells = getLastRowCells();


        String actualName = cells.get(0).getText().trim();
        String actualPrice = cells.get(1).getText().trim();

        Assertions.assertEquals(expectedName, actualName, "Service name mismatch");
        Assertions.assertTrue(
                actualPrice.contains(expectedPrice),
                "Expected price to contain '" + expectedPrice + "', but was: " + actualPrice
        );
    }

    public void deleteService(String serviceId) {
        By rowById = By.cssSelector("#servicesTable tbody tr[data-service-id='" + serviceId + "']");
        WebElement row = driverWait().until(ExpectedConditions.visibilityOfElementLocated(rowById));
        int before = getRowsCount();
        WebElement oldTbody = driver().findElement(tbody);


        WebElement deleteBtn = row.findElement(By.id("delete-" + serviceId));
        driverWait().withTimeout(Duration.ofSeconds(11));
        deleteBtn.click();


        driverWait().until(ExpectedConditions.alertIsPresent()).accept();

        driverWait().until(ExpectedConditions.stalenessOf(oldTbody));

        driverWait().until(ExpectedConditions.invisibilityOfElementLocated(rowById));
        driverWait().until(d -> getRowsCount() == before - 1);
    }

    public void createThenDeleteService(String name, String pricePart) {
        String id = addService(name, pricePart);
        assertLastCreatedService(name, pricePart);
        driverWait().withTimeout(Duration.ofSeconds(11));
        deleteService(id);


        List<WebElement> serviceIsDeleted = driver().findElements(
                By.cssSelector("#servicesTable tbody tr[data-service-id='" + id + "']"));
        Assertions.assertTrue(serviceIsDeleted.isEmpty(), "Service row with id=" + id + " still exists after delete.");
    }

    public void updateFirstServicePrice(String newPrice) {
        // find the first row and its id
        WebElement row = driverWait().until(ExpectedConditions.visibilityOfElementLocated(firstServiceRow));
        String serviceId = row.getAttribute("data-service-id");

        // click Edit button (id="edit-{id}")
        WebElement editBtn = row.findElement(By.id("edit-" + serviceId));
        editBtn.click();

        // wait for the price input to appear (assume name='servicePrice')
        WebElement priceInput = driverWait().until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[@id='service-price-2']/input[@type='number']"))
        );

        // clear old value and type new one
        priceInput.clear();
        priceInput.sendKeys(newPrice);

        // click Save (inside the edit form)
        WebElement saveBtn = driver().findElement(By.id("save-2"));
        saveBtn.click();

        // wait for the row’s price cell to be updated
        driverWait().until(ExpectedConditions.textToBePresentInElement(
                row.findElements(By.tagName("td")).get(1),
                newPrice
        ));
    }

    public WebElement getUpdatedPrice() {
        return driverWait().until(ExpectedConditions.visibilityOfElementLocated(assertUpdatedPrice));
    }
}
