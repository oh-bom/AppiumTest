package base;

import PageObjects.LandingPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    public AndroidDriver driver;
    public LandingPage landingPage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        System.out.println("base test setup");
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setApp(System.getProperty("user.dir") + "/app/General-Store.apk")
                .setAutomationName("UiAutomator2")
                .setNoReset(true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        System.out.println("driver is initialized");
        landingPage = new LandingPage(driver);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
