package appium.tutorial.android.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * Created by eugenel on 5/6/2015.
 */
 public class PropertiesUtil {

    final String devicePath = "device.properties";
    final String packagePath = "package.properties";

    private Properties deviceP;
    public Properties getDeviceP() {
        return deviceP;
    }

    private Properties packageP;
    public Properties getPackageP() {
        return packageP;
    }


    public  PropertiesUtil(){
        deviceP = setupPropertie(devicePath);
        packageP = setupPropertie(packagePath);
    }

    /**
     * @param file
     * @return
     */
    private Properties setupPropertie(String file) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = getClass().getClassLoader().getResourceAsStream(file);
            if (input == null) {
                System.out.println("Sorry, unable to find " + file);
                return null;
            }
            //load a properties file from class path, inside static method
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return prop;
    }
}
