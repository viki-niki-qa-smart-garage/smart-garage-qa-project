package com.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VehiclesPage extends BasePage {
    ClientCarsPage clientCarsPage = new ClientCarsPage();
    public VehiclesPage() {
        super("/vehicles");
    }

    public void assertAllVehicles() {
        List<WebElement> cars = clientCarsPage.getCarList();
        Assertions.assertFalse(cars.isEmpty(), "Expected at least 1 car on the page.");
    }
}
