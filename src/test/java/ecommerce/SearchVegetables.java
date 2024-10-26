package ecommerce;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class SearchVegetables extends BaseTest {

//    By searchInputBox = By.xpath("//input[@type='search']");

    @Test
    public void searchValue() {
        WebElement searchInputBox = driver.findElement(By.xpath("//input[@type='search']"));
        searchInputBox.clear();
        searchInputBox.sendKeys("Cucumber");
        searchInputBox.submit(); // Submit to trigger search
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Use a wait to ensure the product name element is present
        WebElement productNameElement = driver.findElement(By.xpath("//h4[@class='product-name']"));
        String actualProductName = productNameElement.getText();
        String expectedProductName = "Cucumber - 1 Kg";

        Assert.assertEquals(actualProductName, expectedProductName);
    }

}
