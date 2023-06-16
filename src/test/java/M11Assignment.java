import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class M11Assignment {

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";
    private static WebDriver driver;

    @BeforeAll
    public static void createNewAccountTest() throws InterruptedException {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
        driver.findElement(By.xpath("//*[text()='Create new account']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[text() = 'Sign Up']")));
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@name = 'websubmit']")).click();
        Thread.sleep(3000);
    }
    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();
    }
    @Test
    public void errorMessageFirstNameTest() {
        driver.findElement(By.xpath("//*[@name='firstname']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'your name')]")));
    }
    @Test
    public void errorMessageLastNameTest () {
        driver.findElement(By.xpath("//*[@name='lastname']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'your name')]")));
    }
    @Test
    public void errorMessageMobileOrEmailTest () throws InterruptedException {
        driver.findElement(By.xpath("//*[@name='reg_email__']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'you ever need to reset')]")));
        driver.findElement(By.xpath("//*[@name='reg_passwd__']")).sendKeys("vasili12@gmail.com");
        assertNotNull(driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//input[@name='reg_email_confirmation__']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 're-enter your email address')]")));
    }
    @Test
    public void errorMessagePasswordTest () {
        driver.findElement(By.xpath("//*[@name='reg_passwd__']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'at least six numbers')]")));
    }
    @Test
    public void errorMessageBirthdayTest () throws InterruptedException {
        driver.findElement(By.xpath("//*[@title='Month']")).click();
        Thread.sleep(3000);
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'Please be sure to use')]")));
    }
    @Test
    public void errorMessageAgeTest (){
        driver.findElement(By.xpath("//*[@name = 'websubmit']")).click();
        driver.findElement(By.xpath("//*[@name = 'websubmit']")).click();
        driver.findElement(By.xpath("//*[@name='birthday_age']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'Please enter your age')]")));

    }
    @Test
    public void errorMessageGenderTest (){
        driver.findElement(By.xpath("//*[@name='firstname']")).sendKeys("Andrey");
        driver.findElement(By.xpath("//*[@name='lastname']")).sendKeys("Katsevman");
        driver.findElement(By.xpath("//*[@name='reg_email__']")).sendKeys("2045555555");
        driver.findElement(By.xpath("//*[@name='reg_passwd__']")).sendKeys("abc1234");
        driver.findElement(By.xpath("//*[@id='year']//descendant::*[@value='2004']")).click();
        driver.findElement(By.xpath("//*[@name = 'websubmit']")).click();
        assertNotNull(driver.findElement(By.xpath("//*[contains(text(), 'Please choose a gender')]")));


    }

}





