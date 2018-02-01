package appmanager;

import model.GroupData;
import model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void page() {
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

    public void create(GroupData groupData) {
        CreateNewGroup();
        FillNewGroupField(groupData);
        SubmitGroupCreating();
        ReturnToGroupPage();
    }

    public void modify(GroupData group) {
        SelectGroupById(group.getId());
        EditGroup();
        FillNewGroupField(group);
        UpdateGroup();
        ReturnToGroupPage();
    }

    public boolean size() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData()
                    .withId(id).withName(name).withHeader(null).withFooter(null);
            groups.add(group);
        }
        return groups;
    }

    public Groups all() {
        Groups groups = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData()
                    .withId(id).withName(name).withHeader(null).withFooter(null);
            groups.add(group);
        }
        return groups;
    }

    public void SelectGroup(int groupIndex) {
        WebElement element = wd.findElements(By.cssSelector("span.group")).get(groupIndex);
        element.findElement(By.name("selected[]")).click();
    }

    public void SelectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void delete(int index) {
        SelectGroup(index);
        DeleteGroup();
        ReturnToGroupPage();
    }

    public void delete(GroupData group) {
        SelectGroupById(group.getId());
        DeleteGroup();
        ReturnToGroupPage();
    }
}
