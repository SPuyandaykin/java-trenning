package appmanager;

import model.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void SubmitContactCreating() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
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

    public void create(ContactData contactData, boolean creation) {
        CreateNewContact();
        FillContactForms(contactData);
        if(creation){
            if(contactData.getGroups().size()>0){
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
           }else{
                Assert.assertTrue(isElementPresent(By.name("new_group")));
            }
        }

        SubmitContactCreating();
        contactCache = null;

        ContactPhoneData phoneData = contactData.getContactPhone();
        contactData.setAllPhones(contactData.mergePhones(phoneData));
        ReturnToHomePage();

        if(!creation)
            SetUpGroups(contactData.getFirstName(), contactData.getGroups());
    }

    private void SetUpGroups(String firstName, Groups groups) {
        Iterator <GroupData> iterator = groups.iterator();
        while (iterator.hasNext()) {
            SelectGroup(firstName,iterator.next().getName());
            ReturnToMainPage();
            new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
            SelectGroupFilter("[all]");
        }
    }

    public void SelectGroupFilter(String groupName){
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);
    }

    public void UnLinkGroup(String groupName, String groupForUnLink) {
        ReturnToMainPage();
        SelectGroupFilter(groupForUnLink);
        removeGroup(groupName);
    }

    public void removeGroup(String groupName){
        selectByName(groupName);
        wd.findElement(By.xpath("//input[@name='remove']")).click();
    }

    public void SelectGroup(String firstName, String groupName){
        selectByName(firstName);
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
        wd.findElement(By.xpath("//input[@type='submit']")).click();
    }

    public void DeleteAllContact() {
        String contactsNumber = wd.findElement(By.xpath("//span[@id='search_count']")).getText();
        if(contactsNumber.equals("0"))
            return;

        wd.findElement(By.xpath("//input[@id='MassCB']")).click();
        deleteSelectedContact();
    }

    private void selectByName(String firstName) {
        String spath = "//td[contains(text(),'" + firstName + "')]/parent::node()/td/input";
        wd.findElement(By.xpath(spath)).click();
    }


    public void FillContactForms (ContactData contactData){
        FillNameFields(contactData);
        FillPhoneFields(contactData);
    }

    public void ReturnToHomePage() {
        click(By.linkText("home page"));
    }

    public void ReturnToMainPage() {
        click(By.linkText("home"));
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
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            ContactNameData contactName = new ContactNameData(cells.get(2).getText(),
                    cells.get(1).getText(), "");

            ContactPhoneData contactPhone = new ContactPhoneData();
 //           contactPhone.setAllPhones(cells.get(5).getText());
            contactPhone.setEmail(cells.get(4).getText());
            contactPhone.setAddress(cells.get(3).getText());

            ContactData group = new ContactData()
                    .withId(id).withContactName(contactName).withContactPhone(contactPhone);
            group.setAllPhones(cells.get(5).getText());
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
        System.out.println("id is: "+id);
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

    public void FillPhoneFields(ContactData contactData) {
        type(By.name("home"), contactData.getPhoneHome());
        type(By.name("mobile"), contactData.getPhoneMobile());
        type(By.name("work"), contactData.getWorkMobile());
        type(By.name("email"), contactData.getEmail());
        type(By.name("address"), contactData.getAddress());
    }

    public void FillNameFields(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("company"), contactData.getCompany());
//        attach(By.name("photo"), contactNameData.getPhoto());
    }

    public ContactData infoFromContactForm(ContactData contact) {
        EditContactSelectById(contact.getId());

        ContactNameData contactName = new ContactNameData(
                getLabel(By.name("firstname")),
                getLabel(By.name("lastname")),
                getLabel(By.name("company")));
        ContactPhoneData contactPhone = new ContactPhoneData (
                getLabel(By.name("home")),
                getLabel(By.name("mobile")),
                getLabel(By.name("work")),
                getLabel(By.name("email")),
                getLabel(By.name("address")));

        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                .withContactName(contactName).withContactPhone(contactPhone);

    }


}

