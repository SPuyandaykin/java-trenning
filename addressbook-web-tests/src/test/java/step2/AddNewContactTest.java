package step2;

import org.testng.annotations.Test;

public class AddNewContactTest extends TestBase{

    @Test
    public void testAddNewContact() {
        CreateNewContact();
        FillNameFields(new ContactNameData("sergey", "ivanov", "Java Corporation"));
        FillPhoneFields(new ContactPhoneData("+79031111111", "+79042222222"));
        SubminContactCreating();
        ReturnToHomePage();
    }

}
