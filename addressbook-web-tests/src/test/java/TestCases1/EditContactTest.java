package TestCases1;

import model.ContactData;
import model.ContactNameData;
import model.ContactPhoneData;
import model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class EditContactTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().contacts().size()==0) {
            app.goTo().home();
            ContactData contactData = new ContactData()
                    .withContactName(new ContactNameData(
                            app.readProperty("contact.FirstName") + System.currentTimeMillis(),
                            app.readProperty("contact.LastName"),
                            app.readProperty("contact.Company")))
                    .withContactPhone(new ContactPhoneData(
                            app.readProperty("contact.PhoneHome"),
                            app.readProperty("contact.PhoneMobile")));
            app.contact().create(contactData);
        }
    }

    @Test
    public void testEditFirstContact(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        int ind = modifiedContact.getId();
        ContactPhoneData contactPhone = new ContactPhoneData(modifiedContact.getContactPhone());
        ContactNameData contactName = new ContactNameData(modifiedContact.getContactName());
        contactName.setFirstName("vasiya");
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withContactName(contactName).withContactPhone(contactPhone);

        app.contact().modify(contact);

        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

    }


}
