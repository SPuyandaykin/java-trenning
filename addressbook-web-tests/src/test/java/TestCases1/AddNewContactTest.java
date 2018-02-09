package TestCases1;

import model.*;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        app.goTo().home();
        Contacts before = app.contact().all();
        ContactNameData contactName = new ContactNameData(
                app.readProperty("contact.FirstName")+System.currentTimeMillis(),
                app.readProperty("contact.LastName"),
                app.readProperty("contact.Company"));
        ContactPhoneData contactPhone = new ContactPhoneData (
                app.readProperty("contact.PhoneHome"),
                app.readProperty("contact.PhoneMobile"));
        ContactData contact = new ContactData().withContactName(contactName).withContactPhone(contactPhone);
        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
