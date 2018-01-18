package appmanager;

import model.ContactNameData;
import model.ContactPhoneData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void SubminContactCreating() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void FillPhoneFields(ContactPhoneData contactPhoneData) {
        type(By.name("home"), contactPhoneData.getPhoneHome());
        type(By.name("mobile"), contactPhoneData.getPhoneMobile());
    }

    public void FillNameFields(ContactNameData contactNameData) {
        type(By.name("firstname"), contactNameData.getFirstName());
        type(By.name("lastname"), contactNameData.getLastName());
        type(By.name("company"), contactNameData.getCompany());
    }

    public void CreateNewContact() {
        click(By.linkText("add new"));
    }

    public void UpdateContact() {
        click(By.name("update"));
    }

    public void SelectFirstContact() {
        click(By.xpath("//td/input"));
    }

    public void EditContact() {
        click(By.xpath("//a/img[@title='Edit']"));
    }
    public void ChangeMobilePhone(String text) {
        type(By.name("mobile"), text);
    }

    public void DeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }
}

