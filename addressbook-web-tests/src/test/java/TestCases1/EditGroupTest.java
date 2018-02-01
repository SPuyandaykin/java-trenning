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
        app.group().page();
        if(!app.group().size())
            app.group().create(new GroupData().withName("TestGroup"+System.currentTimeMillis())
                    .withHeader("test header")
                    .withFooter("test footer"));
    }

    @Test
    public void testEditFirstGroup(){
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName(modifiedGroup.getName()).withHeader("test header2").withFooter("test footer2");
        app.group().modify(group);

        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    }

}
