package ecommerce;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Footer extends BaseTest {

    @Test
    public void footerValue(){

        WebElement fvt = driver.findElement(By.xpath("//footer/p"));

        String actualProductName = fvt.getText();
        String expectedProductName = "© 2019 GreenKart - buy veg and fruits online";

        Assert.assertEquals(actualProductName, expectedProductName);
    }
}
