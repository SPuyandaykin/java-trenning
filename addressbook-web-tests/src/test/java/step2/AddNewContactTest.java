package step2;

import model.ContactNameData;
import model.ContactPhoneData;
import org.testng.annotations.Test;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        app.CreateNewContact();
        app.FillNameFields(new ContactNameData("sergey", "ivanov", "Java Corporation"));
        app.FillPhoneFields(new ContactPhoneData("+79031111111", "+79042222222"));
        app.SubminContactCreating();
        app.ReturnToHomePage();
    }

}
