package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class N11page {

    public N11page(){

        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(linkText = "Giriş Yap")
    public WebElement btnGirisYap;

    @FindBy(xpath = "//input[@id='email']")
    public WebElement btnEmail;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement btnPassword;

    @FindBy(xpath = "//div[@id='loginButton']")
    public WebElement LoginButton;

    @FindBy(css = "a[title='Hesabım']")
    public WebElement btnHesabim;

    @FindBy(css = "a[title='Çıkış Yap']")
    public WebElement btnCikisYap;




}
