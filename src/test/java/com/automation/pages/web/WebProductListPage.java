package com.automation.pages.web;


import com.automation.pages.interfaces.ProductListPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class WebProductListPage extends WebBasePage implements ProductListPage {

    @FindBy(css = ".SelectBoxDesktop__boxIconLeft")
    WebElement sortByText;
    @FindBy(className = "FilterDesktop__filterClearTxt1")
    WebElement filterOptn;

    public boolean isProductListPageDisplayed() {
        return sortByText.isDisplayed() && filterOptn.isDisplayed();
    }

    @FindBy(tagName = "h1")
    WebElement headingText;

    public boolean isListPageHeadingDisplayed(String product) {
        return headingText.getText().toLowerCase().contains(product.toLowerCase());
    }

    @FindBy(xpath = "//div[@class='ProductModule__content']/div/div/h2")
    List<WebElement> productList;

    public void clickFirstProduct() {
        ConfigReader.setConfigValue("product.name", productList.get(0).getText());
        javascriptExecutorClick(productList.get(0));
        String currentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            if (!handle.equals(currentWindow)) {
                driver.switchTo().window(handle);
            }
        }
    }


//    @FindBy(css = ".ProductDetailsMainCard__productName")
//    WebElement title;
//    int count=0;
//    public void clickProduct() {
//        for(int i=0;i<priceList.size();i++){
//            ConfigReader.setConfigValue("product.name", productList.get(i).getText());
//            javascriptExecutorClick(productList.get(i));
//            String currentWindow = driver.getWindowHandle();
//            Set<String> windowHandles = driver.getWindowHandles();
//            for (String handle : windowHandles) {
//                if (!handle.equals(currentWindow)) {
//                    driver.switchTo().window(handle);
//                }
//            }
//            if(title.isDisplayed()){
//                count++;
//            }
//            driver.close();
//            driver.switchTo().window(currentWindow);
//        }
//        System.out.println(count);
//    }


    @FindBy(className = "Plp__camelCase")
    WebElement headsetPageHeading;

    public boolean isItemHeadingDisplayed(String product) {
        return headsetPageHeading.getText().contains(product);
    }

    @FindBy(className = "SelectBoxDesktop__hideSelect")
    WebElement sortByBtn;

    public void selectSortType(String sortType) {
        Select sortBy = new Select(sortByBtn);
        sortBy.selectByVisibleText(sortType);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @FindBy(xpath = "//div[@class='ProductDescription__discount ProductDescription__priceHolder']/h3")
    List<WebElement> priceList;

    public boolean isPriceHighToLowSorted() {

        List<Double> newPriceList = new ArrayList<>();

        for (WebElement price : priceList) {
            String str = price.getText();
            newPriceList.add(Double.valueOf(str.substring(1)));

        }
        List<Double> copyPriceList = new ArrayList<>(newPriceList);
        Collections.sort(copyPriceList);
        Collections.reverse(copyPriceList);

        return newPriceList.equals(copyPriceList);

    }

    public boolean isPriceLowToHighSorted() {

        List<Double> newPriceList = new ArrayList<>();

        for (WebElement price : priceList) {
            String str = price.getText();
            newPriceList.add(Double.valueOf(str.substring(1)));

        }
        List<Double> copyPriceList = new ArrayList<>(newPriceList);
        Collections.reverse(copyPriceList);
        Collections.sort(copyPriceList);

        return newPriceList.equals(copyPriceList);

    }

    String filterPlusIcon = "//div[text()='%s']/following-sibling::div";
    @FindBy(xpath = "//input[@placeholder='Search by brands']")
    WebElement brandInput;

    public void addBrandFilter(String choice, String filter) {
        driver.findElement(By.xpath(String.format(filterPlusIcon, filter))).click();
        //brandInput.sendKeys(choice);
        driver.findElement(By.xpath("//div[text()='" + choice + "']")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @FindBy(xpath = "//div[@class='ProductDescription__header']")
    List<WebElement> brandProductHeaderList;

    public boolean isBrandFilterApplied(String productName) {
        for (WebElement product : brandProductHeaderList) {
            if (!product.getText().equals(productName)) {
                return false;
            }
        }
        return true;
    }

    @FindBy(xpath = "//div[@class='Plp__icon']/div/div")
    WebElement viewChangeButton;

    public void changeView() {
        viewChangeButton.click();
    }

    @FindBy(xpath = "(//div[@class='Grid__element'])[1]")
    WebElement viewElement;

    public boolean isViewChanged() {
        String style = viewElement.getAttribute("style");
        String widthValue = null;
        if (style != null) {
            String[] styles = style.split(";");
            for (String s : styles) {
                if (s.trim().startsWith("width:")) {
                    widthValue = s.split(":")[1].trim();
                    break;
                }
            }
        }
        return widthValue.equals("33.33%");
    }

    @FindBy(css = ".rplp__rplpHead")
    WebElement similarProductsHeading;

    public boolean isSimilarProductsDisplayed() {
        return similarProductsHeading.isDisplayed();
    }

    @FindBy(xpath = "//div[@class='FeedbackExperienceWidget__line2']/strong")
    WebElement giveFeedBackBtn;

    public void clickFeedBack() {
        giveFeedBackBtn.click();
    }

    @FindBy(css = ".FeedbackExperienceForm__next_box")
    WebElement nextButton;

    public boolean isFeedBackPageDisplayed() {
        return nextButton.isDisplayed();
    }

    @FindBy(xpath = "//div[@class='FeedbackExperienceForm__emojis'][3]")
    List<WebElement> feedBackSmiley;

    public void enterFeedBack() {
        for (WebElement smiley : feedBackSmiley) {
            smiley.click();
        }
        nextButton.click();
    }

    @FindBy(className = "FeedbackExperienceForm__feedback_box")
    WebElement textInput;
    @FindBy(xpath = "//div[text()='Submit']")
    WebElement submitButton;

    public void submitFeedBack() {
        textInput.sendKeys(ConfigReader.getConfigValue("text"));
        submitButton.click();
    }

    @FindBy(className = "feedbackExperienceSubmitMessage__thankyouText")
    WebElement successMsg;

    @Override
    public String successMsg() {
        return successMsg.getText();
    }

    @FindBy(xpath = "//span[@class='ProductDescription__newDiscountPercent']")
    List<WebElement> discountList;

    public boolean isDiscountFilterApplied() {
        for (WebElement discount : discountList) {
            int value = Integer.parseInt(discount.getText().substring(0, 2));
            if (value < 50 || value > 70) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void scrollUpToFilterByColor() {

    }

    @Override
    public void swipeUntilLastColor() {

    }

    @Override
    public void selectLastColor() {

    }

    @Override
    public void clickViewProducts() {

    }

    @Override
    public boolean isFilterByColorApplied() {
        return false;
    }


    @FindBy(css = ".ProductModule__base")
    List<WebElement> productCardList;
    @FindBy(xpath = "//div[@class='Image__base Image__baseScroll']/img")
    List<WebElement> imageList;
    @FindBy(className = "ProductModule__arrowBoxTwo")
    WebElement rightArrow;
    String link1;

    public void clickRightArrow() {
        link1 = imageList.get(0).getAttribute("src");
        Actions actions = new Actions(driver);
        actions.moveToElement(productCardList.get(0)).pause(1000).build().perform();
        actions.moveToElement(rightArrow).pause(1000).click().build().perform();
    }

    public boolean verifyImage() {
        String link2 = imageList.get(0).getAttribute("src");
        return (!link1.equals(link2));
    }

    public boolean isDiscountSorted(){
        int max = Integer.MAX_VALUE;
        for (WebElement discount : discountList) {
            int value = Integer.parseInt(discount.getText().substring(0, 2));
            if (value > max) {
                return false;
            }
            max = value;
        }
        return true;
    }
}
