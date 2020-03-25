package nopcommerce;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.stream.Collectors.toList;

/*
url = "https://demo.nopcommerce.com/"
Class Electronics
Test 1  mouseHoverToElectronics
          mouse hover to Electronics
          mouse hover to Camera & photo
          verify text Camera & Photo
Test 2 : Position Price : Low to High
         verify descending order
         Use select method.
*/
public class Electronics {

    WebDriver driver;
    private JavascriptExecutor jse;

    @Before
    public void openBrowser() {

        String baseUrl = "https://demo.nopcommerce.com/";
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
        jse = (JavascriptExecutor) driver;
    }

    @Test
    public void mouseOverElectronics() throws InterruptedException {
        jse.executeScript("window.scrollBy(0, 800)");
        WebElement electronis = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronis).perform();
        Thread.sleep(3000);

        WebElement cameraPhoto = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Camera & photo')]"));
        actions.moveToElement(cameraPhoto).click().perform();

        WebElement cameraMessage = driver.findElement(By.xpath("//h1[contains(text(),'Camera & photo')]"));
        String expectedResult = "Camera & photo";
        String actualResult = cameraMessage.getText();
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println("Expected Result : "+ expectedResult);
        System.out.println("Actual Result : "+ actualResult);
    }

    @Test
    public void assertPositionLowToHigh() {
        jse.executeScript("window.scrollBy(0, 800)");
        WebElement electronis = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Electronics')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronis).perform();
        WebElement cameraPhoto = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Camera & photo')]"));
        actions.moveToElement(cameraPhoto).click().perform();

        //Select method
        WebElement sortByPosition = driver.findElement(By.xpath("//select[@id='products-orderby']"));
        Select select = new Select(sortByPosition);
        select.selectByVisibleText("Price: Low to High");

        // Bringing Price elements and sorting my comparison
        List<WebElement> elements = driver.findElements(By.className("prices"));
        List<String> webSortedPrices = elements.stream().map(WebElement::getText).collect(toList());
        List<String> mySortedPrices = new ArrayList<>();
        for (WebElement element : elements) {
            String text = element.getText();
            mySortedPrices.add(text);
        }
        //mySortedPrices.sort(Comparator.comparingInt(s -> Integer.valueOf(s)));
        Assert.assertEquals(webSortedPrices, mySortedPrices);
        System.out.println("Expected Result : "+ mySortedPrices);
        System.out.println("Actual Result : "+ webSortedPrices);
    }

    @After
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
