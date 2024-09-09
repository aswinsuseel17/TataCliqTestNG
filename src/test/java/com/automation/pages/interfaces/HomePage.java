package com.automation.pages.interfaces;

import org.openqa.selenium.WebElement;

public interface HomePage {
    void openWebsite();

    void closePopUp();

    boolean isHomePageDisplayed();

    void chooseBrand();

    void searchCategory(String categoryItem,String categoryType);

    void clickAboutUs();

    boolean isAboutUsDisplayed();

    void searchItem(String configValue);

    boolean isDisplayed(WebElement element);

    void clickTataLuxury();

    boolean isLuxuryPageTitleDisplayed();

    void clickContactUS();

    boolean isContactUsDisplayed();
}
