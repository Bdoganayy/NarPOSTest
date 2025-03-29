package pages;

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

    @FindBy(xpath = "//tr[td[contains(text(), 'DOMATES')]]")
    public WebElement domates;

    @FindBy(xpath = ".//button[contains(@class, 'menu-button')]")
    public WebElement menuButton;

    @FindBy(xpath = "//span[@id=\"pr_id_11_label\"]")
    public WebElement stockType;
    @FindBy(xpath = "//li[@aria-label=\"Diğer\"]")
    public WebElement another;
    @FindBy(xpath = "(//span[@class=\"mat-button-wrapper\"])[4]")
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

    @FindBy(xpath = "//*[text()='KDV 1']")
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
    public WebElement okay;

    @FindBy(xpath = "//*[text()='Kaydet ']")
    public WebElement save;

    @FindBy(xpath = "(//td[@class=\"p-element p-frozen-column\"])[2]")
    public WebElement refreshdRow1;

}
