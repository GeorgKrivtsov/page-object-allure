package framework.managers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {

    private static DriverManager INSTANCE = null;

    private WebDriver driver;

    private DriverManager() {

    }

    public static DriverManager getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver(){
        if(driver == null) {
            initDriver();
        }
        return driver;
    }

    private void initDriver() {

        int temp = Integer.parseInt(System.getProperty("browser", "1"));

        switch (temp) {
            case 2:
                System.out.println("Launching Safari");
                driver = new SafariDriver();
                break;
            case 3:
                System.out.println("Launching Firefox");
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Launching Chrome");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

    }

    public void quitDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
