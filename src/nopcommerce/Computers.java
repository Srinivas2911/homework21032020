package nopcommerce;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;
/*
Class Computers
url = https://demo.nopcommerce.com/"
Test 1 : Click on the Computers
         Navigate to Computers page successfully
         verify the Text Computers
Test 2 : Product added to Cart
         Click on Computer
         Click on first item - Desktops
         Clink on Build your own computer or Add to Cart
         (Goes to next page)
         Select radio button for  HDD * 400 GB [+$100.00]
         Then Add to Cart
         And verify message "The product has been added to your shopping cart"
 */

public class Computers {

    WebDriver driver;

    @Before
    public void openBrowser() {

        String baseUrl = "https://demo.nopcommerce.com/";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test
    public void verifyUserCanNavigateToComputersPage() {
        WebElement computer = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]"));
        computer.click();

        WebElement computerText = driver.findElement(By.xpath("//h1[contains(text(),'Computers')]"));
        String expectedResult = "Computers";
        String actualResult = computerText.getText();
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println("Expected Result : "+ expectedResult);
        System.out.println("Actual Result : "+ actualResult);
    }

    @Test
    public void verifyMessageProductAddedtoCart() {
        WebElement computer = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]"));
        computer.click();

        WebElement computerText = driver.findElement(By.xpath("//h1[contains(text(),'Computers')]"));
        computerText.click();

        WebElement desktop = driver.findElement(By.xpath("//h2[@class='title']//a[contains(text(),'Desktops')]"));
        desktop.click();

        WebElement buildYourOwn = driver.findElement(By.xpath("//a[contains(text(),'Build your own computer')]"));
        buildYourOwn.click();

        WebElement gb400 = driver.findElement(By.xpath("//input[@id='product_attribute_3_7']"));
        gb400.click();

        WebElement addtoCart = driver.findElement(By.xpath("//input[@id='add-to-cart-button-1']"));
        addtoCart.click();

        WebElement productAdded = driver.findElement(By.xpath("//p[@class='content']"));
        String expectedResult = "The product has been added to your shopping cart";
        String actualResult = productAdded.getText();
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println(productAdded.getText());
            }

    @After
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}

