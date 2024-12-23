package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import pages.N11page;
import utilities.ConfigReader;
import utilities.Driver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class N11step {
    private N11page n11page;
    private Actions actions;

    public N11step() {
        n11page = new N11page();
    }

    private Actions getActions() {
        if (actions == null) {
            actions = new Actions(Driver.getDriver());
        }
        return actions;
    }

    @Given("Kullanıcı Anasayfaya gider.")
    public void kullanıcıAnasayfayaGider() {
        System.out.println("N11'e gidiliyor...");
        String url = ConfigReader.getProperty("urlN11").trim();
        System.out.println("URL: " + url);
        Driver.getDriver().get(url);
    }

    @When("Ana sayfanın yüklendiği doğrulanır.")
    public void anaSayfanınYüklendiğiDoğrulanır() {
        String actualTitle = Driver.getDriver().getTitle();
        System.out.println("actualTitle = " + actualTitle);
        String expectedTitle = "n11 - 10 Üzerinden 11'lik Alışveriş Deneyimi";
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @And("Kullanıcı ana sayfada Giriş Yap butonunu bulur ve tıklar.")
    public void kullanıcıAnaSayfadaGirişYapButonunuBulurVeTıklar() throws InterruptedException {
        Thread.sleep(2000);
        n11page.btnGirisYap.click();
    }

    @And("Giriş sayfasının yüklendiği doğrulanır.")
    public void girişSayfasınınYüklendiğiDoğrulanır() {
        String actualUrl = Driver.getDriver().getCurrentUrl();
        System.out.println("actualUrl = " + actualUrl);
        String expectedUrl = "https://www.n11.com/giris-yap";
        assertEquals(actualUrl, expectedUrl);
    }

    @And("Kullanıcı giriş sayfasında geçerli bir kullanıcı adı  girer.")
    public void kullanıcıGirişSayfasındaGeçerliBirKullanıcıAdıGirer() {
        n11page.btnEmail.sendKeys(ConfigReader.getProperty("email"));
    }

    @And("Kullanıcı giriş sayfasında geçerli bir şifre girer.")
    public void kullanıcıGirişSayfasındaGeçerliBirŞifreGirer() {
        n11page.btnPassword.sendKeys(ConfigReader.getProperty("sifre"));
    }

    @And("Kullanıcı {string} butonuna tıklar.")
    public void kullanıcıButonunaTıklar(String arg0) {
        n11page.LoginButton.click();
    }

    @And("Kullanıcının hesabına başarıyla giriş yapıldığı doğrulanır.")
    public void kullanıcınınHesabınaBaşarıylaGirişYapıldığıDoğrulanır() throws InterruptedException {
        Thread.sleep(1000);
        getActions().moveToElement(n11page.btnHesabim).perform();
        assertTrue(n11page.btnCikisYap.isEnabled());
    }
}
