import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.validator.routines.EmailValidator;
import org.openqa.selenium.support.ui.Select;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class M10AssignmentTest {

    private static final String HOME_PAGE_URL = "https://www.facebook.com/";

    private static WebElement FirstNameElement;
    private static WebElement lastNameElement;
    private static WebElement createNewAccountElement;
    private static WebElement mobileNumOrEmail;
    private static WebElement newPassword;
    private static WebElement SIGN_UP;

    private static WebDriver driver;



    @BeforeAll
    public static void createNewAccountTest() throws InterruptedException {
        driver = SharedDriver.getWebDriver();
        driver.get(HOME_PAGE_URL);
        createNewAccountElement = driver.findElement(By.xpath("//*[text()='Create new account']"));
        assertNotNull(createNewAccountElement);
        createNewAccountElement.click();
        Thread.sleep(3000);
    }


    @AfterAll
    public static void classTearDown() {
        SharedDriver.closeDriver();
    }

    @AfterEach
    public void clearFields() {
        FirstNameElement.clear();
        lastNameElement.clear();
        mobileNumOrEmail.clear();
        newPassword.clear();
    }


    @Test
    public void signUpPopupVisibilityTest() {

        FirstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        assertNotNull(FirstNameElement);

        lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        assertNotNull(lastNameElement);

        mobileNumOrEmail = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        assertNotNull(mobileNumOrEmail);

        newPassword = driver.findElement(By.xpath("//input[@id='password_step_input']"));
        assertNotNull(newPassword);

        SIGN_UP = driver.findElement(By.xpath("//button[@type='submit']"));
        assertNotNull(SIGN_UP);

    }

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("Vasily", "Katsevman", "2045585658", "abc1234!")
//                Arguments.of("vasili", "KATSEVMAN", "2045585658", "abc1234!"),
//                Arguments.of("v", "t", "2045585658", ""),
//                Arguments.of("sss", "Katsevman", "545353", "abc1234!")

        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void signUpPositiveTest(String name, String surname, String emailOrPhone, String password)throws InterruptedException {

        FirstNameElement = driver.findElement(By.xpath("//input[@name='firstname']"));
        FirstNameElement.sendKeys(name);
        String nameValue = null;
        for (int i = 0; i < name.length(); i++) {

            char c = name.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                nameValue = "bad name";
            }
            else{
                nameValue = FirstNameElement.getAttribute("value");
            }
        }
        
        assertEquals(name, nameValue, "name don't match");


        lastNameElement = driver.findElement(By.xpath("//input[@name='lastname']"));
        lastNameElement.sendKeys(surname);
        String familyName = null;
        for (int i = 0; i < surname.length(); i++) {

            char c = surname.charAt(i);
            if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
                familyName = "wrong surname";
            }
            else{
                familyName = lastNameElement.getAttribute("value");
            }
        }
        
        assertEquals(surname, familyName, "surname don't match");


        mobileNumOrEmail = driver.findElement(By.xpath("//input[@name='reg_email__']"));
        mobileNumOrEmail.sendKeys(emailOrPhone);
        String phoneOrEmail = null;
        EmailValidator validator = EmailValidator.getInstance();


            for (int i = 0; i < emailOrPhone.length(); i++) {

                char c = emailOrPhone.charAt(i);
                if ((c <'1' && c > '9')&&!validator.isValid(emailOrPhone)) {
                    phoneOrEmail = "wrong input";
                }
                else{
                    phoneOrEmail = mobileNumOrEmail.getAttribute("value");
                }
            }
            
        assertEquals(emailOrPhone, phoneOrEmail, "wrong input");

        newPassword = driver.findElement(By.xpath("//input[@id='password_step_input']"));
        newPassword.sendKeys(password);
        String pass;

        if (((password.isEmpty())||(password.length()<6))&&!(password.contains("!@#$%&*()'+,-./:;<=>?[]^_`{|}"))){
            pass = "Wrong password";
        }
        else {
            pass = newPassword.getAttribute("value");
        }
        assertEquals(password, pass, "wrong input");

        Select month = new Select(driver.findElement(By.xpath("//select[@id='month']")));
        assertNotNull(month);
        month.selectByVisibleText("Sep");

        Select day = new Select(driver.findElement(By.xpath("//select[@id='day']")));
        assertNotNull(day);
        day.selectByVisibleText("25");

        Select year = new Select(driver.findElement(By.xpath("//select[@id='year']")));
        assertNotNull(year);
        year.selectByVisibleText("1975");


        WebElement custom = driver.findElement(By.xpath("//*[text()='Custom']"));
        assertNotNull(custom);
        custom.click();

        Select pronoun = new Select(driver.findElement(By.xpath("//select[@name='preferred_pronoun']")));
        assertNotNull(pronoun);
        pronoun.selectByVisibleText("She: \"Wish her a happy birthday!\"");

        SIGN_UP = driver.findElement(By.xpath("//button[@type='submit']"));
        SIGN_UP.click();

        Thread.sleep(10000);

    }

}




