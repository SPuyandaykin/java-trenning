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
        app.goTo().home();
        if(!app.contact().size()) {
            ContactData contactData = new ContactData()
                    .withContactName(new ContactNameData("sergey", "ivanov", "Java Corporation"))
                    .withContactPhone(new ContactPhoneData("+79031111111", "+79042222222"));
            app.contact().create(contactData);
        }
    }

    @Test
    public void testEditFirstContact(){
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        int ind = modifiedContact.getId();
        ContactPhoneData contactPhone = new ContactPhoneData(modifiedContact.getContactPhone());
        ContactNameData contactName = new ContactNameData(modifiedContact.getContactName());
        contactName.setFirstName("vasiya");
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withContactName(contactName).withContactPhone(contactPhone);

        app.contact().modify(contact);
        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

    }


}
