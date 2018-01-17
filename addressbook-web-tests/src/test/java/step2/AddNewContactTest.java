package step2;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class AddNewContactTest {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
//        wd = new FirefoxDriver();
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("C:/Tools/Browsers/Mozilla Firefox ESR/firefox.exe"));
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        login("admin", "secret");
    }
    
    @Test
    public void testAddNewContact() {
        CreateNewContact();
        FillNameFields(new ContactNameData("sergey", "ivanov", "Java Corporation"));
        FillPhoneFields(new ContactPhoneData("+79031111111", "+79042222222"));
        SubminContactCreating();
        ReturnToHomePage();
    }

    private void ReturnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    private void SubminContactCreating() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    private void FillPhoneFields(ContactPhoneData contactPhoneData) {
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactPhoneData.getPhoneHome());
        wd.findElement(By.name("mobile")).click();
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(contactPhoneData.getPhoneMobile());
    }

    private void FillNameFields(ContactNameData contactNameData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactNameData.getFirstName());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactNameData.getLastName());
        wd.findElement(By.name("company")).click();
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(contactNameData.getCompany());
    }

    private void CreateNewContact() {
        wd.findElement(By.linkText("add new")).click();
    }

    private void login(String name, String passw) {
        wd.get("http://localhost/addressbook/group.php");
        wd.findElement(By.name("user")).click();
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(name);
        wd.findElement(By.name("pass")).click();
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(passw);
        wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
