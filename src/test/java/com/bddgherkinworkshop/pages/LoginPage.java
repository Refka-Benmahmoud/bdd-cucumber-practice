package com.bddgherkinworkshop.pages;

import com.bddgherkinworkshop.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{
    private final By user = By.cssSelector("[data-test='username']");
    private final By password = By.cssSelector("[data-test='password']");
    private final By btn_login = By.cssSelector("[data-test='login-button']");
    private final By error_msg = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    public LoginPage ouvrirPage(){
        driver.get(ConfigReader.get("baseUrl"));
        return this;
    }
    public LoginPage tentativeCnx(String username,String userPassword){
        saisir(user, username);
        saisir(password, userPassword);
        cliquer(btn_login);
        return this;
    }
    public String getMsgError(){
        return lireText(error_msg);
    }
}
