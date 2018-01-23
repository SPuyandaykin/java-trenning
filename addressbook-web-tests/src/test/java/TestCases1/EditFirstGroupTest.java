package TestCases1;

import model.GroupData;
import org.testng.annotations.Test;

public class EditFirstGroupTest extends TestBase{

    @Test
    public void testEditFirstGroup(){
        app.getGroupHelper().SelectGroupPage();
        if(!app.getGroupHelper().isAnyGroup())
            app.getGroupHelper().NewGroupAdd(new GroupData("TestGroup", "test header", "test footer"));
        app.getGroupHelper().SelectFirstGroup();
        app.getGroupHelper().EditGroup();
        app.getGroupHelper().ChangeGroupFooter("test2 footer");
        app.getGroupHelper().UpdateGroup();
        app.getGroupHelper().ReturnToGroupPage();
    }
}
