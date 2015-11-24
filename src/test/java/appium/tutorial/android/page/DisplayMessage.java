package appium.tutorial.android.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static appium.tutorial.android.util.Helpers.driver;

/**
 * Created by eugenel on 4/26/2015.
 */
public abstract class DisplayMessage {

    /***
     * activityDisplay
     * @return
     */
    public static  String activityDisplay(){
        WebDriverWait driverWait = new WebDriverWait(driver, 30);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("android.widget.TextView")));
        WebElement textField = driver.findElement(By.id("com.example.eugenel.simpleaapp:id/activity_display"));
        return textField.getText();
    }

    /****
     * actionBar
     */
//    public static  String actionBar(){
//        WebElement abar = driver.findElement(By.id("com.example.eugenel.simpleaapp:id/action_bar"));
//        WebElement settings = abar.findElement(By.className("android.widget.ImageView"));
//        settings.click();
//        //Now we need to close it
//        WebElement text = driver.findElement(By.id("com.example.eugenel.simpleaapp:id/title"));
//        String settingsName = text.getText();
//        text.click();
//        return settingsName;
//    }

}
