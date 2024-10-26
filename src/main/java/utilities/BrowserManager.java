package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.io.FileInputStream;

//import utilities.BrowserFactory.browserType;

public class BrowserManager {

    public WebDriver driver;
    public Properties prop;
    public static ChromeOptions options;

    public WebDriver initializeDriver() throws IOException {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties")) {
            prop.load(fis);
        }

        String browserName = prop.getProperty("browser");
        if (browserName == null) {
            throw new IllegalArgumentException("Browser property is not specified in the properties file.");
        }

        switch (browserName.toLowerCase()) {
            case "chrome":
                options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe");
                driver = new ChromeDriver(options);
                break;

            case "firefox":
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\main\\resources\\geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "ie":
                // IE code
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
