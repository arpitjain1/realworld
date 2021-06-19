package conduit.hooks;

import config.TestConfig;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Setup {

    public static String envFileLocation = null;
    private static final String propertiesFile = "build/resources/test/config/";
    private static boolean SysPropsset = false;
    public static WebDriver driver;

    // hook to be executed before every scenario
    @Before
    public void BeforeEverything (Scenario scenario) {
        // For single time settings, just before executing
        if (!SysPropsset) {
            envFileLocation = propertiesFile + "env.properties";
            System.setProperty("PROPERTY_FILE", envFileLocation);
            System.out.println("prop file set in before hook" + envFileLocation);
            System.setProperty("webdriver.chrome.driver","/Users/ajain1/github/realworld/src/test/resources/lib/chromedriver");
            System.out.println("Capabilities and preferences for Chrome browser has been set");
            SysPropsset=true;
        }
    }

    // Setting chrome driver and launching url
    @Before("@filterbytag")
    public void BeforeFilterByTags (Scenario scenario) throws Exception {
        System.out.println("scenario "+ scenario.getName() + " started");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String path = TestConfig.getResource("hosturl");
        driver.get(path);
    }
}
