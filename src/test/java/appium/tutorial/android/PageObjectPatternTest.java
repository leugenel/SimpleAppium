package appium.tutorial.android;

import appium.tutorial.android.page.DisplayMessage;
import appium.tutorial.android.page.HomePage;
import appium.tutorial.android.util.AppiumTest;
import org.junit.Assert;

public class PageObjectPatternTest extends AppiumTest {

    @org.junit.Test
    public void pageObject() throws Exception {
        String myText = "Eugene Mobile";
        logger.info("Entry to pageObject");
        //DisplayMessage.actionBar();

        HomePage.mainActivity(myText);
        String text= DisplayMessage.activityDisplay();
        Assert.assertEquals(myText, text);

        //DisplayMessage.actionBar();

    }
}