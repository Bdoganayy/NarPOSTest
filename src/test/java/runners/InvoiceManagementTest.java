package runners;


import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import pages.NarPosPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JSUtilities;
import utilities.ReusableMethods;

public class InvoiceManagementTest {
    public static void main(String[] args) {

        getToNarPosCom();

        // --- Check if the URL contains ‘purchase-invoice’. ---
        invoiceManagement();

        // --- Add New Invoice ---
        addNewInvoice();

        // --- Delete Added Invoice ---
        deleteAddedInvoice();

    }

    private static void getToNarPosCom() {
        Driver.getDriver().get("https://narcost.narpos.com.tr/login");
        NarPosPage narPosPage = new NarPosPage();

        narPosPage.email.sendKeys(ConfigReader.getProperty("email"));
        narPosPage.password.sendKeys(ConfigReader.getProperty("password"));
        narPosPage.submitButton.click();
        ReusableMethods.wait(3);
    }
    public static void invoiceManagement(){

        NarPosPage narPosPage = new NarPosPage();

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
    public static void addNewInvoice(){
        NarPosPage narPosPage = new NarPosPage();

        narPosPage.newAdd.click();

        narPosPage.chooseWarehouse.click();
        ReusableMethods.wait(1);
        narPosPage.mainWarehouseMainOffice.click();

        narPosPage.currentName.click();
        narPosPage.narposTedarikci.click();

        narPosPage.placeHolder.sendKeys("DOMATES");
        ReusableMethods.wait(2);
        narPosPage.dgrDomates.click();

        narPosPage.quantity.clear();
        ReusableMethods.wait(2);
        narPosPage.quantity.sendKeys("10");

        narPosPage.unitPrice.clear();
        narPosPage.unitPrice.clear();
        ReusableMethods.wait(3);
        narPosPage.unitPrice.sendKeys("100");
        narPosPage.netTotal.click();
        ReusableMethods.wait(5);


        String kdvTutar = (String) JSUtilities.getTextWithJS(Driver.getDriver(), narPosPage.totalVat);
        String netToplam = (String)  JSUtilities.getTextWithJS(Driver.getDriver(), narPosPage.netTotal);

        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String satirToplamValue = (String) js.executeScript("return arguments[0].value;", narPosPage.lineTotal);
        // System.out.println("JavaScript ile Input Value: " + satirToplamValue);

        // **KDV, Satır Toplamı ve Net Toplam Kontrol Et**
        Assert.assertEquals(kdvTutar.trim(), "10,00", "KDV Tutarı yanlış!");
        Assert.assertEquals(satirToplamValue.trim(), "1000,00", "Satır Toplamı yanlış!");
        Assert.assertEquals(netToplam.trim(), "1.010,00", "Net Toplam yanlış!");

        narPosPage.save.click();
        ReusableMethods.wait(3);
        narPosPage.okayButton.click();
        System.out.println("Test PASSED: KDV, Satır ve Net Toplam doğru hesaplandı.");
        ReusableMethods.wait(3);

        if (narPosPage.getMainWarehouseMainOffice.getText().contains("Ana Şube Ana Depo")){
            System.out.println("Test PASSEd: Listeye eklenen veri dogru");
        }else {
            System.out.println("Test FAILED: Listeye eklenen veri yanlis");
        }
    }
    private static void deleteAddedInvoice() {

        NarPosPage narPosPage = new NarPosPage();
        narPosPage.theerePointsButton.click();
        narPosPage.deleteButton.click();
        ReusableMethods.waitForClickablility(narPosPage.okayButton,3);
        Driver.getDriver().navigate().refresh();
        String actual = " Ana Şube Ana Depo ";
        String expect = narPosPage.getMainWarehouseMainOffice.getText();
        Assert.assertFalse(expect.contains(actual));
        System.out.println("Test PASSED: Liste tekrardan konrol edildi");
        Driver.closeDriver();
    }

    public void runTest() {

        getToNarPosCom();

        // --- Check if the URL contains ‘purchase-invoice’. ---
        invoiceManagement();

        // --- Add New Invoice ---
        addNewInvoice();

    }
}
