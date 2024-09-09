package com.automation.pages.android;

import com.automation.pages.interfaces.CartPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AndroidCartPage extends AndroidBasePage implements CartPage {

    @FindBy(id = "com.tul.tatacliq:id/text_size")
    WebElement changeSizeDropdown;
    @FindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View")
    List<WebElement> sizeChange;
    @FindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]/android.view.View[4]/android.view.View")
    List<WebElement> quantityChange;
    @FindBy(xpath = "//android.widget.TextView[@text='Done']")
    WebElement doneButton;
    int flag =0;
    @Override
    public void changeSize() throws InterruptedException {
        changeSizeDropdown.click();
        if(isPresent(sizeChange.get(1))) {
            sizeChange.get(1).click();
        }
        doneButton.click();
        // page loading
        Thread.sleep(5000);
    }

    public void changeQuantity() throws InterruptedException {

        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();


        while (!isPresent(changeSizeDropdown)){
            scrollOrSwipe(width/2, height/2, width/2, height);
        }

        changeSizeDropdown.click();
        if(quantityChange.get(1).isEnabled()) {
            quantityChange.get(1).click();
            flag=1;
        }
        doneButton.click();
        // page loading
        Thread.sleep(5000);
    }

    @FindBy(id = "com.tul.tatacliq:id/text_quantity")
    WebElement changeQuantityDropdown;
    @Override
    public boolean verifySizeAndQuantityUpdated(String size) {
        if(changeSizeDropdown.getText().equals(size)){
            return false;
        }
        if(flag==1 && (!changeQuantityDropdown.getText().equals("1"))){
            return false;
        }
        return true;
    }

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.tul.tatacliq:id/toolbar_title']")
    WebElement myBagHeader;
    @Override
    public boolean isCartPageDisplayed() {
        return isDisplayed(myBagHeader);
    }

    @FindBy(id = "com.tul.tatacliq:id/text_view_remove_product")
    WebElement removeButton;
    @Override
    public void removeItem() {
        removeButton.click();
    }

    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.tul.tatacliq:id/emptyCartLayoutNew']/android.widget.TextView[1]")
    WebElement cartMessage;
    @Override
    public boolean isItemRemoved() {
        return cartMessage.getText().equals("Your shopping cart is empty!");
    }

    @Override
    public void clickContinueShopping() {

    }

    @FindBy(id = "com.tul.tatacliq:id/text_view_my_bag_product_name")
    WebElement title;
    @Override
    public String cartProductTitle() {
        return title.getText();
    }


    @FindBy(xpath = "//androidx.cardview.widget.CardView[@resource-id='com.tul.tatacliq:id/cart_disclaimer_view']/android.widget.RelativeLayout")
    WebElement pageEnd;
    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.tul.tatacliq:id/text_view_my_bag_product_offer_price']")
    List<WebElement> productPrice;
    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.tul.tatacliq:id/processingValue']")
    WebElement processingPrice;
    @FindBy(xpath = "//android.widget.TextView[@text='Total Payable']/following-sibling::android.widget.TextView")
    WebElement totalPayable;

    double actualTotal = 0;

    @Override
    public boolean calculateTotal() {

        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        double expectedTotal = 0;
        for (WebElement price : productPrice) {
            double itemPrice = Double.parseDouble(price.getText().substring(1));
            expectedTotal += itemPrice;
        }

        while (!isPresent(pageEnd)){
            scrollOrSwipe(width/2, height/2, width/2, 0);
        }

        double processingFee = Double.parseDouble(processingPrice.getText().substring(1));
        expectedTotal += processingFee;
        actualTotal = Double.parseDouble(totalPayable.getText().substring(1));
        ConfigReader.setConfigValue("total.price", String.valueOf(actualTotal));
        return expectedTotal == actualTotal;

    }


    @FindBy(xpath = "//android.widget.TextView[@text='Check for coupon']")
    WebElement checkForCouponBtn;
    @Override
    public void clickCheckForCoupons() {

        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        while (!isPresent(pageEnd)){
            scrollOrSwipe(width/2, height/2, width/2, 0);
        }

        String payableAmount = totalPayable.getText().substring(1);
        ConfigReader.setConfigValue("actual.amount",payableAmount);
        checkForCouponBtn.click();
    }

    @FindBy(xpath = "//android.widget.TextView/preceding-sibling::android.widget.TextView[@text='Apply']")
    WebElement applyBtn;
    @FindBy(id = "com.tul.tatacliq:id/txtSecondTermValue")
    WebElement couponPrice;
    @FindBy(className = "android.widget.ImageButton")
    WebElement backBtn;
    @Override
    public void applyCoupon() {
        if(isDisplayed(applyBtn)){
            double amount = Double.parseDouble(couponPrice.getText().substring(1));
            ConfigReader.setConfigValue("coupon.amount", String.valueOf(amount));
            applyBtn.click();
        }
        else{
            backBtn.click();
        }
    }

    @FindBy(xpath = "//android.view.View[@resource-id='com.tul.tatacliq:id/hurrayThanksView']")
    WebElement successMsg;
    @FindBy(xpath = "//android.widget.TextView[@text='Total Payable']/following-sibling::android.widget.TextView")
    WebElement totalPrice;
    @Override
    public boolean isCouponApplied() {
        if(isDisplayed(successMsg)){
            double total = Double.parseDouble(totalPrice.getText().substring(1));
            return successMsg.isDisplayed() && total == Double.parseDouble(
                    ConfigReader.getConfigValue("actual.amount")) -
                    Double.parseDouble(ConfigReader.getConfigValue("coupon.amount"));
        }
        return true;
    }

    @Override
    public void clickLogo() {

    }
}
