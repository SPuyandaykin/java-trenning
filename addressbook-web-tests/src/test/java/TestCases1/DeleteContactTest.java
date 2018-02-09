package TestCases1;

import model.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteContactTest extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().home();
        if(!app.contact().size()) {
            ContactData contactData = new ContactData()
                    .withContactName(new ContactNameData(
                            app.readProperty("contact.FirstName")+System.currentTimeMillis(),
                            app.readProperty("contact.LastName"),
                            app.readProperty("contact.Company")))
                    .withContactPhone(new ContactPhoneData(
                            app.readProperty("contact.PhoneHome"),
                            app.readProperty("contact.PhoneMobile")));
            app.contact().create(contactData);
        }
    }

    @Test
    public void testDeleteFirstContact () {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        int index = before.size()-1;
        app.contact().delete(deletedContact);
        app.goTo().home();

        assertThat(app.contact().count(), equalTo(before.size()-1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}
