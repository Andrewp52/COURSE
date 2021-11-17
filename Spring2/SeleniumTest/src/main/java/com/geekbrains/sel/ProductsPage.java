package com.geekbrains.sel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends DriverActions{
    private static final String PRODUCTS_URL = "http://localhost:8080/shop/?page=1";

    private static final By TABLE_BODY = By.tagName("tbody");
    private static final By TABLE_ROW = By.tagName("tr");


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void go(){
        driver.get(PRODUCTS_URL);
    }

    public boolean isProductsTablePresent(){
        return isElementPresent(TABLE_BODY);
    }

    public boolean isProductsTableNotEmpty(){
        List<WebElement> elements = getInnerElements(TABLE_BODY, TABLE_ROW);
        return elements != null && !elements.isEmpty();
    }
}
