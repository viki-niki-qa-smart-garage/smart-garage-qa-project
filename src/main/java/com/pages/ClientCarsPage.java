package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class ClientCarsPage extends BasePage {
    Select select;
    private final By editButton = By.id("edit-21");
    private final By editVinInput = By.id("input-vin-21");
    private final By editLicensePlateInput = By.id("input-licensePlate-21");
    private final By saveButton = By.id("save-21");
    private final By searchBar = By.id("search");
    private final By sortByDropdown = By.id("sort-by");
    private final By orderByDropdown = By.id("order");
    private final By searchButton = By.xpath("//button[@class='modern-button']");
    private final By vinInput = By.id("vin");
    private final By licensePlateInput = By.id("licensePlate");
    private final By ownerInput = By.id("owner");
    private final By brandDropdown = By.id("brand");
    private final By makeInput = By.id("make");
    private final By engineTypeInput = By.id("engineType");
    private final By yearInput = By.id("yearOfCreation");
    private final By addClientCarButton = By.xpath("//button[@type='submit'and contains(text(), 'ADD CLIENT CAR')]");
     private final By lastPageButton = By.xpath("//div[@class='pagination']//a[contains(text(), '3')]");
    private final By vinAssertion = By.xpath("//div[@class='custom-car-list-container']//div[contains(text(), 'WAUZZZ8P4AA000100')]");
    private final By ErrorMessage = By.xpath("//div[@class='error-message']");
    private final By carList = By.cssSelector(".vehicle-item.custom-car-list-row");
    private final By vinField = By.cssSelector("#vin-21");
    private final By plateField = By.cssSelector("#licensePlate-21");
    private final By ownerField = By.cssSelector("div.custom-car-list-column:nth-of-type(3)");

    private final By listContainer = By.cssSelector("div.custom-car-list-items");
    private final By vehicleRows = By.cssSelector("div.custom-car-list-items > div.vehicle-item");
    private final By pagination = By.cssSelector("ul.pagination");
    private final By lastCreatedVehicle = By.cssSelector("div.custom-car-list-items > div.vehicle-item:last-child");



    public ClientCarsPage() {
        super("/client-cars");
    }

    public void addClientCar(String vin, String licensePlate, String owner, String make, String engineType, String year) {


        WebElement vinField = driver().findElement(vinInput);
        vinField.sendKeys(vin);

        WebElement licensePlateField = driver().findElement(licensePlateInput);
        licensePlateField.sendKeys(licensePlate);

        WebElement ownerField = driver().findElement(ownerInput);
        ownerField.sendKeys(owner);

        WebElement brandSelectField = driver().findElement(brandDropdown);
        select = new Select(brandSelectField);
        select.selectByVisibleText("Audi");

        WebElement makeField = driver().findElement(makeInput);
        makeField.sendKeys(make);

        WebElement engineTypeField = driver().findElement(engineTypeInput);
        engineTypeField.sendKeys(engineType);

        WebElement yearField = driver().findElement(yearInput);
        yearField.sendKeys(year);

        WebElement addClientCarBtn = driver().findElement(addClientCarButton);
        addClientCarBtn.click();

    }

    public void navigateToLastPage() {
        driver().findElement(lastPageButton).click();
    }

    public List<WebElement> getLastPageVehicles() {
      return driverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(listContainer));
    }

    private int getRowCount() {
        return driver().findElements(vehicleRows).size();
    }

    private List<WebElement> getLastRowCells() {
        driverWait().withTimeout(Duration.ofSeconds(10));
        WebElement lastRow = driverWait().until(ExpectedConditions.visibilityOfElementLocated(lastCreatedVehicle));
        return lastRow.findElements(By.cssSelector("div.custom-car-list-column"));
    }

    public WebElement getVin() {
        return driver().findElement(vinAssertion);
    }

    public WebElement getErrorMessage() {
        return driver().findElement(ErrorMessage);
    }

    public List<WebElement> getCarList() {
        return driver().findElements(carList);
    }

    public void updateCarDetails(String vin, String licensePlate) {
        driver().findElement(editButton).click();

        WebElement vinField = driver().findElement(editVinInput);
        vinField.clear();
        vinField.sendKeys(vin);

        WebElement licensePlateField = driver().findElement(editLicensePlateInput);
        licensePlateField.clear();
        licensePlateField.sendKeys(licensePlate);

        driver().findElement(saveButton).click();
        driverWait().withTimeout(Duration.ofSeconds(20));
    }

    public String getCarVin(WebElement carRow) {
        return carRow.findElement(vinField).getText();
    }

    public String getCarPlate(WebElement carRow) {
        return carRow.findElement(plateField).getText();
    }

    public void filterByFirstName(String firstName) {
        WebElement search = driver().findElement(searchBar);
        search.sendKeys(firstName);
        WebElement sortBy = driver().findElement(sortByDropdown);
        select = new Select(sortBy);
        select.selectByVisibleText("Owner First Name");
        WebElement searchBtn = driver().findElement(searchButton);
        searchBtn.click();
    }

    public WebElement getOwner() {
        WebElement firstRow = driver().findElement(By.cssSelector(".vehicle-item.white.custom-car-list-row"));
        return firstRow.findElement(ownerField);
    }
}
