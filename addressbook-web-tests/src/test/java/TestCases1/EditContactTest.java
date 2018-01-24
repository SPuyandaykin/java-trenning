package TestCases1;

import model.ContactNameData;
import model.ContactPhoneData;
import org.testng.annotations.Test;

public class EditContactTest extends TestBase {

    @Test
    public void testEditFirstContact(){
        app.getNavigationHelper().OpenHomePage();
        if(!app.getContactHelper().isAnyContact())
            app.getContactHelper().NewContactAdd(new ContactNameData("sergey", "ivanov", "Java Corporation"),
                    new ContactPhoneData("+79031111111", "+79042222222"));
        app.getContactHelper().SelectFirstContact();
        app.getContactHelper().EditContact();
        app.getContactHelper().ChangeMobilePhone("+79081112233");
        app.getContactHelper().UpdateContact();
        app.getContactHelper().ReturnToHomePage();
    }
}
