package runners;

import org.testng.Assert;
import pages.NarPosPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;

public class LoginTest {

    public static void main(String[] args) throws IOException {

        // --- Get To NarPos Page
        getToNarPosCom();

        // --- Incorrect Entry Scenario ---
        incorrectEntryScenario();

        // --- Successful Entry Scenario ---
        successfulEntryScenario();
    }

    public static void getToNarPosCom(){
        Driver.getDriver().get("https://narcost.narpos.com.tr/login");
    }

    public static void incorrectEntryScenario() throws IOException {
        NarPosPage narPosPage = new NarPosPage();
        narPosPage.email.sendKeys(ConfigReader.getProperty("wrongMail"));
        narPosPage.password.sendKeys(ConfigReader.getProperty("wrongPassword"));
        narPosPage.submitButton.click();

        ReusableMethods.wait(2);
        // incorrect message
        Assert.assertTrue(narPosPage.inCorrectMessage.isDisplayed());
        System.out.println("Hatalı giriş senaryosu gerçekleşti");
        ReusableMethods.wait(2);
        // Take Screen Shot
        ReusableMethods.getScreenshot("incorrectEntryScenario");
        ReusableMethods.wait(1);

        narPosPage.okayButton.click();

        narPosPage.email.click();
        narPosPage.email.clear();
        narPosPage.password.click();
        narPosPage.password.clear();
    }

    public static void successfulEntryScenario() throws IOException {
        NarPosPage narPosPage = new NarPosPage();


        narPosPage.email.sendKeys(ConfigReader.getProperty("email"));
        narPosPage.password.sendKeys(ConfigReader.getProperty("password"));
        narPosPage.submitButton.click();
        // Verify that the URL contains ‘homepage’
        ReusableMethods.wait(2);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("anasayfa"));
        ReusableMethods.getScreenshot("successfulEntryScenario");
        System.out.println("Başarılı giriş doğrulandı! Mevcut URL: " + Driver.getDriver().getCurrentUrl());
        Driver.getDriver().close();
    }

    public void runTest() throws IOException {
        // --- Get To NarPos Page
        getToNarPosCom();

        // --- Incorrect Entry Scenario ---
        incorrectEntryScenario();

        // --- Successful Entry Scenario ---
        successfulEntryScenario();
    }
}
