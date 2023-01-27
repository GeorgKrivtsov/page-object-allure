package framework.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

public class DriverManager {

    private static DriverManager INSTANCE = null;

    private static WebDriver driver;

    private DriverManager() {

    }

    public static DriverManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initRemoteDriver();
        }
        return driver;
    }

//    private void initDriver() {

//        String temp = System.getProperty("browser", "Chrome");
//
//        switch (temp) {
//            case "Safari":
//                System.out.println("Launching Safari");
//                WebDriverManager.safaridriver().setup();
//                driver = new SafariDriver();
//                break;
//            case "Firefox":
//                System.out.println("Launching Firefox");
//                WebDriverManager.firefoxdriver().setup();
//                driver = new FirefoxDriver();
//                break;
//            case "remote":
//                DesiredCapabilities capabilities = new DesiredCapabilities();
//                capabilities.setCapability("browserName", "chrome");
//                capabilities.setCapability("browserVersion", "109.0");
//                capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                        "enableVNC", true,
//                        "enableVideo", true
//                ));
//                try {
//                    driver = new RemoteWebDriver(
//                            URI.create("http://selenoid:4444/wd/hub").toURL(),
//                            capabilities
//                    );
//                } catch (MalformedURLException e) {
//                    throw new RuntimeException(e);
//                }
//
//            default:
//                System.out.println("Launching Chrome");
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//                break;
//        }
//
//    }

    private static void initRemoteDriver() {
        String browser = System.getProperty("browser", "chrome");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setBrowserName(browser);
        switch (browser) {
            case "chrome":
            case "firefox":
                capabilities.setVersion("109.0");
                break;
            case "opera":
                capabilities.setVersion("94.0");
        }
        try {
            driver = new RemoteWebDriver(URI.create("http://149.154.71.152:4444/wd/hub").toURL(), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
