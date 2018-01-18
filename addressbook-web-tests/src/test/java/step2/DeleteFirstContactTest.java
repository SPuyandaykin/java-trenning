package step2;

import org.testng.annotations.Test;

public class DeleteFirstContactTest extends TestBase{

    @Test
    public void testDeleteFirstContact () {
        app.getContactHelper().SelectFirstContact();
        app.getContactHelper().DeleteContact();
    }
}
