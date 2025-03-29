package runners;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import pages.NarPosPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JSUtilities;
import utilities.ReusableMethods;
import org.openqa.selenium.*;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class Runner {
    private static WebDriverWait wait;
    public static void main(String[] args) throws IOException {
        NarPosPage narPosPage = new NarPosPage();

        // --- Get to Narpos.com ---
        getToNarPosCom();

        // --- Incorrect Entry Scenario ---
       //    incorrectEntryScenario();
        Driver.closeDriver();

        // --- Get to Narpos.com ---
        getToNarPosCom();
        // --- Successful Entry Scenario ---
        successfulEntryScenario();

        // --- Delete Domates Stock ---
         deleteDomatesStock();

        // --- Add New Stock Card ---
         addNewStockCard();

       // --- test Stock Organisation ---
        testStockOrganisation();






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
        ReusableMethods.wait(2);
        // Take Screen Shot
     //   ReusableMethods.getScreenshot("incorrectEntryScenario");
        Driver.closeDriver();
    }
    public static void successfulEntryScenario(){
        NarPosPage narPosPage = new NarPosPage();

        narPosPage.email.sendKeys(ConfigReader.getProperty("email"));
        narPosPage.password.sendKeys(ConfigReader.getProperty("password"));
        narPosPage.submitButton.click();
        // Verify that the URL contains ‘homepage’
        ReusableMethods.wait(2);
        Assert.assertTrue(Driver.getDriver().getCurrentUrl().contains("anasayfa"));
        System.out.println("Başarılı giriş doğrulandı! Mevcut URL: " + Driver.getDriver().getCurrentUrl());

    }
    public static void deleteDomatesStock() {
        NarPosPage narPosPage = new NarPosPage();

        narPosPage.anaVeriler.click();
        ReusableMethods.wait(1);
        narPosPage.stokKartlari.click();

        try {
            if (narPosPage.domates.isDisplayed()) {
                // Sağdaki 3 noktaya tıkla
                narPosPage.menuButton.click();
                // "DOMATES" stok listesinde hala var mı kontrol et
                boolean isDomatesStillThere = narPosPage.domates.isDisplayed();
                assertFalse("DOMATES stoğu hala listede görünüyor!", isDomatesStillThere);
            } else {
                System.out.println("DOMATES stoğu bulunamadı.");
                ReusableMethods.getScreenshot("notfoundDomates");
            }
        } catch (NoSuchElementException e) {
            System.out.println("DOMATES stoğu bulunamadı.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addNewStockCard() throws IOException {
        NarPosPage narPosPage = new NarPosPage();

            // Click on the ‘Add New’ button
            narPosPage.newAdd.click();

            // Enter stock information
            JSUtilities.clickWithJS(Driver.getDriver(), narPosPage.stockType);
            narPosPage.another.click();
            ReusableMethods.wait(1);
            narPosPage.nameNewStock.sendKeys("DOMATES");
            JSUtilities.scrollToElement(Driver.getDriver(), narPosPage.stockCode);
            narPosPage.stockCode.clear();
            narPosPage.stockCode.sendKeys("DOMATES");

            JSUtilities.scrollToElement(Driver.getDriver(), narPosPage.addGroup);
            ReusableMethods.wait(1);
            JSUtilities.clickWithJS(Driver.getDriver(), narPosPage.group1);
            narPosPage.food.click();

            ReusableMethods.wait(1);
            JSUtilities.scrollToElement(Driver.getDriver(), narPosPage.tax);
            narPosPage.tax.click();
            ReusableMethods.wait(1);
            narPosPage.kdv1.click();

            JSUtilities.scrollToElement(Driver.getDriver(), narPosPage.selectBasicUnit);
            ReusableMethods.wait(1);
            narPosPage.selectBasicUnit.click();
            JSUtilities.clickWithJS(Driver.getDriver(),narPosPage.kilogram);

            JSUtilities.clickWithJS(Driver.getDriver(), narPosPage.conversionUnit);

            // Click on the ‘Save and continue’ button
            narPosPage.saveAndContinue.click();
            ReusableMethods.wait(5);

            // Check operation successful message
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'İşlem Başarılı')]")));
            Assert.assertTrue(successMessage.isDisplayed(), "Test Passed: İşlem Başarılı");
//

            if (narPosPage.successMessage.isDisplayed()) {
                System.out.println("Test Passed: DOMATES listeye eklendi.");
            //    ReusableMethods.getScreenshot("İşlem Başarılı");

            } else {
                System.out.println("Test Fail: DOMATES listeye eklenemedi.");
            }
            narPosPage.okay.click();
            narPosPage.close.click();
            narPosPage.yes.click();
    }
    public static void testStockOrganisation() throws IOException {
        NarPosPage narPosPage = new NarPosPage();
        // Click on the 3 dots to the right of the stock card
        narPosPage.menuThreePoints.click();

        // Click on the ‘Edit’ button
       narPosPage.edit.click();

        // EditStockCard
        narPosPage.nameNewStock.clear();
        ReusableMethods.wait(1);
        narPosPage.nameNewStock.sendKeys("DOMATES-REVIZE");
        JSUtilities.scrollToElement(Driver.getDriver(), narPosPage.stockCode);
        narPosPage.stockCode.clear();
        narPosPage.stockCode.sendKeys("DOMATES-REV");

        JSUtilities.scrollToElement(Driver.getDriver(), narPosPage.editGroup);
        ReusableMethods.wait(1);
        JSUtilities.clickWithJS(Driver.getDriver(), narPosPage.group1);
        narPosPage.gida.click();

        // click save
        narPosPage.save.click();

        // Wait for the operation successful message and verify
        if (narPosPage.successMessage.isDisplayed()) {
            System.out.println("Test Passed: Stok karti düzenlendi.");
        //    ReusableMethods.getScreenshot("İşlem Başarılı");
        } else {
            System.out.println("Test Failed: DOMATES listeye eklenemedi.");
        }
        ReusableMethods.wait(10);

        // Check the list
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'İşlem Başarılı')]")));
        Assert.assertTrue(successMessage.isDisplayed(), "Test Passed: Stok Kartı başarıyla güncellenmiştir.");

        // Refresh page (F5)
        Driver.getDriver().navigate().refresh();

        // Check if changes are preserved on the renewed page
        String actual = narPosPage.refreshdRow1.getText();
        String expected = "DOMATES-REVIZE";
        Assert.assertTrue(actual.equals(expected));
        // ReusableMethods.getScreenshot("testStockOrganisation");
        System.out.println("Test Passed: Stok kartı başarıyla güncellendi ve doğrulandı.");
    }
}
