package com.bddgherkinworkshop.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class DemoListener implements WebDriverListener {

    // Durée de chaque pause en millisecondes (1000 = 1 seconde)
    private static final long PAUSE_MS = 1000;

    private final WebDriver driver;   // navigateur d'origine, utilisé pour encadrer les éléments

    public DemoListener(WebDriver driver) {
        this.driver = driver;
    }

    // ---------- Actions sur les éléments ----------

    @Override
    public void beforeClick(WebElement element) {
        surligner(element);
    }

    @Override
    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        surligner(element);
    }

    @Override
    public void beforeClear(WebElement element) {
        surligner(element);
    }

    // ---------- Navigation ----------

    @Override
    public void afterGet(WebDriver driver, String url) {
        pause(PAUSE_MS);
    }

    @Override
    public void afterTo(WebDriver.Navigation navigation, String url) {
        pause(PAUSE_MS);
    }

    @Override
    public void afterBack(WebDriver.Navigation navigation) {
        pause(PAUSE_MS);
    }

    @Override
    public void afterForward(WebDriver.Navigation navigation) {
        pause(PAUSE_MS);
    }

    @Override
    public void afterRefresh(WebDriver.Navigation navigation) {
        pause(PAUSE_MS);
    }

    // ---------- Fin du test ----------

    @Override
    public void beforeQuit(WebDriver driver) {
        pause(2 * PAUSE_MS);   // laisser voir le résultat final avant de fermer Chrome
    }

    // ---------- Outils ----------

    /** Encadre l'élément en rouge, attend, puis retire le cadre. */
    private void surligner(WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.outline = '3px solid red';", element);
            pause(PAUSE_MS);
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.outline = '';", element);
        } catch (RuntimeException e) {
            // élément caché, disparu ou page en cours de changement : on laisse Selenium continuer
        }
    }

    private void pause(long millisecondes) {
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}