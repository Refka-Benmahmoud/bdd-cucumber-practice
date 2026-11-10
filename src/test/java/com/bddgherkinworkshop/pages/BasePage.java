package com.bddgherkinworkshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
  protected final WebDriver driver;
  protected final WebDriverWait wait;

  protected BasePage(WebDriver driver){
      this.driver = driver;
      this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
  }
  protected void saisir(By by, String text){
      WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
      e.clear();
      e.sendKeys(text);
  }
  protected void cliquer(By by){
      wait.until(ExpectedConditions.elementToBeClickable(by)).click();
  }
  protected String lireText(By by)
  {
     return wait.until(ExpectedConditions.visibilityOfElementLocated(by)).getText();
  }

}
