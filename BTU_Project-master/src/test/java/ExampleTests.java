import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;



import java.util.List;

public class ExampleTests {
    WebDriver driver;
    public ExampleTests(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }
    @Test
    public void progressBarTest() throws InterruptedException {


        // navigate to the demo page
        driver.get("https://demoqa.com/progress-bar");

        // find the 'Start' button and click it using JavascriptExecutor
        WebElement startButton = driver.findElement(By.id("startStopButton"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", startButton);

        // wait until progress bar reaches 100%

        // wait until progress bar reaches 100%
        WebElement progressBar = driver.findElement(By.xpath("//div[@role='progressbar']"));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.attributeToBe(progressBar, "class", "progress-bar bg-success"));
        // print
        System.out.println("მიაღწია 100%-მდე");
        // close the browser
        driver.quit();
    }



    @Test
    public void locatorTest(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/category&path=33");
        driver.manage().window().maximize();
        //*[@id="content"]/div/div[2]/h1 - XPath
        //#content > div > div.col-sm-4 > h1 - css
        List<WebElement> anchors = driver.findElements(By.className("product-thumb"));
        Assert.assertEquals(anchors.size(),2);
         WebElement button=driver.findElement(By.partialLinkText("Canon EOS 5D"));
        button.click();
    }
    @Test
    public void browserCommands(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://tutorialsninja.com/demo/index.php?route=product/category&path=33");
        // Get the title of the page
        String title = driver.getTitle();
        System.out.println("Title " + title);
        // Get the current URL
        String url = driver.getCurrentUrl();
        System.out.println("URL " + url);
        // Get the current page HTML source
        String html = driver.getPageSource();
        System.out.println("HTML " + html);
    }
}
