package TestCases1;

import model.*;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        app.goTo().home();
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        ContactNameData contactName = new ContactNameData(
                app.readProperty("contact.FirstName")+System.currentTimeMillis(),
                app.readProperty("contact.LastName"),
                app.readProperty("contact.Company"));
        ContactPhoneData contactPhone = new ContactPhoneData (
                app.readProperty("contact.PhoneHome"),
                app.readProperty("contact.PhoneMobile"));
        ContactData contact = new ContactData().withContactName(contactName)
                .withContactPhone(contactPhone).inGroup(groups.iterator().next());
        app.contact().create(contact, true);

        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verityContactListinUI();
    }

}
