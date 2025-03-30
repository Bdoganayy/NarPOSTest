package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.NarPosPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;

public class LoginStepdefinitions {

    NarPosPage narPosPage = new NarPosPage();

    @Given("User navigates to the login page")
    public void user_navigates_to_the_login_page() {
        Driver.getDriver().get("https://narcost.narpos.com.tr/login");
    }

    @When("User enters incorrect email and password")
    public void user_enters_incorrect_email_and_password() throws IOException {
        narPosPage.email.sendKeys(ConfigReader.getProperty("wrongMail"));
        narPosPage.password.sendKeys(ConfigReader.getProperty("wrongPassword"));
        narPosPage.submitButton.click();
        ReusableMethods.wait(2);
    }

    @Then("User should see an incorrect login message")
    public void user_should_see_an_incorrect_login_message() throws IOException {
        Assert.assertTrue(narPosPage.inCorrectMessage.isDisplayed());
        System.out.println("Hatalı giriş senaryosu gerçekleşti");
      //  ReusableMethods.getScreenshot("incorrectEntryScenario");
        narPosPage.okayButton.click();
        Driver.closeDriver();
    }

    @When("User enters correct email and password")
    public void user_enters_correct_email_and_password() throws IOException {
        narPosPage.email.sendKeys(ConfigReader.getProperty("email"));
        narPosPage.password.sendKeys(ConfigReader.getProperty("password"));
        narPosPage.submitButton.click();
        ReusableMethods.wait(2);
    }

    @Then("User should be redirected to the homepage")
    public void user_should_be_redirected_to_the_homepage() throws IOException {
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("anasayfa"));
      //  ReusableMethods.getScreenshot("successfulEntryScenario");
        System.out.println("Başarılı giriş doğrulandı! Mevcut URL: " + Driver.getDriver().getCurrentUrl());
        Driver.getDriver().close();
    }
}
