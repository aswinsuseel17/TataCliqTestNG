package com.automation.pages.interfaces;

public interface ProductDetailPage {

    boolean isTheClickedProductDisplayed();

    void clickAddToCart();

    String verifyCartCount();

    void clickCartIcon();

    void selectSize(String key);

    void clickMoreProductInfo();

    boolean isProductInfoDisplayed();

    void clickVisitStore();

    void clickSimilarProductsIcon();

    void clickViewAllProducts();

    String isSuccessMsgDisplayed();
}
