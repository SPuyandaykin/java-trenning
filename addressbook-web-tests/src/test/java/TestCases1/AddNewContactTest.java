package TestCases1;

import model.ContactNameData;
import model.ContactPhoneData;
import org.testng.annotations.Test;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        app.getNavigationHelper().OpenHomePage();
        app.getContactHelper().NewContactAdd(new ContactNameData("sergey", "ivanov", "Java Corporation"),
                new ContactPhoneData("+79031111111", "+79042222222"));
    }

}
