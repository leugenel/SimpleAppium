package appium.tutorial.android.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static appium.tutorial.android.util.Helpers.*;

/** Page object for the home page **/
public abstract class HomePage {

    public static String myText = "Eugene Mobile";
    public static String settingsText = "Settings";

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

    public static  String actionBar(){
        WebElement abar = driver.findElement(By.id("com.example.eugenel.simpleaapp:id/action_bar"));
        WebElement settings = abar.findElement(By.className("android.widget.ImageView"));
        settings.click();
        //Now we need to close it
        WebElement text = driver.findElement(By.id("com.example.eugenel.simpleaapp:id/title"));
        String settingsName = text.getText();
        text.click();
        return settingsName;
    }

    /** Verify the home page has loaded **/
    public static void loaded() {
        find("Enter");
    }
}