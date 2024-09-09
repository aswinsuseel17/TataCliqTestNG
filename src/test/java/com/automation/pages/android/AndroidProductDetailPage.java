package com.automation.pages.android;

import com.automation.pages.interfaces.ProductDetailPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AndroidProductDetailPage extends AndroidBasePage implements ProductDetailPage {

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Product Name']")
    WebElement title;
    @Override
    public boolean isTheClickedProductDisplayed() {
        return title.getText().equals(ConfigReader.getConfigValue("product.name"));
    }

    @FindBy(xpath = "//android.widget.TextView[@content-desc='Add to Bag']")
    WebElement addToCartBtn;
    @Override
    public void clickAddToCart() {
        addToCartBtn.click();
    }

    @FindBy(id = "com.tul.tatacliq:id/cartBadge")
    WebElement cartCount;
    @Override
    public String verifyCartCount() {
        return cartCount.getText();
    }

    @FindBy(id = "com.tul.tatacliq:id/item_bag")
    WebElement cartIcon;
    @Override
    public void clickCartIcon() {
        cartIcon.click();
    }

    @FindBy(xpath = "//android.widget.TextView/preceding-sibling::android.widget.TextView[contains(@text,'Offers')]")
    WebElement offers;
    @FindBy(id = "com.tul.tatacliq:id/llShape")
    List<WebElement> sizeList;
    @Override
    public void selectSize(String key) {
        //Scroll Logic
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        while (!isPresent(offers)){
            scrollOrSwipe(width / 2, height / 2, width / 2, 0);
        }

        ConfigReader.setConfigValue(key, sizeList.get(0).getText());
        sizeList.get(0).click();
    }

    @Override
    public void clickMoreProductInfo() {
    }

    @Override
    public boolean isProductInfoDisplayed() {
        return false;
    }


    @FindBy(xpath = "//android.view.ViewGroup[@resource-id='com.tul.tatacliq:id/titleLayout']")
    WebElement styleWithContainer;
    @FindBy(id = "com.tul.tatacliq:id/tv_visit_store_two")
    WebElement visitStore;
    @Override
    public void clickVisitStore() {

        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        while (!isPresent(styleWithContainer)){
            scrollOrSwipe(width/2, height/2, width/2, 0);
        }

        visitStore.click();

    }

    @FindBy(id = "com.tul.tatacliq:id/imgVSimilarProd")
    WebElement similarProducts;
    @Override
    public void clickSimilarProductsIcon() {
        similarProducts.click();
    }

    @Override
    public void clickViewAllProducts() {
    }

    @FindBy(xpath = " //android.widget.Toast[@text='This item has been added to My Bag']")
    WebElement successMsg;
    @Override
    public String isSuccessMsgDisplayed() {
        return successMsg.getText();
    }
}
