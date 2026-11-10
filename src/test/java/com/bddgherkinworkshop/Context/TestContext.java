package com.bddgherkinworkshop.Context;

import com.bddgherkinworkshop.utils.ConfigReader;
import com.bddgherkinworkshop.utils.DemoListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestContext {

    private WebDriver driver;


    public WebDriver driver() {
        if (driver == null) {
            Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder")
                    .setLevel(Level.SEVERE);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-features=PasswordLeakDetection");
            //options.addArguments("--guest");
            options.setExperimentalOption("prefs", Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false,
                    "profile.password_manager_leak_detection", false));
            driver = new ChromeDriver(options);
            // Mode démo : chaque action est encadrée en rouge puis mise en pause (voir DemoListener)
            driver = new EventFiringDecorator<WebDriver>(new DemoListener(driver)).decorate(driver);
            driver.manage().window().maximize();
        }
        return driver;
    }

    public void fermer() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}