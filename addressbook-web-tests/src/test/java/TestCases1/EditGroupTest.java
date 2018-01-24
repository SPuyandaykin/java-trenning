package TestCases1;

import model.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class EditGroupTest extends TestBase{

    @Test
    public void testEditFirstGroup(){
        app.getGroupHelper().SelectGroupPage();
        if(!app.getGroupHelper().isAnyGroup())
            app.getGroupHelper().NewGroupAdd(new GroupData("TestGroup"+System.currentTimeMillis(),
                    "test header", "test footer"));
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().SelectGroup(before.size()-1);
        app.getGroupHelper().EditGroup();
        GroupData oldGroup = before.get(before.size()-1);
        GroupData group = new GroupData(oldGroup.getId(),oldGroup.getName(),"test header2", "test footer2");
        app.getGroupHelper().FillNewGroupField(group);
        app.getGroupHelper().UpdateGroup();
        app.getGroupHelper().ReturnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(group);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
