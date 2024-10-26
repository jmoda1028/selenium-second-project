package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import utilities.BrowserManager;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class BaseTest extends BrowserManager{

    public WebDriver driver;

    @BeforeClass
    public void setup() throws IOException {
        loadProperties(); // Load properties once for the whole test class
    }

    @BeforeMethod
    public void initialize() {
        try {
            driver = initializeDriver(); // Initialize driver
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (driver == null) {
            throw new IllegalStateException("WebDriver initialization failed.");
        }
        driver.get(prop.getProperty("url")); // Ensure driver is not null before this line
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit(); // Quit the driver after each test method
        }
    }

    @AfterClass
    public void cleanUp() {
        // Clean up or any final actions can be added here
    }

    private void loadProperties() throws IOException {
        prop = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\data.properties")) {
            prop.load(fis);
        }
    }

}
