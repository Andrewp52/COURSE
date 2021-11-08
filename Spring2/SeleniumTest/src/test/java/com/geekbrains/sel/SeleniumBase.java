package com.geekbrains.sel;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumBase {
    WebDriver driver;
    ProductsPage productsPage;
    @Before
    public void init(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        productsPage = new ProductsPage(driver);
    }

    @After
    public void stop(){
        driver.close();
        driver.quit();
    }
}
