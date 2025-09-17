package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyOrdersPage extends BasePage{
    private final By firstOrder = By.cssSelector(".vehicle-item.white:first-of-type .column.column-1-6 a");
    private final By employeeOrderInfo = By.xpath("//div[@class='row page-margin-top']");
    private final By viewDetailsButton = By.xpath("//button[@class='more simple']");
    private final By customerOrderInfo = By.xpath("//div[@class='column column-3-4']");
    private final By customerOrdersList = By.xpath("//div[@class='row page-margin-top']");

    public MyOrdersPage() {
        super("/orders");
    }

    public void selectFirstOrder() {
        driver().findElement(firstOrder).click();
    }

    public WebElement getEmployeeOrderInformation() {
        return driver().findElement(employeeOrderInfo);
    }

    public void clickViewDetailsButton() {
        driver().findElement(viewDetailsButton).click();
    }

    public WebElement getCustomerOrderInformation() {
        return driver().findElement(customerOrderInfo);
    }

    public WebElement getAllCustomerOrders() {
        return driver().findElement(customerOrdersList);
    }
}