package TestCases1;

import model.GroupData;
import org.testng.annotations.Test;

public class DeleteFirstGroupTest extends TestBase{

    @Test
    public void testRemoveFirstGroup() {
        app.getGroupHelper().SelectGroupPage();
        if(!app.getGroupHelper().isAnyGroup())
            app.getGroupHelper().NewGroupAdd(new GroupData("TestGroup", "test header", "test footer"));
        app.getGroupHelper().SelectFirstGroup();
        app.getGroupHelper().DeleteGroup();
    }
}
