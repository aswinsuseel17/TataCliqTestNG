package com.automation.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.time.Duration;

public class DriverManager {
    static WebDriver driver;
    public static void createDriver(){
        if(ConfigReader.getConfigValue("platform").equals("Web")) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        else{
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", ConfigReader.getConfigValue("platform.name"));
            capabilities.setCapability("deviceName", ConfigReader.getConfigValue("device.name"));
            capabilities.setCapability("automationName", ConfigReader.getConfigValue("automation.name"));
            capabilities.setCapability("app",System.getProperty("user.dir")+ ConfigReader.getConfigValue("app.path"));
            capabilities.setCapability("appActivity", ConfigReader.getConfigValue("app.activity"));
            capabilities.setCapability("appPackage", ConfigReader.getConfigValue("app.package"));
            //capabilities.setCapability("orientation","LANDSCAPE");

            driver = new AppiumDriver(capabilities);

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    public static WebDriver getDriver(){
        return driver;
    }
}
