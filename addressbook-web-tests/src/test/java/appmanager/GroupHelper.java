package appmanager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupHelper extends HelperBase {

    public GroupHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void SubmitGroupCreating() {
        click(By.name("submit"));
    }

    public void FillNewGroupField(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void CreateNewGroup() {
        click(By.name("new"));
    }

    public void SelectGroupPage() {
        click(By.linkText("groups"));
    }

    public void SelectFirstGroup() {
    }

    public void EditGroup() {
    }

    public void UpdateGroup() {
    }
}
