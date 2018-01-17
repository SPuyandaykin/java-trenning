package step2;

import model.GroupData;
import org.testng.annotations.Test;

public class AddNewGroupTest extends TestBase{

    @Test
    public void testAddNewGroup() {
        app.getGroupHelper().SelectGroupPage();
        app.getGroupHelper().CreateNewGroup();
        app.getGroupHelper().FillNewGroupField(new GroupData("TestGroup", "test header", "test footer"));
        app.getGroupHelper().SubmitGroupCreating();
        app.ReturnToGroupPage();
    }

}
