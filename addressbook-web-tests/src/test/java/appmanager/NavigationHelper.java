package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void ReturnToGroupPage() {
        wd.findElement(By.linkText("group page")).click();
    }

    public void ReturnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }
}
