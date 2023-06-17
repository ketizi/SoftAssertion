import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ForExample {
    public WebDriver driver ; // კლასის დონეზე იქნება გლობალური ცვლადი (public უბრალოს ყველგან რომ დაინახოს მაგიტომ)
    SoftAssert softAssertion; // ეს იმიტომ ,რომ შემდეგ გამოვიყენოთ after ტესტში
    @BeforeTest
    public void beforeTestConfig(){ // ჩვენი გარემო დასეთაფებული გვაქვს, ესეიგი ყოველი ტესტის გაშვების დროს ვებდრაივერი გაიხსნება და გადავა გუგლის საიტზე
        driver = new ChromeDriver();
        driver.get("https://www.google.com/");
    }
    @Test
    public void testSoftAsser() throws InterruptedException {
        By googleSearch = By.name("q");
        driver.findElement(googleSearch).sendKeys("linkedin");
        driver.findElement(googleSearch).sendKeys(Keys.ENTER);
 // ზოგადად assertion რაღაც მოსალოდნელ შედეგს ადარებს რეალურ შედეგს
        softAssertion = new SoftAssert();

        String expectedResult = "Lankdin"; // მოსალოდნელ შედეგს ვინახავთ სტრინგ ტიპის ცვლადში
        String actualResult = driver.findElement(googleSearch).getText();
        softAssertion.assertEquals(actualResult,expectedResult);

        Thread.sleep(6000);


    }
    @AfterTest
    public void afterTestConfig(){
        softAssertion.assertAll();
        driver.quit();

    }


}
// საბოლოოდ softassertion არის ტიპი, რომელიც მკაცრად არ ეუბნება ბრძანებას შესამოწმებლად, უბრალოდ verify ტიპია და თუ არ დაემთხვა დაიკიდებს და გააფასდებს.