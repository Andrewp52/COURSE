package com.geekbrains.sel;

import org.junit.Test;
import static org.junit.Assert.*;
public class ProductsPageTest extends SeleniumBase{
    @Test
    public void isProductsTablePresent(){
        productsPage.go();
        assertTrue(productsPage.isProductsTablePresent());
    }

    @Test
    public void isProductsTableNotEmpty(){
        productsPage.go();
        assertTrue(productsPage.isProductsTableNotEmpty());
    }
}
