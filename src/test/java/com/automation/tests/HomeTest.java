package com.automation.tests;

import com.automation.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest{
    @Test
    public void verifyUserCanSearchAnItem(){
        homePage.openWebsite();
        homePage.closePopUp();
        Assert.assertTrue(homePage.isHomePageDisplayed());
        homePage.searchItem(ConfigReader.getConfigValue("search.item"));
        Assert.assertTrue(productListPage.isItemHeadingDisplayed(ConfigReader.getConfigValue("search.item")));
        Assert.assertTrue(productListPage.isProductListPageDisplayed());
        productListPage.clickFirstProduct();
        Assert.assertTrue(productDetailPage.isTheClickedProductDisplayed());
    }

    @Test
    public void verifyCategorySearch(){
        homePage.openWebsite();
        homePage.closePopUp();
        Assert.assertTrue(homePage.isHomePageDisplayed());
        homePage.searchCategory("T-shirts","Categories");
        Assert.assertTrue(productListPage.isProductListPageDisplayed());
        Assert.assertTrue(productListPage.isListPageHeadingDisplayed(ConfigReader.getConfigValue("category.name")));
        productListPage.clickFirstProduct();
        Assert.assertTrue(productDetailPage.isTheClickedProductDisplayed());
    }
}