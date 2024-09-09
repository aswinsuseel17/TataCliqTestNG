package com.automation.pages.web;

import com.automation.pages.interfaces.ProductDetailPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WebProductDetailPage extends WebBasePage implements ProductDetailPage {
    @FindBy(xpath = "//div[@itemprop='name']")
    WebElement productName;

    public boolean isTheClickedProductDisplayed() {
        return productName.getText().equals(ConfigReader.getConfigValue("product.name"));
    }

    @FindBy(xpath = "//button[text()='Add To Bag']")
    WebElement addToCartBtn;
    @FindBy(xpath = "//span[text()='ADD TO BAG']")
    WebElement addToCartBtn2;
    public void clickAddToCart() {
        if(isDisplayed(addToCartBtn)){
            addToCartBtn.click();
        }
        else {
            addToCartBtn2.click();
        }
    }

    @FindBy(css = ".DesktopHeader__cartCount")
    WebElement cartCount;

    public String verifyCartCount() {
        return cartCount.getText();
    }

    public void clickCartIcon() {
        cartCount.click();
    }

    @FindBy(xpath = "//div[@class='SizeSelectNewPdp__base']")
    List<WebElement> sizeList;

    public void selectSize(String key) {
        if(!sizeList.isEmpty()) {
            ConfigReader.setConfigValue(key, sizeList.get(0).getText());
            javascriptExecutorClick(sizeList.get(0));
        }
    }

    @FindBy(className = "ProductDescriptionPage__moreProductInfoHead")
    WebElement moreProductInfo;

    public void clickMoreProductInfo() {
        javascriptExecutorClick(moreProductInfo);
    }

    @FindBy(css = ".MoreProductInfoComponent__header")
    WebElement productInfoHeading;

    public boolean isProductInfoDisplayed() {
        return productInfoHeading.isDisplayed();
    }

    @FindBy(className = "ProductDescriptionPage__visitStore")
    WebElement visitStoreBtn;
    @FindBy(xpath = "//button[@class='ProductDescriptionPage__visitStore']/preceding-sibling::div[2]")
    WebElement brandName;

    public void clickVisitStore() {
        ConfigReader.setConfigValue("brand.name", brandName.getText());
        javascriptExecutorClick(visitStoreBtn);
    }

    @FindBy(id = "pg-similar-icon")
    WebElement similarProductsIcon;

    public void clickSimilarProductsIcon() {
        similarProductsIcon.click();
    }

    @FindBy(className = "CarouselWithControls__viewAllBtn")
    WebElement viewAllProductsBtn;

    public void clickViewAllProducts() {
        viewAllProductsBtn.click();
    }

    @FindBy(css = ".Toast__textHolder")
    WebElement successMsg;

    public String isSuccessMsgDisplayed() {
        return successMsg.getText();
    }
}
