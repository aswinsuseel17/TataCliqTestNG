package com.automation.tests;

import com.automation.pages.android.AndroidCartPage;
import com.automation.pages.android.AndroidHomePage;
import com.automation.pages.android.AndroidProductDetailPage;
import com.automation.pages.android.AndroidProductListPage;
import com.automation.pages.interfaces.CartPage;
import com.automation.pages.interfaces.HomePage;
import com.automation.pages.interfaces.ProductDetailPage;
import com.automation.pages.interfaces.ProductListPage;
import com.automation.pages.web.WebCartPage;
import com.automation.pages.web.WebHomePage;
import com.automation.pages.web.WebProductDetailPage;
import com.automation.pages.web.WebProductListPage;
import com.automation.utils.ConfigReader;
import com.automation.utils.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    HomePage homePage;
    ProductListPage productListPage;
    ProductDetailPage productDetailPage;
    CartPage cartPage;

    @BeforeMethod
    public void setUp(){
        ConfigReader.initConfig();
        DriverManager.createDriver();
        if (ConfigReader.getConfigValue("platform").equals("Web")) {
            homePage = new WebHomePage();
            productListPage = new WebProductListPage();
            productDetailPage = new WebProductDetailPage();
            cartPage = new WebCartPage();
        } else {
            homePage = new AndroidHomePage();
            productListPage = new AndroidProductListPage();
            productDetailPage = new AndroidProductDetailPage();
            cartPage = new AndroidCartPage();
        }
    }

    @AfterMethod
    public void cleanUp(){
        DriverManager.getDriver().quit();
    }
}
