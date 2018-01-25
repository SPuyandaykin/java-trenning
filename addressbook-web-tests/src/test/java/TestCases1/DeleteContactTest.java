package TestCases1;

import model.ContactData;
import model.ContactNameData;
import model.ContactPhoneData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteContactTest extends TestBase{

    @Test
    public void testDeleteFirstContact () {
        app.getNavigationHelper().OpenHomePage();
        if(!app.getContactHelper().isAnyContact())
            app.getContactHelper().NewContactAdd(new ContactNameData("sergey", "ivanov", "Java Corporation"),
                    new ContactPhoneData("+79031111111", "+79042222222"));
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().SelectContact(before.size()-1);
        app.getContactHelper().DeleteContact();
        app.getNavigationHelper().OpenHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(after, before);
    }
}
