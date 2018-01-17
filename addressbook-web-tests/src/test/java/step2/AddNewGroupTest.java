package step2;

import org.testng.annotations.Test;

public class AddNewGroupTest extends TestBase{

    @Test
    public void testAddNewGroup() {
        SelectGroupPage();
        CreateNewGroup();
        FillNewGroupField(new GroupData("TestGroup", "test header", "test footer"));
        SubmitGroupCreating();
        ReturnToGroupPage();
    }

}
