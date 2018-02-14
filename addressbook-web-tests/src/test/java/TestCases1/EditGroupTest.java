package TestCases1;

import model.GroupData;
import model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class EditGroupTest extends TestBase{

    @BeforeMethod
    public void insurePreconditions(){
        if(app.db().groups().size()==0){
            app.group().page();
            app.group().create(new GroupData().withName(app.readProperty("group.name")+System.currentTimeMillis())
                    .withHeader(app.readProperty("group.header"))
                    .withFooter(app.readProperty("group.footer")));
        }
    }

    @Test
    public void testEditFirstGroup(){
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId())
                .withName(modifiedGroup.getName())
                .withHeader(app.readProperty("group.header")+"2")
                .withFooter(app.readProperty("group.footer")+"2");

        app.group().page();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    }

}
