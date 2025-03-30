package runners;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.NarPosPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JSUtilities;
import utilities.ReusableMethods;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class StockCardManagementTest {
    public static void main(String[] args) throws IOException {


        // --- Get to Narpos.com ---
        getToNarPosCom();

        // --- Delete Domates Stock ---
        deleteDomatesStock();

        // --- Add New Stock Card ---
        addNewStockCard();

        // --- Test Stock Organisation ---
        testStockOrganisation();

        // --- Stock Cart Delete Test ---
        stockCartDeleteTest();

        // Inventory And Invoice Test
        inventoryAndInvoiceTest();

    }

    private static void getToNarPosCom() {
        Driver.getDriver().get("https://narcost.narpos.com.tr/login");
        NarPosPage narPosPage = new NarPosPage();

        narPosPage.email.sendKeys(ConfigReader.getProperty("email"));
        narPosPage.password.sendKeys(ConfigReader.getProperty("password"));
        narPosPage.submitButton.click();
        ReusableMethods.wait(3);
    }
    public static void deleteDomatesStock() throws IOException {
        NarPosPage narPosPage = new NarPosPage();

        narPosPage.anaVeriler.click();
        ReusableMethods.wait(1);
        narPosPage.stokKartlari.click();


        // "DOMATES" stokunun olup olmadığını kontrol et
        List<WebElement> stoklar = Driver.getDriver().findElements(By.xpath("//span[contains(@class, 'stock-text') and (text()='DOMATES-REVIZE' or text()='DOMATES')]"));

        if (!stoklar.isEmpty()) {
            // Üç noktaya tıkla
            narPosPage.menuThreePoints.click();

            // "Sil" butonuna tıkla
            narPosPage.deleteButton.click();

            // Onay popup'ında "Tamam" butonuna bas
            narPosPage.okayButton.click();

            // Sayfayı yenile (F5)
            Driver.getDriver().navigate().refresh();

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
        narPosPage.okayButton.click();
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
        Assert.assertEquals(expected, actual);
        // ReusableMethods.getScreenshot("testStockOrganisation");
        System.out.println("Test Passed: Stok kartı başarıyla güncellendi ve doğrulandı.");
    }
    public static void stockCartDeleteTest(){
        NarPosPage narPosPage = new NarPosPage();

        // "DOMATES-REVIZE" satırını bul ve üç noktaya tıkla ve sil
        narPosPage.menuThreePoints.click();
        narPosPage.deleteButton.click();
        narPosPage.okayButton.click();

        // Refresh page (F5)
        Driver.getDriver().navigate().refresh();

        // "DOMATES-REVIZE" tekrar listede var mı kontrol et

        //  if (narPosPage.refreshdRow1.isDisplayed()){
        //      System.out.println("Test Başarılı: Stok kartı silindi.");
        //  }else {
        //      System.out.println("Test Başarısız: Stok kartı hala listede.");
        //  }



        List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//span[contains(@class, 'stock-text') and (text()='DOMATES-REVIZE' or text()='DOMATES')]"));

        if (elements.isEmpty()) {
            System.out.println("Test PASSED: Stok kartı silindi.");
        } else {
            System.out.println("Test FAILED: Stok kartı hala listede.");
        }

    }
    public static void inventoryAndInvoiceTest(){

        NarPosPage narPosPage = new NarPosPage();

        try {

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

            // Refresh page
            Driver.getDriver().navigate().refresh();
            ReusableMethods.wait(3);


            // *** "DOMATES-REVIZE" stoğunu tekrar ekle ***
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
            ReusableMethods.wait(2);

            // *** "İsim kullanılıyor" hatası kontrolü ***

            if (narPosPage.usedNameFailed.isDisplayed()) {
                System.out.println("Test FAILED: 'İsim kullanılıyor' hatası alındı!");
                ReusableMethods.getScreenshot("nameUsed");
            } else {
                System.out.println("Test PASSED: Stok kartı eklendi ve isim çakışması yok.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runTest() throws IOException {
        // --- Get to Narpos.com ---
                getToNarPosCom();

        // --- Delete Domates Stock ---
        deleteDomatesStock();

        // --- Add New Stock Card ---
        addNewStockCard();
//
        // --- Test Stock Organisation ---
        testStockOrganisation();
//
        // --- Stock Cart Delete Test ---
        stockCartDeleteTest();
//
        // Inventory And Invoice Test
        inventoryAndInvoiceTest();
    }
}