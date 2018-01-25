package TestCases1;

import model.ContactData;
import model.ContactNameData;
import model.ContactPhoneData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        app.getNavigationHelper().OpenHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactNameData contactName = new ContactNameData("sergey"+System.currentTimeMillis(),
                "ivanov", "Java Corporation");
        ContactPhoneData contactPhone = new ContactPhoneData ("+79031111111", "+79042222222");
        ContactData contactData = new ContactData(contactName, contactPhone);
        app.getContactHelper().NewContactAdd(contactName, contactPhone);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()+1);

        int max = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();
        contactData.setId(max);
        before.add(contactData);
        // only First and Last Name are used for comparing
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}
