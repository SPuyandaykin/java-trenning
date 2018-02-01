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
        ContactNameData contactName = new ContactNameData("sergey"+System.currentTimeMillis(),
                "ivanov", "Java Corporation");
        ContactPhoneData contactPhone = new ContactPhoneData ("+79031111111", "+79042222222");
        ContactData contact = new ContactData().withContactName(contactName).withContactPhone(contactPhone);
        app.contact().create(contact);
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

}
