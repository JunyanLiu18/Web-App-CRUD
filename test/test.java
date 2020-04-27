import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import org.junit.Assert;

import java.util.List;

public class test {
    @Test
    public void addTest(){
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Navigate to a web page

        driver.get("http://localhost:8080/UserTest_war_exploded/");

        // Perform actions on HTML elements, entering text and submitting the form
        WebElement yearElement     = driver.findElement(By.name("year"));
        WebElement modelElement     = driver.findElement(By.name("model"));
        WebElement makeElement     = driver.findElement(By.name("make"));
        WebElement formElement        = driver.findElement(By.tagName("form"));

        yearElement.sendKeys("2018");
        modelElement.sendKeys("Crown");
        makeElement.sendKeys("Toyota");

        //modelElement.submit(); // submit by text input element
        formElement.submit();        // submit by form element


        // Anticipate web browser response, with an explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement messageElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Toyota']"))
        );

        // Run a test
        String message                 = messageElement.getText();
        String successMsg             = "Toyota";
        Assert.assertEquals (message, successMsg);
        // Conclude a test
        driver.quit();
    }

    @Test
    public void deleteTest(){
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Downloads/VehicleCRUD/web/WEB-INF/lib/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:8080/UserTest_war_exploded/list");

        // Perform actions on HTML elements, entering text and submitting the form
        driver.findElement(By.xpath("//a[@href='delete?id=1']")).click();

        List<WebElement> deleteLinks = driver.findElements(By.xpath("//*[text()='Civic']"));

        Assert.assertTrue (deleteLinks.isEmpty());
        // Conclude a test
        driver.quit();
    }

    @Test
    public void editTest(){
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:8080/UserTest_war_exploded/list");

        driver.findElement(By.xpath("//a[@href='edit?id=2']")).click();

        WebElement modelElement     = driver.findElement(By.name("model"));
        WebElement formElement        = driver.findElement(By.tagName("form"));

        modelElement.clear();
        modelElement.sendKeys("i8");
        formElement.submit();        // submit by form element

        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement messageElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='i8']"))
        );

        // Run a test
        String message                 = messageElement.getText();
        String successMsg             = "i8";
        Assert.assertEquals (message, successMsg);
        // Conclude a test
        driver.quit();
    }


    @Test
    public void searchTest(){
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Downloads/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Navigate to a web page

        driver.get("http://localhost:8080/UserTest_war_exploded/search");

        // Perform actions on HTML elements, select from dropdown, entering text and submitting the form
        Select item = new Select(driver.findElement((By.name("item"))));
        item.selectByVisibleText("Model");
        WebElement valElement     = driver.findElement(By.name("val"));
        WebElement formElement        = driver.findElement(By.tagName("form"));

        valElement.sendKeys("Crown");

        //modelElement.submit(); // submit by text input element
        formElement.submit();        // submit by form element


        // Anticipate web browser response, with an explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement messageElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Crown']"))
        );

        // Run a test
        String message                 = messageElement.getText();
        String successMsg             = "Crown";
        Assert.assertEquals (message, successMsg);
        // Conclude a test
        driver.quit();
    }
}