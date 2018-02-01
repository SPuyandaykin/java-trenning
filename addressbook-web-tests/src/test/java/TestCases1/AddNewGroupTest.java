package TestCases1;

import model.GroupData;
import model.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class AddNewGroupTest extends TestBase{

    @Test
    public void testAddNewGroup() {
        app.group().page();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("TestGroup"+System.currentTimeMillis())
                .withHeader("test header")
                .withFooter("test footer");
        app.group().create(group);

        assertThat(app.group().count(), equalTo(before.size()+1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
