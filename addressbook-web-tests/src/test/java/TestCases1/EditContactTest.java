package TestCases1;

import model.ContactData;
import model.ContactNameData;
import model.ContactPhoneData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class EditContactTest extends TestBase {

    @Test
    public void testEditFirstContact(){
        app.getNavigationHelper().OpenHomePage();
        if(!app.getContactHelper().isAnyContact())
            app.getContactHelper().NewContactAdd(new ContactNameData("sergey", "ivanov", "Java Corporation"),
                    new ContactPhoneData("+79031111111", "+79042222222"));
        List<ContactData> before = app.getContactHelper().getContactList();

        ContactData oldContact = before.get(before.size()-1);
        ContactPhoneData contactPhone = oldContact.getContactPhone();
        ContactNameData contactName = oldContact.getContactName();
        contactName.setFirstName("vasiya");
        ContactData newContact = new ContactData (oldContact.getId(), contactName, contactPhone);

        app.getContactHelper().EditContact(before.size()-1);
        app.getContactHelper().FillContactForms(newContact);
        app.getContactHelper().UpdateContact();
        app.getContactHelper().ReturnToHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(newContact);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }
}
