package com.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private final By rows = By.cssSelector("#servicesTable tbody tr");
    private final By tbody = By.cssSelector("#servicesTable tbody");
    private final By lastCreatedService = By.cssSelector("#servicesTable tbody tr:last-child");
    private final By firstServiceRow = By.cssSelector("#servicesTable tbody tr:first-child");
    private final By addServiceButton = By.id("addServiceButton");
    private final By serviceNameInput = By.xpath("//input[@name='serviceName']");
    private final By servicePriceInput = By.xpath("//input[@name='servicePrice']");
    private final By saveServiceButton = By.xpath("//div[@class='row page-margin-top']//a[contains(text(), 'Save Service')]");
    private final By assertUpdatedPrice = By.xpath("//td[@id='service-price-2']/span");

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

    private int getRowsCount() {
        return driver().findElements(rows).size();
    }


    public String addService(String serviceName, String servicePrice) {

        int before = getRowsCount();
        WebElement oldTbody = driver().findElement(tbody);

        driver().findElement(addServiceButton).click();
        driver().findElement(serviceNameInput).sendKeys(serviceName);
        driver().findElement(servicePriceInput).sendKeys(servicePrice);

        WebElement saveButton = driver().findElement(saveServiceButton);
        saveButton.click();

        driverWait().until(ExpectedConditions.stalenessOf(oldTbody));

        driverWait().until(d -> getRowsCount() > before);
        WebElement lastRow = driver().findElement(lastCreatedService);
        return lastRow.getAttribute("data-service-id");
    }

    private List<WebElement> getLastRowCells() {
        driverWait().withTimeout(Duration.ofSeconds(10));
        WebElement lastRow = driverWait().until(ExpectedConditions.visibilityOfElementLocated(lastCreatedService));
        return lastRow.findElements(By.tagName("td"));
    }

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

    public void deleteCreatedService(String name, String pricePart) {
        String id = addService(name, pricePart);
        assertLastCreatedService(name, pricePart);
        driverWait().withTimeout(Duration.ofSeconds(11));
        deleteService(id);

        List<WebElement> serviceIsDeleted = driver().findElements(
                By.cssSelector("#servicesTable tbody tr[data-service-id='" + id + "']"));
        Assertions.assertTrue(serviceIsDeleted.isEmpty(), "Service row with id=" + id + " still exists after delete.");
    }


    public void updateFirstServicePrice(String newPrice) {
        WebElement row = driverWait().until(
                ExpectedConditions.visibilityOfElementLocated(firstServiceRow));
        String serviceId = row.getAttribute("data-service-id");

        WebElement editBtn = row.findElement(By.id("edit-" + serviceId));
        editBtn.click();

        By priceInputLocator = By.cssSelector("#service-price-" + serviceId + " input[type='number']");
        WebElement priceInput = driverWait().until(
                ExpectedConditions.visibilityOfElementLocated(priceInputLocator));

        priceInput.clear();
        priceInput.sendKeys(newPrice);

        WebElement saveBtn = row.findElement(By.id("save-" + serviceId));
        saveBtn.click();

        String expected = formatToTwoDecimals(newPrice);
        By priceSpan = By.cssSelector("#service-price-" + serviceId + " > span");

        driverWait().until(d -> {
            WebElement span = d.findElement(priceSpan);
            String txt = span.getText().trim();
            return txt.equals(expected) || txt.equals(expected.replace('.', ',')); // dot or comma
        });
    }

    private String formatToTwoDecimals(String raw) {
        BigDecimal bd = new BigDecimal(raw.replace(",", "."));
        return bd.setScale(2, RoundingMode.UNNECESSARY).toPlainString(); // "120.00"
    }

    public WebElement getUpdatedPrice() {
        WebElement fresh = driverWait().until(ExpectedConditions.visibilityOfElementLocated(firstServiceRow));
        String serviceId = fresh.getAttribute("data-service-id");
        return driver().findElement(By.cssSelector("#service-price-" + serviceId + " > span"));
    }

    public int integerPart(String value) {
        Matcher match = Pattern.compile("([0-9]+)([\\.,][0-9]{1,2})?").matcher(value);
        if (!match.find()) throw new IllegalArgumentException("No numeric value in: " + value);
        return Integer.parseInt(match.group(1));
    }
}
