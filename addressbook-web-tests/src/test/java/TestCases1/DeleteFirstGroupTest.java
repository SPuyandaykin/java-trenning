package TestCases1;

import org.testng.annotations.Test;

public class DeleteFirstGroupTest extends TestBase{

    @Test
    public void testRemoveFirstGroup() {
        app.getGroupHelper().SelectGroupPage();
        app.getGroupHelper().SelectFirstGroup();
        app.getGroupHelper().DeleteGroup();
    }
}
