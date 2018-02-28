package appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Signup']"));
    }

    public void logIn(String username, String password) {
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[value='Login']"));
    }

    public void finish(String comfirmationLink, String password) {
        wd.get(comfirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }

    public boolean checkLogin(String user) {
        String sUser = wd.findElement(By.xpath("//td[@class='login-info-left']/span")).getText();
        return sUser.equals(user);
    }

    public void manageUserPage() {
        click(By.xpath("//a[contains(text(),'Manage User')]"));
    }

    public void selectUser(String user) {
        click(By.xpath("//a[contains(text(),'"+user+"')]"));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }
}
