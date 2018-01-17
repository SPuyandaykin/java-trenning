package appmanager;

import model.ContactNameData;
import model.ContactPhoneData;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private FirefoxDriver wd;

    private ContactHelper contactHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;

    public void init() {
        //        wd = new FirefoxDriver();
        wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("C:/Tools/Browsers/Mozilla Firefox ESR/firefox.exe"));
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public void ReturnToGroupPage() {
        navigationHelper.ReturnToGroupPage();
    }

    public void ReturnToHomePage() {
        navigationHelper.ReturnToHomePage();
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public void SubminContactCreating() {
        contactHelper.SubminContactCreating();
    }

    public void FillPhoneFields(ContactPhoneData contactPhoneData) {
        contactHelper.FillPhoneFields(contactPhoneData);
    }

    public void FillNameFields(ContactNameData contactNameData) {
        contactHelper.FillNameFields(contactNameData);
    }

    public void CreateNewContact() {
        contactHelper.CreateNewContact();
    }
}
