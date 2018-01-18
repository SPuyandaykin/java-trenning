package step2;

import org.testng.annotations.Test;

public class EditFirstContactTest extends TestBase {

    @Test
    public void testEditFirstContact(){
        app.getContactHelper().SelectFirstContact();
        app.getContactHelper().EditContact();
        app.getContactHelper().ChangeMobilePhone("+79081112233");
        app.getContactHelper().UpdateContact();
        app.getNavigationHelper().ReturnToHomePage();
    }
}
