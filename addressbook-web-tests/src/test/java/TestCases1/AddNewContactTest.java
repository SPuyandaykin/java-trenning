package TestCases1;

import model.ContactNameData;
import model.ContactPhoneData;
import org.testng.annotations.Test;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        app.getNavigationHelper().OpenHomePage();
        app.getContactHelper().CreateNewContact();
        app.getContactHelper().FillNameFields(new ContactNameData("sergey", "ivanov", "Java Corporation"));
        app.getContactHelper().FillPhoneFields(new ContactPhoneData("+79031111111", "+79042222222"));
        app.getContactHelper().SubminContactCreating();
        app.getNavigationHelper().ReturnToHomePage();
    }

}
