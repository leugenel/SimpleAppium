package appium.tutorial.android.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static appium.tutorial.android.util.Helpers.*;

/** Page object for the home page **/
public abstract class HomePage {

    /** Verify the home page has loaded.
     *  Click the accessibility button.
     *  Verify the accessibility page has loaded. **/
    public static void mainActivity(String setText) {
        loaded();
        WebElement textField = driver.findElement(By.className("android.widget.TextView"));
        textField.sendKeys(setText);
        WebElement button = driver.findElement(By.name("Send"));
        button.click();
    }


    /** Verify the home page has loaded **/
    public static void loaded() {
        find("Enter");
    }
}