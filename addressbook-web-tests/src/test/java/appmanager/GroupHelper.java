package appmanager;

import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
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
        click(By.name("selected[]"));
    }

    public void ReturnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void EditGroup() {
        click(By.name("edit"));
    }

    public void UpdateGroup() {
        click(By.name("update"));
    }

    public void ChangeGroupFooter(String text) {
        type(By.name("group_footer"), text);
    }

    public void DeleteGroup() {
        click(By.name("delete"));
    }

    public void NewGroupAdd(GroupData groupData) {
        CreateNewGroup();
        FillNewGroupField(groupData);
        SubmitGroupCreating();
        ReturnToGroupPage();
    }

    public boolean isAnyGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
