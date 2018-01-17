package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }
    public void login(String name, String passw) {
        type(By.name("user"), name);
        type(By.name("pass"), passw);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }

}
