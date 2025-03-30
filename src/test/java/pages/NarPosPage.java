package pages;

import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class NarPosPage {
    public NarPosPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//input[@type=\"email\"]")
    public WebElement email;

    @FindBy(xpath = "(//input[@type=\"password\"])[1]")
    public WebElement password;

    @FindBy(xpath = "(//button[@type=\"submit\"])[1]")
    public WebElement submitButton;

    @FindBy(xpath = "//*[contains(text(), 'Email or password is incorrect')]")
    public WebElement inCorrectMessage;

    @FindBy(xpath = "//*[text()='Ana Veriler']")
    public WebElement anaVeriler;

    @FindBy(xpath = "//*[text()='Stok Kartları']")
    public WebElement stokKartlari;

    @FindBy(xpath = "//*[text()='DOMATES-REVIZE']")
    public WebElement domates;

    @FindBy(xpath = ".//button[contains(@class, 'menu-button')]")
    public WebElement menuButton;

    @FindBy(xpath = "//*[text()='Malzeme']")
    public WebElement stockType;
    @FindBy(xpath = "//li[@aria-label=\"Diğer\"]")
    public WebElement another;
    @FindBy(xpath = "//*[text()=' Yeni Ekle ']")
    public WebElement newAdd;

    @FindBy(xpath = "//input[@placeholder=\"Adı Yazınız.\"]")
    public WebElement nameNewStock;

    @FindBy(xpath = "//input[@id=\"stockCode\"]")
    public WebElement stockCode;

    @FindBy(xpath = "//*[text()=' Grup Ekle ']")
    public WebElement addGroup;

    @FindBy(xpath = "(//div[@role=\"button\"])[6]")
    public WebElement group1;

    @FindBy(xpath = "//li[@aria-label=\"YİYECEK\"]")
    public WebElement food;

    @FindBy(xpath = "//*[text()='Vergi Seçiniz.']")
    public WebElement tax;

    @FindBy(xpath = "(//span[@class=\"ng-star-inserted\"])[2]")
    public WebElement kdv1;

    @FindBy(xpath = "//*[text()='Temel Birim Seçiniz.']")
    public WebElement selectBasicUnit;

    @FindBy(xpath = "//*[text()='Kilogram']")
    public WebElement kilogram;

    @FindBy(xpath = "//*[text()='Gram']")
    public WebElement conversionUnit;

    @FindBy(xpath = "//*[text()='Kaydet ve Devam Et ']")
    public WebElement saveAndContinue;

    @FindBy(xpath = "//*[contains(text(), 'İşlem Başarılı')]")
    public WebElement successMessage;

    @FindBy(xpath = "//*[text()='Kapat ']")
    public WebElement close;

    @FindBy(xpath = "//td[@aria-haspopup=\"menu\"]")
    public WebElement menuThreePoints;

    @FindBy(xpath = "//*[text()='Düzenle']")
    public WebElement edit;

    @FindBy(xpath = "//*[text()=' Grup Düzenle ']")
    public WebElement editGroup;

    @FindBy(xpath = "//li[@aria-label=\"GIDA\"]")
    public WebElement gida;

    @FindBy(xpath = "//*[text()='Evet']")
    public WebElement yes;

    @FindBy(xpath = "//*[text()='Tamam']")
    public WebElement okayButton;

    @FindBy(xpath = "//*[text()='Kaydet ']")
    public WebElement save;

    @FindBy(xpath = "(//td[@class=\"p-element p-frozen-column\"])[2]")
    public WebElement refreshdRow1;

    @FindBy(xpath = "//*[text()='Sil']")
    public WebElement deleteButton;

    @FindBy(xpath = "//*[text()='Yazmış olduğunuz isim kullanılıyor.']")
    public WebElement usedNameFailed;

    @FindBy(xpath = "//*[text()='Maliyet Yönetimi']")
    public WebElement costManagement;

    @FindBy(xpath = "//*[text()='Alış Faturası']")
    public WebElement purchaseInvoice;

    @FindBy(xpath = "//*[text()='Depo Seçiniz']")
    public WebElement chooseWarehouse;

    @FindBy(xpath = "//*[text()='Ana Şube Ana Depo']")
    public WebElement mainWarehouseMainOffice;

    @FindBy(xpath = "//*[text()=' Ana Şube Ana Depo ']")
    public WebElement getMainWarehouseMainOffice;

    @FindBy(xpath = "//*[text()='Cari Adı Seçiniz.']")
    public WebElement currentName;

    @FindBy(xpath = "//*[text()='NarPOS Tedarikçi']")
    public WebElement narposTedarikci;


    @FindBy(xpath = "//input[@placeholder=\"Stok Yazınız.\"]")
    public WebElement placeHolder;
    // (DGR) DOMATES
    @FindBy(xpath = "//*[text()='(DGR) DOMATES']")
    public WebElement dgrDomates;

    @FindBy(xpath = "//li[@role=\"option\"]")
    public WebElement option;

    @FindBy(xpath = "(//input[@id=\"quantity\"])[1]")
    public WebElement quantity;

    @FindBy(xpath = "(//input[@placeholder=\"Miktar Yazınız.\"])[2]")
    public WebElement unitPrice;

    @FindBy(xpath = "//div[@class=\"col-md-6 modalFontNumber\"]")
    public WebElement totalVat;

    @FindBy(xpath = "(//input[@id=\"lineTotal\"])[2]")
    public WebElement lineTotal;

    @FindBy(xpath = "(//div[@class=\"col-md-6 modalFontNumber ng-star-inserted\"])[2]")
    public WebElement netTotal;

    @FindBy(xpath = "//*[@class=\"feather feather-arrow-left-circle\"]")
    public WebElement arrowLeft;

    @FindBy(xpath = "(//*[@aria-hidden=\"true\"])[3]")
    public WebElement theerePointsButton;

    @FindBy(xpath = "//*[text()='Fatura başarıyla silinmiştir.']")
    public WebElement successVoice;

}
