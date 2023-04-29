import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Task2Tests {
    WebDriver driver;
    public Task2Tests(){
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
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        // Choose programming language from dropdown and check that it was selected
        WebElement dropdown = driver.findElement(By.id("dropdowm-menu-1"));
        dropdown.click();
        WebElement javaOption = driver.findElement(By.xpath("//option[@value='python']"));
        javaOption.click();
        Assert.assertTrue(javaOption.isSelected(), "Java option is not selected");

        // Click on all unselected checkboxes
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox' and not(@checked)]"));
        for (WebElement checkbox : checkboxes) {
            checkbox.click();
            Assert.assertTrue(checkbox.isSelected(), checkbox.getAttribute("value") + " checkbox is not selected");
        }

        // Click on 'Purple' radio button
        WebElement purpleRadioButton = driver.findElement(By.xpath("//input[@value='purple']"));
        purpleRadioButton.click();
        Assert.assertTrue(purpleRadioButton.isSelected(), "Purple radio button is not selected");

        // In 'Selected & Disabled' section check that 'Orange' option in dropdown is disabled
        WebElement selectedAndDisabledSection = driver.findElement(By.id("fruit-selects"));
        WebElement orangeOption = selectedAndDisabledSection.findElement(By.xpath(".//option[@value='orange']"));
        Assert.assertFalse(orangeOption.isEnabled(), "Orange option is enabled");


    }

}
