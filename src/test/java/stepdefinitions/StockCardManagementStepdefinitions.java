package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
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

    public class StockCardManagementStepdefinitions {

        NarPosPage narPosPage = new NarPosPage();

        @Given("I am logged in to NarPos")
        public void iAmLoggedInToNarPos() {
            Driver.getDriver().get("https://narcost.narpos.com.tr/login");
            narPosPage.email.sendKeys(ConfigReader.getProperty("email"));
            narPosPage.password.sendKeys(ConfigReader.getProperty("password"));
            narPosPage.submitButton.click();
            ReusableMethods.wait(3);
        }

        @When("I delete the DOMATES stock if it exists")
        public void iDeleteTheDomatesStockIfItExists() throws IOException {
            narPosPage.anaVeriler.click();
            ReusableMethods.wait(1);
            narPosPage.stokKartlari.click();

            // "DOMATES" stock check
            List<WebElement> stoklar = Driver.getDriver().findElements(By.xpath("//span[contains(@class, 'stock-text') and (text()='DOMATES-REVIZE' or text()='DOMATES')]"));

            if (!stoklar.isEmpty()) {
                narPosPage.menuThreePoints.click();
                narPosPage.deleteButton.click();
                narPosPage.okayButton.click();
                Driver.getDriver().navigate().refresh();

                List<WebElement> kontrolStoklar = Driver.getDriver().findElements(By.xpath("//tr[td[text()='DOMATES-REVIZE']]"));
                if (kontrolStoklar.isEmpty()) {
                    System.out.println("Test PASSED: 'DOMATES' stoğu silindi.");
                } else {
                    System.out.println("Test FAILED: 'DOMATES' stoğu hala listede.");
                }
            } else {
                System.out.println("'DOMATES' stoğu mevcut değil.");
            }

        }

        @When("I add a new DOMATES stock card and veriying")
        public void iAddANewDomatesStockCardandverifying() throws IOException {
            narPosPage.newAdd.click();
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
            JSUtilities.clickWithJS(Driver.getDriver(), narPosPage.kilogram);

            JSUtilities.clickWithJS(Driver.getDriver(), narPosPage.conversionUnit);

            narPosPage.saveAndContinue.click();
            ReusableMethods.wait(5);

            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'İşlem Başarılı')]")));
            Assert.assertTrue(successMessage.isDisplayed(), "Test Passed: İşlem Başarılı");

            if (narPosPage.successMessage.isDisplayed()) {
                System.out.println("Test Passed: DOMATES listeye eklendi.");
            } else {
                System.out.println("Test Fail: DOMATES listeye eklenemedi.");
            }
            narPosPage.okayButton.click();
            narPosPage.close.click();
            narPosPage.yes.click();

        }

        //   @Then("I verify that the DOMATES stock is added")
        //   public void iVerifyThatTheDomatesStockIsAdded() {
        //       WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        //       WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'İşlem Başarılı')]")));
        //       Assert.assertTrue(successMessage.isDisplayed(), "Test Passed: Stok kartı başarıyla eklendi.");
        //   }

        @When("I edit the DOMATES stock card and verifying that updated")
        public void iEditTheDomatesStockCard() throws IOException {
            narPosPage.menuThreePoints.click();
            narPosPage.edit.click();

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

            narPosPage.save.click();

            if (narPosPage.successMessage.isDisplayed()) {
                System.out.println("Test Passed: Stok kartı düzenlendi.");
            } else {
                System.out.println("Test Failed: Stok kartı düzenlenemedi.");
            }
            ReusableMethods.wait(10);

            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'İşlem Başarılı')]")));
            Assert.assertTrue(successMessage.isDisplayed(), "Test Passed: Stok Kartı başarıyla güncellenmiştir.");

            Driver.getDriver().navigate().refresh();
            String actual = narPosPage.refreshdRow1.getText();
            String expected = "DOMATES-REVIZE";
            Assert.assertEquals(expected, actual);
            System.out.println("Test Passed: Stok kartı başarıyla güncellendi ve doğrulandı.");
        }

        @When("I delete the DOMATES-REVIZE stock card and verifying DOMATES-REVIZE stock card is deleted")
        public void iDeleteTheDomatesRevizeStockCard() {
            narPosPage.menuThreePoints.click();
            narPosPage.deleteButton.click();
            narPosPage.okayButton.click();


            Driver.getDriver().navigate().refresh();

            List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//span[contains(@class, 'stock-text') and (text()='DOMATES-REVIZE' or text()='DOMATES')]"));

            if (elements.isEmpty()) {
                System.out.println("Test PASSED: Stok kartı silindi.");
            } else {
                System.out.println("Test FAILED: Stok kartı hala listede.");
            }
        }

        @Then("I verify that the DOMATES-REVIZE stock card is deleted")
        public void iVerifyThatTheDomatesRevizeStockCardIsDeleted() {
            List<WebElement> elements = Driver.getDriver().findElements(By.xpath("//span[contains(@class, 'stock-text') and (text()='DOMATES-REVIZE' or text()='DOMATES')]"));
            Assert.assertTrue(elements.isEmpty(), "Test Passed: DOMATES-REVIZE stoğu silindi.");
        }

        @When("I try to add the same DOMATES stock card again")
        public void iTryToAddTheSameDomatesStockCardAgain() {
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
                JSUtilities.clickWithJS(Driver.getDriver(), narPosPage.kilogram);

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
                JSUtilities.clickWithJS(Driver.getDriver(), narPosPage.kilogram);

                JSUtilities.clickWithJS(Driver.getDriver(), narPosPage.conversionUnit);

                // Click on the ‘Save and continue’ button
                narPosPage.saveAndContinue.click();
                ReusableMethods.wait(2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Then("I shouldn't see a name conflict error")
        public void iShouldSeeANameConflictError() throws IOException {
            if (narPosPage.usedNameFailed.isDisplayed()) {
                ReusableMethods.getScreenshot("conflictError");
                System.out.println("Test FAILED: 'İsim kullanılıyor' hatası alındı!");
                Assert.assertFalse(narPosPage.usedNameFailed.isDisplayed());
            } else {
                System.out.println("Test PASSED: Stok kartı eklendi ve isim çakışması yok.");
            }
        }
    }
