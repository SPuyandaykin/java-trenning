package appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import unilities.StringUtilities;

public class HelperBase {
    protected WebDriver wd;
    protected StringUtilities strUtility;

    public HelperBase(WebDriver wd) {

        this.wd = wd;
        strUtility = new StringUtilities();
    }

    protected void type(By locator, String text) {
        if(text.length()== 0)
            return;
        click(locator);
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    protected String getLabel(By locator) {
        return wd.findElement(locator).getAttribute("value");
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public boolean isElementPresent (By locator){
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
