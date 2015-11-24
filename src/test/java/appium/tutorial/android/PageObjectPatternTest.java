package appium.tutorial.android;

import appium.tutorial.android.page.DisplayMessage;
import appium.tutorial.android.page.HomePage;
import appium.tutorial.android.util.AppiumTest;
import org.junit.Assert;

public class PageObjectPatternTest extends AppiumTest {

    @org.junit.Test
    public void pageObject() throws Exception {
        Assert.assertEquals(HomePage.settingsText, HomePage.actionBar());
        HomePage.mainActivity(HomePage.myText);
        Assert.assertEquals(HomePage.myText, DisplayMessage.activityDisplay());

        //DisplayMessage.actionBar();

    }
}