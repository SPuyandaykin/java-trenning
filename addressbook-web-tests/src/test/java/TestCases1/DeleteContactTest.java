package TestCases1;

import model.ContactNameData;
import model.ContactPhoneData;
import org.testng.annotations.Test;

public class DeleteContactTest extends TestBase{

    @Test
    public void testDeleteFirstContact () {
        app.getNavigationHelper().OpenHomePage();
        if(!app.getContactHelper().isAnyContact())
            app.getContactHelper().NewContactAdd(new ContactNameData("sergey", "ivanov", "Java Corporation"),
                    new ContactPhoneData("+79031111111", "+79042222222"));
        app.getContactHelper().SelectFirstContact();
        app.getContactHelper().DeleteContact();
        app.getNavigationHelper().OpenHomePage();
    }
}
