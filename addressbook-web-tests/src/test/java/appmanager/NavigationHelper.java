package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void ReturnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void ReturnToHomePage() {
        click(By.linkText("home page"));
    }
}
