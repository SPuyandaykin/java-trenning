package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{
    private FirefoxDriver wd;

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void ReturnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void ReturnToHomePage() {
        click(By.linkText("home page"));
    }

    public void OpenHomePage() {
        click(By.linkText("home"));
    }
}
