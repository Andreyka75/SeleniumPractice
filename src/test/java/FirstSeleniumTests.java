import org.apache.commons.compress.archivers.zip.ScatterZipOutputStream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FirstSeleniumTests {
    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    private static WebDriver driver;

    @BeforeAll
    public static void classSetup(){
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
    }
    @AfterAll
    public static void classTearDown(){
        SharedDriver.closeDriver();
    }
    @AfterEach
    public void testTearDown(){
        driver.get(HOME_PAGE_URL);
    }

    @Test
    public void homePageURLTest(){

       String actualURL = driver.getCurrentUrl();
       assertEquals(HOME_PAGE_URL,actualURL,"URLs do not match");

    }
    @Test
    public void findEmailFieldTest(){
        //WebElement element = driver.findElement(By.id("email"));
        //WebElement element = driver.findElement(By.name("email"));
        //WebElement element = driver.findElement(By.linkText("Create a Page"));
        //WebElement element = driver.findElement(By.partialLinkText(" a Page"));
        //WebElement element = driver.findElement(By.cssSelector("#email"));
        //WebElement element = driver.findElement(By.className("inputtext"));
        List<WebElement>element = driver.findElements(By.className("inputtext"));
        System.out.println(element.size());
        assertNotNull(element);
    }
    @Test
    public void findElementByXpathTest(){
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));
        assertNotNull(emailElement);
        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-testid='royal_pass']"));
        assertNotNull(passwordElement);
        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement);
        //WebElement forgotPassElement = driver.findElement(By.xpath("//*[text()='Forgot password?']"));
        //assertNotNull(forgotPassElement);
        WebElement creatNewAccountButton = driver.findElement(By.xpath("//*[text()='Create new account']"));
        assertNotNull(creatNewAccountButton);
    }
    @Test
    public void loginScreenTest() {
        WebElement emailElement = driver.findElement(By.xpath("//input[@name='email']"));
        assertNotNull(emailElement);
        emailElement.sendKeys("andrey.katsevman@gmail.com");
        String emailValue = emailElement.getAttribute("value");
        assertEquals("andrey.katsevman@gmail.com",emailValue);

        WebElement passwordElement = driver.findElement(By.xpath("//input[@data-testid='royal_pass']"));
        assertNotNull(passwordElement);
        passwordElement.sendKeys("12345");
        String passwordValue = passwordElement.getAttribute("value");
        assertEquals("12345",passwordValue);

        WebElement loginButtonElement = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(loginButtonElement);
        loginButtonElement.click();
    }
    @Test
    public void signupTest(){
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));

    }
    @Test
    public void gendersTest() throws InterruptedException {

        String femaleXpath = "//*[@name = 'sex' and @value = 1]";
        String maleXpath = "//*[@name = 'sex' and @value = 2]";

        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));
        Thread.sleep(1000);

        //Verify female gender is checked
        WebElement femaleButton = driver.findElement(By.xpath( femaleXpath));
        femaleButton.click();
        String isFemaleChecked = driver.findElement(By.xpath( femaleXpath)).getAttribute("checked");
        assertNotNull(isFemaleChecked);
        assertEquals("true",isFemaleChecked);

        //Verify male gender is checked
        WebElement maleButton = driver.findElement(By.xpath( maleXpath));
        maleButton.click();
        String isMaleChecked = driver.findElement(By.xpath( maleXpath)).getAttribute("checked");
        Thread.sleep(1000);
        assertNotNull(isMaleChecked);
        assertEquals("true",isMaleChecked);


    }
    @Test
    public void errorMessageTest() throws InterruptedException {

        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name = 'websubmit']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@name='reg_email__']")).click();

        WebElement error = driver.findElement(By.xpath("//*[contains(text(), 'to reset your password')]"));
        assertNotNull(error);
    }
    @Test
    public void yearTest()throws InterruptedException{
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id = 'year']")).click();
        driver.findElement(By.xpath("//*[text() = '1990']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title = 'Year']")).getAttribute("value");

        assertEquals("1990",yearValue);

    }

    @ParameterizedTest
    @ValueSource(strings = {"1905","1950","2020"})
    public void yearTestParametrized(String yearInput) throws InterruptedException{
        driver.findElement(By.xpath("//*[text() = 'Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//*[@id = 'year']")).click();
        driver.findElement(By.xpath("//*[text() = '"+yearInput+"']")).click();
        String yearValue = driver.findElement(By.xpath("//*[@title = 'Year']")).getAttribute("value");

        assertEquals(yearInput,yearValue);

    }
    @Test
    public void actionTest(){
        driver.get("https://www.daviktapes.com/");
        //pause();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() = 'Company2']")));
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[text() = 'Company2']")));

        WebElement element = driver.findElement(By.xpath("//a[text() = 'Company2']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    @Test
    public void waitAndClickTest() {
        driver.get("https://www.daviktapes.com/");
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text() = 'Company']"))).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = 'Company']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text() = 'Company']"))).click();
        pause();
    }

    public void pause(){
        try{
            Thread.sleep(5000);
        }
        catch (Exception error) {
            System.out.println("Something went wrong");
        }
    }




}
