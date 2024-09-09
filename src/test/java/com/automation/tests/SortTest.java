package com.automation.tests;

import com.automation.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SortTest extends BaseTest{
    @Test
    public void verifyUserCanSortPriceHighToLow(){
        homePage.openWebsite();
        homePage.closePopUp();
        homePage.isHomePageDisplayed();
        homePage.searchItem(ConfigReader.getConfigValue("search.item"));
        Assert.assertTrue(productListPage.isItemHeadingDisplayed(ConfigReader.getConfigValue("search.item")));
        Assert.assertTrue(productListPage.isProductListPageDisplayed());
        productListPage.selectSortType("Price High to Low");
        Assert.assertTrue(productListPage.isPriceHighToLowSorted());
    }


    @Test
    public void verifyUserCanSortPriceLowToHigh(){
        homePage.openWebsite();
        homePage.closePopUp();
        homePage.isHomePageDisplayed();
        homePage.searchItem(ConfigReader.getConfigValue("search.item"));
        Assert.assertTrue(productListPage.isItemHeadingDisplayed(ConfigReader.getConfigValue("search.item")));
        Assert.assertTrue(productListPage.isProductListPageDisplayed());
        productListPage.selectSortType("Price Low to High");
        Assert.assertTrue(productListPage.isPriceLowToHighSorted());
    }

    @Test
    public void verifyUserCanSortDiscount(){
        homePage.openWebsite();
        homePage.closePopUp();
        homePage.isHomePageDisplayed();
        homePage.searchItem(ConfigReader.getConfigValue("search.item"));
        Assert.assertTrue(productListPage.isItemHeadingDisplayed(ConfigReader.getConfigValue("search.item")));
        Assert.assertTrue(productListPage.isProductListPageDisplayed());
        productListPage.selectSortType("Discounts");
        Assert.assertTrue(productListPage.isDiscountSorted());
    }
}
