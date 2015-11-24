package appium.tutorial.android.util;

import appium.tutorial.android.page.HomePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteStreamHandler;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

        PropertiesUtil prop = new PropertiesUtil();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.VERSION, "1.1.0");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getDeviceP().getProperty("platformName"));
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getDeviceP().getProperty("deviceName"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getDeviceP().getProperty("platformVersion"));
        capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, prop.getPackageP().getProperty("appPackage"));
        capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, prop.getPackageP().getProperty("appActivity"));
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");


        String localApp = prop.getPackageP().getProperty("Name");
        String appPath = Paths.get(prop.getPackageP().getProperty("localPath"),
                localApp).toAbsolutePath().toString();
        System.out.println("App path:"+appPath);
        capabilities.setCapability(MobileCapabilityType.APP, appPath);

        URL serverAddress;
        serverAddress = new URL("http://127.0.0.1:4723/wd/hub");

        startAppiumServer();

        driver = new AndroidDriver(serverAddress, capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /** Run after each test **/
    @After
    public void tearDown() throws Exception {
        if (driver != null) driver.quit();

        stopAppiumServer();
    }

    /***
     *
     */
    public  void startAppiumServer() throws IOException {
        CommandLine command = new CommandLine("cmd");
        command.addArgument("/c");
        command.addArgument("D:\\Appium\\node.exe");
        command.addArgument("D:\\Appium\\node_modules\\appium\\bin\\appium.js");
        command.addArgument("--address");
        command.addArgument("127.0.0.1",false);
        command.addArgument("--port",false);
        command.addArgument("4723",false);
        command.addArgument("--bootstrap-port",false);
        command.addArgument("4724",false);
        command.addArgument("--selendroid-port",false);
        command.addArgument("8082",false);
        command.addArgument("--no-reset",false);
        command.addArgument("--local-timezone");
        command.addArgument("--log");
        command.addArgument("D:\\Appium\\appiumServerLogs.txt");
        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);

        InputStream is = new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
        executor.getStreamHandler().setProcessOutputStream(is);

        try {
            System.out.println("Going to execute: "+ command);
            executor.execute(command, resultHandler);
            for (int i=1; i<10; i++) {
                int nRead = is.read();
                if(nRead!=0)
                    break;
                Thread.sleep(5000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public  void stopAppiumServer(){
        CommandLine command = new CommandLine("cmd");
        command.addArgument("/c");
        command.addArgument("taskkill");
        command.addArgument("/F");
        command.addArgument("/IM");
        command.addArgument("node.exe");

        DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);

        try {
            executor.execute(command, resultHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}