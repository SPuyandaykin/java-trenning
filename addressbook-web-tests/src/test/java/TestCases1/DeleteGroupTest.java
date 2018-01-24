package TestCases1;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DeleteGroupTest extends TestBase{

    @Test
    public void testRemoveFirstGroup() {
        app.getGroupHelper().SelectGroupPage();
        if(!app.getGroupHelper().isAnyGroup())
            app.getGroupHelper().NewGroupAdd(new GroupData("TestGroup"+System.currentTimeMillis(),
                    "test header", "test footer"));
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().SelectGroup(before.size()-1);
        app.getGroupHelper().DeleteGroup();
        app.getGroupHelper().ReturnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size()-1);

        before.remove(before.size()-1);
        Assert.assertEquals(after, before);
    }
}
