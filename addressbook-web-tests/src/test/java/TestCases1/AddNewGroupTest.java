package TestCases1;

import model.GroupData;
import org.testng.annotations.Test;

public class AddNewGroupTest extends TestBase{

    @Test
    public void testAddNewGroup() {
        app.getGroupHelper().SelectGroupPage();
        app.getGroupHelper().NewGroupAdd(new GroupData("TestGroup", "test header", "test footer"));
    }

}
