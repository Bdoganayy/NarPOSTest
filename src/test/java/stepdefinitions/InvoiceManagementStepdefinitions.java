package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.NarPosPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JSUtilities;
import utilities.ReusableMethods;

import java.net.ServerSocket;
import java.time.Duration;

public class InvoiceManagementStepdefinitions {
    NarPosPage narPosPage = new NarPosPage();

    @Given("user is on the NarPos login page")
    public void user_is_on_the_narpos_login_page() {
        Driver.getDriver().get("https://narcost.narpos.com.tr/login");
    }

    @When("user logs in with valid credentials")
    public void user_logs_in_with_valid_credentials() {
        narPosPage.email.sendKeys(ConfigReader.getProperty("email"));
        narPosPage.password.sendKeys(ConfigReader.getProperty("password"));
        narPosPage.submitButton.click();
        ReusableMethods.wait(3);
    }

    @Then("user navigates to purchase invoice page")
    public void user_navigates_to_purchase_invoice_page() {
        narPosPage.costManagement.click();
        narPosPage.purchaseInvoice.click();
        ReusableMethods.wait(3);
        // Get URL
        String urlPurchaseInvoice = Driver.getDriver().getCurrentUrl();

        // Verify that the URL contains ‘purchase-invoice’
        Assert.assertTrue(urlPurchaseInvoice.contains("purchase-invoice"), "Test FAILED: URL 'purchase-invoice' içermiyor!");
        System.out.println("Test PASSED: URL 'purchase-invoice' içeriyor.");
        ReusableMethods.wait(3);
    }

    @When("user adds a new invoice")
    public void user_adds_a_new_invoice() {
        narPosPage.newAdd.click();
        narPosPage.chooseWarehouse.click();
        ReusableMethods.wait(1);
        narPosPage.mainWarehouseMainOffice.click();
       // narPosPage.currentName.click();
      //  narPosPage.narposTedarikci.click();

        Actions actions = new Actions(Driver.getDriver());

        // Dropdown açılır menüyü tıkla
        narPosPage.currentName.click();

        // 2 defa aşağı ok tuşuna bas, ardından ENTER ile seç
        for (int i = 0; i < 2; i++) {
            actions.sendKeys(Keys.ARROW_DOWN).perform();
        }

        actions.sendKeys(Keys.ENTER).perform();

        narPosPage.placeHolder.sendKeys("DOMATES");
        ReusableMethods.wait(2);
        narPosPage.dgrDomates.click();
        narPosPage.quantity.clear();
        ReusableMethods.wait(2);
        narPosPage.quantity.sendKeys("10");
        narPosPage.unitPrice.clear();
        ReusableMethods.wait(3);
        narPosPage.unitPrice.sendKeys("100");
        narPosPage.netTotal.click();
        ReusableMethods.wait(5);
    }

    @Then("user verifies the invoice details")
    public void user_verifies_the_invoice_details() {
        String kdvTutar = JSUtilities.getTextWithJS(Driver.getDriver(), narPosPage.totalVat);
        String netToplam = JSUtilities.getTextWithJS(Driver.getDriver(), narPosPage.netTotal);
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String satirToplamValue = (String) js.executeScript("return arguments[0].value;", narPosPage.lineTotal);
        Assert.assertEquals(kdvTutar.trim(), "10,00", "KDV Tutarı yanlış!");
        Assert.assertEquals(satirToplamValue.trim(), "1000,00", "Satır Toplamı yanlış!");
        Assert.assertEquals(netToplam.trim(), "1.010,00", "Net Toplam yanlış!");
        narPosPage.save.click();
        System.out.println("Test PASSED: Fatura dogru sekilde eklendi");
        ReusableMethods.wait(3);
        narPosPage.okayButton.click();

    }

    @Then("user deletes the added invoice")
    public void user_deletes_the_added_invoice() {
        narPosPage.arrowLeft.click();
        narPosPage.theerePointsButton.click();
        narPosPage.deleteButton.click();
        narPosPage.okayButton.click();
        Assert.assertTrue(narPosPage.successVoice.isDisplayed());
        Driver.getDriver().navigate().refresh();
        System.out.println("Test PASSED: Eklenen fatura silindi");

        // Elementin kaybolmasını bekleyelim
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        boolean isElementInvisible = wait.until(ExpectedConditions.invisibilityOf(narPosPage.getMainWarehouseMainOffice));
        Assert.assertTrue(isElementInvisible, "Eleman hala görünür, test FAILED!");

        System.out.println("Test PASSED: Sayfa yenilendikten sonra element kayboldu");
    }



    /*


  Scenario: User deletes the invoice
    Then user deletes the added invoice
    And user closes the browser
     */
}
