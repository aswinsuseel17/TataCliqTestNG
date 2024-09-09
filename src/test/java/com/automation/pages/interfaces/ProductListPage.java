package com.automation.pages.interfaces;

public interface ProductListPage {
    boolean isProductListPageDisplayed();

    boolean isListPageHeadingDisplayed(String configValue);

    void clickFirstProduct();

//    void clickProduct();

    boolean isItemHeadingDisplayed(String configValue);

    void selectSortType(String value);

    boolean isPriceHighToLowSorted();

    void addBrandFilter(String configValue, String filterType);

    boolean isBrandFilterApplied(String configValue);

    void changeView();

    boolean isViewChanged();

    boolean isSimilarProductsDisplayed();

    void clickFeedBack();

    boolean isFeedBackPageDisplayed();

    void enterFeedBack();

    void submitFeedBack();

    String successMsg();

//    void sortPriceLowToHigh();

    boolean isPriceLowToHighSorted();

    void clickRightArrow();

    boolean verifyImage();

    boolean isDiscountSorted();

    boolean isDiscountFilterApplied();

    void scrollUpToFilterByColor();

    void swipeUntilLastColor();

    void selectLastColor();

    void clickViewProducts();

    boolean isFilterByColorApplied();
}
