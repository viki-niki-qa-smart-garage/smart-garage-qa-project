package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ClientCarsPage extends BasePage {
    Select select;
    private final By editButton = By.id("edit-21");
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
    private final By thirdPageButton = By.xpath("//a[contains(text(), '3')]");
    private final By vinAssertion = By.xpath("//div[@class='custom-car-list-container']//div[contains(text(), 'WAUZZZ8P4AA000100')]");
    private final By ErrorMessage = By.xpath("//div[@class='error-message']");
    private final By carList = By.cssSelector(".vehicle-item.custom-car-list-row");

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

    public void clickThirdPageButton() {
        driver().findElement(thirdPageButton).click();
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
}
