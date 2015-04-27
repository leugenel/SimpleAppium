package appium.tutorial.android.util;

import appium.tutorial.android.page.HomePage;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.logging.LogFactory;
import static appium.tutorial.android.util.Helpers.driver;

public class AppiumTest {

    static {
        // Disable annoying cookie warnings.
        // WARNING: Invalid cookie header
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
    }

    public static Logger logger = Logger.getLogger(AppiumTest.class);;

    /** Page object references. Allows using 'home' instead of 'HomePage' **/
    protected HomePage home;

    /** wait wraps Helpers.wait **/
    public static WebElement wait(By locator) {
        return Helpers.wait(locator);
    }


    @Rule
    public TestRule printTests = new TestWatcher() {
        protected void starting(Description description) {
            System.out.print("  test: " + description.getMethodName());
        }

        protected void finished(Description description) {
            System.out.println();
        }
    };

    /** Keep the same date prefix to identify job sets. **/
    private static Date date = new Date();

    /** Run before each test **/
    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium-version", "1.1.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "GoogleNexus5");
        capabilities.setCapability("platformVersion", "4.4");

        // Set job name on Sauce Labs
        //capabilities.setCapability("name", "Java Android tutorial " + date);
        String userDir = System.getProperty("user.dir");

        URL serverAddress;
        String localApp = "app-debug.apk";
        String appPath = Paths.get("D:\\TryProjects\\SimpleAApp\\app\\build\\outputs\\apk",
                localApp).toAbsolutePath().toString();
        System.out.println("Eugene LOG app path:"+appPath);
        capabilities.setCapability("app", appPath);
        serverAddress = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(serverAddress, capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Helpers.init(driver, serverAddress);
    }

    /** Run after each test **/
    @After
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();
    }

}