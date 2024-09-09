package com.automation.pages.interfaces;

public interface CartPage {
    void changeSize() throws InterruptedException;

    void changeQuantity() throws InterruptedException;

    boolean verifySizeAndQuantityUpdated(String configValue);

    boolean isCartPageDisplayed();

    void removeItem();

    boolean isItemRemoved();

    void clickContinueShopping();

    String cartProductTitle();

    boolean calculateTotal();

    void clickCheckForCoupons();

    void applyCoupon();

    boolean isCouponApplied();

    void clickLogo();
}
