import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.Iterator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnhandledAlertException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Quiz1 {
    WebDriver driver;

    public Quiz1() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.driver = new ChromeDriver(options);
    }

    @Test
    public void locatorTest() {
        driver.get("https://demoqa.com/login");

        // locate username and password fields and enter values
        WebElement username = driver.findElement(By.id("userName"));
        WebElement password = driver.findElement(By.id("password"));
        username.sendKeys("test123");
        password.sendKeys("Automation@123");
        driver.manage().window().maximize();

        // click on login button
        WebElement loginBtn = driver.findElement(By.id("login"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn));

        try {
            loginBtn.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", loginBtn);
        }

        // wait for logout button to appear
        WebElement logoutBtn = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-primary")));


        // Assert that the logout button is displayed
        WebElement logoutButton = driver.findElement(By.id("submit"));
        Assert.assertTrue(logoutButton.isDisplayed());

        // Navigate to the book store
        driver.get("https://demoqa.com/books");

        // Assert that 8 books are returned in the table

        WebElement bookTable = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("rt-tbody")));
        int bookCount = bookTable.findElements(By.className("action-buttons")).size();

        Assert.assertEquals(bookCount, 8);





        // Click on the 'Git Pocket Guide' book
        WebElement gitBookLink = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/books?book=9781449325862' and contains(text(),'Git Pocket Guide')]")));
        gitBookLink.click();


        // Assert that the book details page is opened
        String expectedTitle = "Git Pocket Guide";
        WebElement bookTitle = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@id='userName-value' and text()='Git Pocket Guide']")));
        String actualTitle = bookTitle.getText();
        Assert.assertEquals(expectedTitle, actualTitle);




        // Click on the 'Add to your collection' button
        WebElement addToCollectionButton = driver.findElement(By.xpath("//button[@id='addNewRecordButton' and text()='Add To Your Collection']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCollectionButton);
        addToCollectionButton.click();





        // Assert that the 'Book already present in your collection!' text appears
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            System.out.println("Alert text: " + alertText);
            alert.accept();
        } catch (NoAlertPresentException e) {
            // Handle the case when no alert is present
            System.out.println("No alert present");
        }




        // Go back to the book store
        WebElement backk = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='addNewRecordButton' and text()='Back To Book Store']")));
        backk.click();

        try {
            backk.click();
        } catch (Exception e) {
            // Handle the case when no alert is present
            System.out.println("No alert present");
        }
        // Assert that 8 books are returned in the table
        driver.get("https://demoqa.com/books?book=9781449325862");
        WebElement bakk = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='addNewRecordButton' and text()='Back To Book Store']")));
        bakk.click();

        WebElement bokTable = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("rt-tbody")));
        int bookCont = bokTable.findElements(By.className("action-buttons")).size();

        Assert.assertEquals(bookCont, 8);

        // Log out
        WebElement logoutLink = driver.findElement(By.id("submit"));
        logoutLink.click();

        // Assert that the login page is displayed
        WebElement loginPageHeader = driver.findElement(By.xpath("//h2[text()='Welcome,']"));
        Assert.assertTrue(loginPageHeader.isDisplayed());
    }

}