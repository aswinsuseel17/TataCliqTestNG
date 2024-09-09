package com.automation.pages.android;

import com.automation.pages.interfaces.ProductListPage;
import com.automation.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AndroidProductListPage extends AndroidBasePage implements ProductListPage {

    @FindBy(xpath = "//android.widget.TextView[@text='Sort']")
    WebElement sortButton;
    @FindBy(id = "com.tul.tatacliq:id/textViewRefine")
    WebElement filterButton;

    @Override
    public boolean isProductListPageDisplayed() {
        return isDisplayed(sortButton) && isDisplayed(filterButton);
    }

    @Override
    public boolean isListPageHeadingDisplayed(String configValue) {
        return true;
    }


    @FindBy(xpath = "//android.widget.LinearLayout/android.widget.RelativeLayout/following-sibling::android.widget.LinearLayout/android.widget.TextView[2]")
    List<WebElement> titleList;
    @FindBy(id = "com.tul.tatacliq:id/llGridListView")
    WebElement viewButton;

    @Override
    public void clickFirstProduct() {
        if (isDisplayed(viewButton)) {
            viewButton.click();
        }
        ConfigReader.setConfigValue("product.name", titleList.get(0).getText());
        titleList.get(0).click();
    }

//    @Override
//    public void clickProduct() {
//    }


    @FindBy(id = "com.tul.tatacliq:id/toolbar_icon_title")
    WebElement heading;

    @Override
    public boolean isItemHeadingDisplayed(String configValue) {
        return isDisplayed(heading);
    }


    //    @FindBy(xpath = "//android.widget.TextView[@text='Price High to Low']")
    String sortOption = "//android.widget.TextView[@text='%s']";

    @Override
    public void selectSortType(String sortType) {
        sortButton.click();
        driver.findElement(By.xpath(String.format(sortOption, sortType))).click();
    }


    @FindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.tul.tatacliq:id/linearLayoutPriceInfo']/android.widget.TextView[1]")
    List<WebElement> priceList;

    @Override
    public boolean isPriceHighToLowSorted() {

        int i = 0;

        //Scroll Logic
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        do {
            List<Double> newPriceList = new ArrayList<>();
            for (WebElement price : priceList) {
                newPriceList.add(Double.parseDouble(price.getText().substring(1).replace(",", "")));
            }
            List<Double> copyPriceList = new ArrayList<>(newPriceList);
            Collections.sort(copyPriceList);
            Collections.reverse(copyPriceList);

            if (!newPriceList.equals(copyPriceList)) {
                return false;
            }

            scrollOrSwipe(width / 2, height / 2, width / 2, 0);

            i++;

        } while (i <= 5);

        return true;
    }

    @FindBy(id = "com.tul.tatacliq:id/searchEditText")
    WebElement brandInput;
    @FindBy(id = "com.tul.tatacliq:id/textViewFilterValueName")
    WebElement searchResult;
    @FindBy(id = "com.tul.tatacliq:id/txtShowResults")
    WebElement showResult;

    @Override
    public void addBrandFilter(String filter, String filterType) {
        filterButton.click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.tul.tatacliq:id/txtFilterKey' and @text='" + filterType + "']")).click();
        if (isDisplayed(brandInput)) {
            brandInput.sendKeys(filter);
            searchResult.click();
        } else {
            driver.findElement(By.xpath("//android.widget.TextView[@text='" + filter + "']")).click();
        }
        showResult.click();
    }

    @FindBy(id = "com.tul.tatacliq:id/appCompatTextView2")
    WebElement giveFeedback;
    @FindBy(xpath = "//android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[1]")
    List<WebElement> brandHeading;

    @Override
    public boolean isBrandFilterApplied(String filterBrand) {
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();
        do {
            List<String> brandList = new ArrayList<>();
            for (WebElement brand : brandHeading) {
                brandList.add(brand.getText());
            }
            for (String brandName : brandList) {
                if (!brandName.equals(filterBrand)) {
                    return false;
                }
            }
            scrollOrSwipe(width / 2, height / 2, width / 2, 0);

        } while (!isPresent(giveFeedback));
        return true;

    }

    @FindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.tul.tatacliq:id/relativeLayout']")
    List<WebElement> productContainer;
    int containerWidth;
    Dimension containerDimension;

    @Override
    public void changeView() {
        containerDimension = productContainer.get(0).getSize();
        containerWidth = containerDimension.getWidth();
        viewButton.click();
    }

    @Override
    public boolean isViewChanged() {
        containerDimension = productContainer.get(0).getSize();
        return containerWidth != containerDimension.getWidth();
    }

    @FindBy(xpath = "//android.widget.TextView[@text='Similar Products']")
    WebElement similarProductHeader;

    @Override
    public boolean isSimilarProductsDisplayed() {
        return similarProductHeader.isDisplayed();
    }


    @FindBy(id = "com.tul.tatacliq:id/appCompatTextView3")
    WebElement feedbackText;
    @FindBy(id = "com.tul.tatacliq:id/appCompatTextView2")
    WebElement feedbackBtn;

    @Override
    public void clickFeedBack() {

        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        while (!isPresent(feedbackText)) {
            scrollOrSwipe(width / 2, height / 2, width / 2, 0);
        }

        feedbackBtn.click();

    }

    @FindBy(id = "com.tul.tatacliq:id/toolbar_title")
    WebElement feedbackForm;

    @Override
    public boolean isFeedBackPageDisplayed() {
        return feedbackForm.isDisplayed();
    }

    @FindBy(xpath = "//android.widget.ImageView[@resource-id='com.tul.tatacliq:id/iv_emoji_3']")
    List<WebElement> feedBackSmiley;

    @Override
    public void enterFeedBack() {
        for (WebElement smiley : feedBackSmiley) {
            smiley.click();
        }
    }

    @FindBy(id = "com.tul.tatacliq:id/comments_textbox")
    WebElement textInput;
    @FindBy(id = "com.tul.tatacliq:id/tv_submit")
    WebElement submitBtn;

    @Override
    public void submitFeedBack() {
        textInput.sendKeys(ConfigReader.getConfigValue("text"));
        submitBtn.click();
    }

    @FindBy(id = "com.tul.tatacliq:id/textView3")
    WebElement successMsg;

    @Override
    public String successMsg() {
        return successMsg.getText();
    }


    @Override
    public boolean isPriceLowToHighSorted() {
        int i = 0;

        //Scroll Logic
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        do {
            List<Double> newPriceList = new ArrayList<>();
            for (WebElement price : priceList) {
                newPriceList.add(Double.parseDouble(price.getText().substring(1).replace(",", "")));
            }
            List<Double> copyPriceList = new ArrayList<>(newPriceList);
            Collections.reverse(copyPriceList);
            Collections.sort(copyPriceList);

            if (!newPriceList.equals(copyPriceList)) {
                return false;
            }

            scrollOrSwipe(width / 2, height / 2, width / 2, 0);

            i++;

        } while (i <= 5);

        return true;
    }

    @Override
    public void clickRightArrow() {

    }

    @Override
    public boolean verifyImage() {
        return false;
    }

    @Override
    public boolean isDiscountSorted() {
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = (int)(dimension.getHeight()*0.75);
        int max = Integer.MAX_VALUE;
        do {
            for (WebElement discount : discountList) {
                int value = Integer.parseInt(discount.getText().substring(0, 2));
                System.out.println(value);
                if (value > max) {
                    return false;
                }
                max = value;
            }
            scrollOrSwipe(width / 2, height, width / 2, 0);
        } while (!isPresent(giveFeedback));
        return true;
    }

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.tul.tatacliq:id/text_discount']")
    List<WebElement> discountList;

    @Override
    public boolean isDiscountFilterApplied() {
        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();
        do {
            for (WebElement discount : discountList) {
                int value = Integer.parseInt(discount.getText().substring(0, 2));
                if (value < 50 || value > 70) {
                    return false;
                }
            }
            scrollOrSwipe(width / 2, height / 2, width / 2, 0);
        } while (!isPresent(giveFeedback));
        return true;
    }


    @FindBy(xpath = "//android.widget.ImageView[@resource-id='com.tul.tatacliq:id/ivColor']")
    WebElement colors;

    @Override
    public void scrollUpToFilterByColor() {

        Dimension dimension = driver.manage().window().getSize();
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        while (!isPresent(colors)) {
            scrollOrSwipe(width / 2, height / 2, width / 2, 0);
        }
    }


    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.tul.tatacliq:id/tvMore']")
    WebElement moreBtn;
    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.tul.tatacliq:id/rvFilterValues']")
    WebElement filterDiv;
    @FindBy(xpath = "//androidx.recyclerview.widget.RecyclerView[@resource-id='com.tul.tatacliq:id/rvFilterValues']/android.widget.LinearLayout")
    List<WebElement> coloursList;

    @Override
    public void swipeUntilLastColor() {

        int startX = coloursList.get(coloursList.size() - 1).getLocation().getX();
        int startY = coloursList.get(coloursList.size() - 1).getLocation().getY();

        while (!isPresent(moreBtn)) {
            scrollOrSwipe(startX, startY, startX - 900, startY);
        }
    }


    @Override
    public void selectLastColor() {
        ConfigReader.setConfigValue("filter.color", coloursList.get(coloursList.size() - 1).getText());
        coloursList.get(coloursList.size() - 1).click();
    }

    @FindBy(xpath = "//android.widget.TextView[@resource-id='com.tul.tatacliq:id/btnViewProducts']")
    WebElement viewProductsBtn;

    @Override
    public void clickViewProducts() {
        viewProductsBtn.click();
    }

    @FindBy(id = "com.tul.tatacliq:id/textViewProductName")
    List<WebElement> productTitles;

    @Override
    public boolean isFilterByColorApplied() {
        String color = ConfigReader.getConfigValue("filter.color");
        return productTitles.get(0).getText().contains(color);
    }
}
