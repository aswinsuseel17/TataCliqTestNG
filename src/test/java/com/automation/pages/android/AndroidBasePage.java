package com.automation.pages.android;

import com.automation.utils.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

public class AndroidBasePage {

    WebDriver driver;
    WebDriverWait wait;

    AndroidBasePage() {
        this.driver = DriverManager.getDriver();
        PageFactory.initElements(this.driver, this);
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
    }

    public boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void setImplicitWait(long sec){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }

    public boolean isPresent(WebElement element) {
        try {
            setImplicitWait(0);
            return element.isDisplayed();
        } catch (Exception var3) {
            return false;
        }finally {
            setImplicitWait(20);
        }
    }

    public void scrollOrSwipe(int startX,int startY,int endX,int endY){
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH,"finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO,PointerInput.Origin.viewport(),startX,startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1,Duration.ofSeconds(2)))
                .addAction(finger1.createPointerMove(Duration.ofSeconds(1),PointerInput.Origin.viewport(),endX,endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Sequence is a requestSpecification
        ((AppiumDriver)driver).perform(Collections.singletonList(sequence));
    }
}
