package TestCases1;

import model.GroupData;
import model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class DeleteGroupTest extends TestBase{

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
    public void testRemoveFirstGroup() {
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();

        app.group().page();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size()-1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withOut(deletedGroup)));
    }

}
