package com.bddgherkinworkshop.Steps;

import com.bddgherkinworkshop.Context.TestContext;
import com.bddgherkinworkshop.pages.LoginPage;
import com.bddgherkinworkshop.utils.ConfigReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private final TestContext ctx;

    public LoginSteps(TestContext ctx) {   // injecté par PicoContainer
        this.ctx = ctx;
    }
    @Given("I am on the SauceDemo login page")
    public void inLoginPage() {
        new LoginPage(ctx.driver()).ouvrirPage();
    }
    @When("I login with user")
    public void i_login_with_and() {
        new LoginPage(ctx.driver())
                .tentativeCnx(ConfigReader.get("utilisateur.standard"),ConfigReader.get("password"));
    }
    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        assertTrue(ctx.driver().getCurrentUrl().endsWith("/inventory.html"));
        ctx.fermer();
    }

    @When("I login with {string} And {string}")
    public void i_login_with_and(String string, String string2) {
        new LoginPage(ctx.driver())
                .tentativeCnx(string,string2);
    }

    @Then("I should see an error message contain {string}")
    public void i_should_see_an_error_message_contain(String error) {
        assertTrue(new LoginPage(ctx.driver()).getMsgError().contains(error));
        ctx.fermer();

    }
}
