package com.geekbrains.sel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DriverActions {
    WebDriver driver;

    public DriverActions(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getInnerElements(By parent, By by){
        List<WebElement> elements = null;
        try {
            elements = driver.findElement(parent).findElements(by);
        } catch (Exception e) {

        }
        return elements;
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
