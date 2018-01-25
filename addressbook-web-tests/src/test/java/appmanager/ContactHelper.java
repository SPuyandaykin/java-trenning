package appmanager;

import model.ContactData;
import model.ContactNameData;
import model.ContactPhoneData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void SubmitContactCreating() {
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

    public void SelectContact(int groupIndex) {
        WebElement element = wd.findElements(By.name("entry")).get(groupIndex);
        element.findElement(By.name("selected[]")).click();
    }

    public void EditContact(int groupIndex) {
        WebElement element = wd.findElements(By.name("entry")).get(groupIndex);
        element.findElement(By.xpath(".//a/img[@title='Edit']")).click();
    }
    public void ChangeMobilePhone(String text) {
        type(By.name("mobile"), text);
    }

    public void DeleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    public boolean isAnyContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void NewContactAdd(ContactData contactData) {
        CreateNewContact();
        FillContactForms(contactData);
        SubmitContactCreating();
        ReturnToHomePage();
    }

    public void NewContactAdd(ContactNameData nameData, ContactPhoneData phoneData) {
        CreateNewContact();
        FillNameFields(nameData);
        FillPhoneFields(phoneData);
        SubmitContactCreating();
        ReturnToHomePage();
    }

    public void FillContactForms (ContactData contactData){
        FillNameFields(contactData.getContactName());
        FillPhoneFields(contactData.getContactPhone());
    }

    public void ReturnToHomePage() {
        click(By.linkText("home page"));
    }

    public List<ContactData> getContactList() {

        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element :  elements) {
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            ContactNameData contactName = new ContactNameData(firstName, lastName, "");
            ContactPhoneData contactPhone = new ContactPhoneData ("", "");
            ContactData group = new ContactData (id, contactName, contactPhone);
            contacts.add(group);
        }
        return contacts;
    }
}

