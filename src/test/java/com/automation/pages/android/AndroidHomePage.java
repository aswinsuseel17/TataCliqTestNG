package com.automation.pages.android;

import com.automation.pages.interfaces.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AndroidHomePage extends AndroidBasePage implements HomePage {

//    @FindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[1]")
//    WebElement goToHomePage;
    @FindBy(xpath = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View[2]")
    WebElement goToHomePage;
    public void openWebsite(){
        goToHomePage.click();
    }

    @FindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
    WebElement denyButton;
    @FindBy(id = "android:id/button2")
    WebElement closeAdd;
    @FindBy(id = "android:id/button1")
    WebElement skipNowBtn;
    @FindBy(className = "android.widget.ImageView")
    WebElement closeButton;
    @Override
    public void closePopUp() {
        denyButton.click();
        if(isDisplayed(skipNowBtn)){
            skipNowBtn.click();
        }
        if(isDisplayed(closeAdd)){
            closeAdd.click();
        }
        closeButton.click();
    }


    @FindBy(id = "com.tul.tatacliq:id/rl_tv_prominent")
    WebElement searchBox;
    @Override
    public boolean isHomePageDisplayed() {
        return searchBox.isDisplayed();
    }

    @Override
    public void chooseBrand() {

    }

//    @FindBy(xpath = "//android.widget.FrameLayout[@content-desc='Categories']")
    String categoryBtn="//android.widget.FrameLayout[@content-desc='%s']";
    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.tul.tatacliq:id/cardCategoryItem']")
    List<WebElement> categoryList;
    @FindBy(xpath = "//android.widget.TextView[@text='Casual Wear']")
    WebElement casualWears;
//    @FindBy(xpath = "//android.widget.TextView[@text='T-shirts']/preceding-sibling::android.widget.FrameLayout")
    String tShirtBtn="//android.widget.TextView[@text='%s']/preceding-sibling::android.widget.FrameLayout";
    @Override
    public void searchCategory(String categoryItem,String categoryType) {
        driver.findElement(By.xpath(String.format(categoryBtn,categoryType))).click();
        categoryList.get(1).click();
        casualWears.click();
        driver.findElement(By.xpath(String.format(tShirtBtn,categoryItem))).click();
    }

    @Override
    public void clickAboutUs() {

    }

    @Override
    public boolean isAboutUsDisplayed() {
    return false;
    }

    @FindBy(xpath = "//android.widget.AutoCompleteTextView[@resource-id='android:id/search_src_text']")
    WebElement searchInput;
    @Override
    public void searchItem(String key) {
        searchBox.click();
        searchInput.sendKeys(key);
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ENTER).pause(1000).keyUp(Keys.ENTER).build().perform();
    }

    @Override
    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickTataLuxury(){
    }

    @Override
    public boolean isLuxuryPageTitleDisplayed() {
        return false;
    }

    @Override
    public void clickContactUS() {

    }

    @Override
    public boolean isContactUsDisplayed() {
        return false;
    }
}
