package step2;

import org.testng.annotations.Test;

public class EditFirstGroupTest extends TestBase{

    @Test
    public void testEditFirstGroup(){
        app.getGroupHelper().SelectGroupPage();
        app.getGroupHelper().SelectFirstGroup();
        app.getGroupHelper().EditGroup();
        app.getGroupHelper().UpdateGroup();
        app.getNavigationHelper().ReturnToGroupPage();
    }
}
