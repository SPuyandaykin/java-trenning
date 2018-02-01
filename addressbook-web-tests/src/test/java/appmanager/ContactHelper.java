package appmanager;

import model.ContactData;
import model.ContactNameData;
import model.ContactPhoneData;
import model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        type(By.name("work"), contactPhoneData.getWorkMobile());
        type(By.name("email"), contactPhoneData.getEmail());
        type(By.name("address"), contactPhoneData.getAddress());
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

    public void ChangeMobilePhone(String text) {
        type(By.name("mobile"), text);
    }

    public boolean size() {
        return isElementPresent(By.name("selected[]"));
    }

    public void NewContactAdd(ContactData contactData) {
        CreateNewContact();
        FillContactForms(contactData);
        SubmitContactCreating();
        ReturnToHomePage();
    }

/*    public void create(ContactNameData nameData, ContactPhoneData phoneData) {
        CreateNewContact();
        FillNameFields(nameData);
        FillPhoneFields(phoneData);
        SubmitContactCreating();
        ReturnToHomePage();
    }*/

    public void create(ContactData contactData) {
        CreateNewContact();
        FillContactForms(contactData);
        SubmitContactCreating();
        contactCache = null;
        ReturnToHomePage();
    }

    public void FillContactForms (ContactData contactData){
        FillNameFields(contactData.getContactName());
        FillPhoneFields(contactData.getContactPhone());
    }

    public void ReturnToHomePage() {
        click(By.linkText("home page"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element :  elements) {
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            ContactNameData contactName = new ContactNameData(firstName, lastName, "");
            ContactPhoneData contactPhone = new ContactPhoneData ("", "");
            ContactData group = new ContactData()
                    .withId(id).withContactName(contactName).withContactPhone(contactPhone);
            contacts.add(group);
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element :  elements) {
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

            ContactNameData contactName = new ContactNameData(firstName, lastName, "");
            ContactPhoneData contactPhone = new ContactPhoneData ("", "");
            ContactData group = new ContactData()
                    .withId(id).withContactName(contactName).withContactPhone(contactPhone);
            contacts.add(group);
        }
        return contacts;
    }

    public void EditContactSelectByIndex(int groupIndex) {
        WebElement element = wd.findElements(By.name("entry")).get(groupIndex);
        element.findElement(By.xpath(".//a/img[@title='Edit']")).click();
    }

    public void EditContactSelectById(int id) {
        wd.findElement(By.xpath("//input[@value='" + id + "']/parent::node()/parent::node()/td[8]//a")).click();
    }

    public void modify(ContactData contact) {
        EditContactSelectById(contact.getId());
        FillContactForms(contact);
        UpdateContact();
        contactCache = null;
        ReturnToHomePage();
    }

    public void selectByIndex(int groupIndex) {
        WebElement element = wd.findElements(By.name("entry")).get(groupIndex);
        element.findElement(By.name("selected[]")).click();
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void delete(int index) {
        selectByIndex(index);
        deleteSelectedContact();
    }

    public void delete(ContactData deletedContact) {
        selectById(deletedContact.getId());
        deleteSelectedContact();
    }

    public void deleteSelectedContact(){
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        contactCache = null;
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }
}

