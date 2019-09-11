package RegisterAuto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.parseDouble;

public class Maven5Test {

    protected static WebDriver driver;

    public static String randomDate() {

        DateFormat format = new SimpleDateFormat("ddMMyyHHmmss");

        return format.format(new Date());

    }

    @BeforeMethod
    public void OpenBrowser() {

        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDriver\\chromedriver.exe");

        driver = new ChromeDriver();

        //maximise the browser window screen
        driver.manage().window().fullscreen();

        //set implicity wait for driver object
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //open the website
        driver.get("https://demo.nopcommerce.com/");

    }
    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test

    public void userShouldBeAbleToRegisterSuccessfully() {

        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        // enter first name
        driver.findElement(By.id("FirstName")).sendKeys("Pujan");

        //enter last name
        driver.findElement(By.xpath("//input[@name=\"LastName\"]")).sendKeys("Acharya");

        //Enter Email
        driver.findElement(By.id("Email")).sendKeys("acharyachiragben" + randomDate() + "@gmail.com");

        //enter password
        driver.findElement(By.id("Password")).sendKeys("mnop456");

        //enter confirm password
        driver.findElement(By.xpath("//input[@data-val-equalto-other='*.Password']")).sendKeys("mnop456");

        //click on register
        driver.findElement(By.xpath("//input[@value=\"Register\"]")).click();


        String actualResult = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();

        String expectedResult = ("Your registration completed");

        Assert.assertEquals(actualResult, expectedResult);

    }



    @Test

    public void userShouldBeAbleToRegisterAndEmailaFriendToReferaProductAppleMacBook() {

        //click on register button
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        // enter first name
        driver.findElement(By.id("FirstName")).sendKeys("Pujan");

        //enter last name
        driver.findElement(By.xpath("//input[@name=\"LastName\"]")).sendKeys("Acharya");

        //Enter Email
        driver.findElement(By.id("Email")).sendKeys("acharyachiragben" + randomDate() + "@gmail.com");

        //enter password
        driver.findElement(By.id("Password")).sendKeys("mnop456");

        //enter confirm password
        driver.findElement(By.xpath("//input[@data-val-equalto-other='*.Password']")).sendKeys("mnop456");

        //click on register
        driver.findElement(By.xpath("//input[@value=\"Register\"]")).click();

        // click on continue
        driver.findElement(By.xpath("//input[@class ='button-1 register-continue-button']")).click();

        //click on nop commerce link
        driver.findElement(By.xpath("//img[@alt=\"nopCommerce demo store\"]")).click();

        //click on apple macbook
        driver.findElement(By.xpath("//img[@alt='Picture of Apple MacBook Pro 13-inch']")).click();

        //click on email a friend
        driver.findElement(By.xpath("//input[@value=\"Email a friend\"]")).click();

        //enter friend email
        driver.findElement(By.id("FriendEmail")).sendKeys("poojan@yahoo.com");

        //enter my email
        //driver.findElement(By.name("YourEmailAddress")).sendKeys("acharyachiragben" + randomDate() + "@gmail.com");
        //write massage
        driver.findElement(By.id("PersonalMessage")).sendKeys("Hi look this laptop");

        //click button
        driver.findElement(By.name("send-email")).click();

        // match the actual and expected results
       String actualResult = driver.findElement(By.xpath("//a[@href = \"/apple-macbook-pro-13-inch\"]")).getText();
       String expectedResult =("Apple MacBook Pro 13-inch");
       Assert.assertEquals(actualResult,expectedResult);

    }

    @Test

    public void userShouldBeAbleToNavigateCameraAndPhotoPage(){

        //click on electronics
        driver.findElement(By.className("title")).click();

        //click on camera&photo
        driver.findElement(By.className("sub-category-item")).click();

        // match the result
        String actualS1 = driver.findElement(By.xpath("//h1")).getText();
        String expectedS1 = "Camera & photo";

        Assert.assertEquals(actualS1, expectedS1);

    }


    @Test

    public void userShouldBeAbleToFilterJewelryByPrice() throws InterruptedException {

        //click on jewelry
        driver.findElement(By.partialLinkText("Jewelry")).click();

        //click on price range of 700-3000
        driver.findElement(By.xpath("//a[contains(@href,'700-3000')]")).click();

        //match the result
        //driver.findElement(By.xpath("//img[@alt=\"Picture of Vintage Style Engagement Ring\"]")).getText();

        String actualM2 = driver.findElement(By.xpath("//span[@class=\"price actual-price\"]")).getText();
        String expectedM2 = "$700.00 - $3,000.00";
        String s[] = expectedM2.split("-");
        String s4 = s[1].replaceAll(" ", "").replace("$", "").replace("," , "");
        String s3 = s[0].replace("$", "");
        //Convert String to Double
        double p1 = parseDouble(s3);
        double p2 = parseDouble(s4);
        //Converting Actual value to double
        double AM2 = parseDouble(actualM2.replace("$", "").replace(",", ""));

        Assert.assertTrue(AM2>p1 && AM2<p2);}


        @Test
        public void userShouldBeAbleToAddTwoProductTwoBasket() throws InterruptedException {

            //Go to book category
            driver.findElement(By.linkText("Books")).click();

            //Add first book to cart
            driver.findElement(By.xpath("//input[@type='button' and contains(@onclick, '38/1/1')]")).click();

            Thread.sleep(2000);

            //Add second book to cart
            driver.findElement(By.xpath("//input[@type='button' and contains(@onclick, '37/1/1')]")).click();

            Thread.sleep(7000);

            //Click on shopping cart
            driver.findElement(By.className("ico-cart")).click();

            String actualu1 = driver.findElement(By.xpath("//span[@class='sku-number' and contains(text(), 'FIRST_PRP')]")).getText();
            String expectedu1 = "FIRST_PRP";
            org.testng.Assert.assertEquals(actualu1, expectedu1);

            String actualb2 = driver.findElement(By.xpath("//span[@class='sku-number' and contains(text(), 'FR_451_RB')]")).getText();
            String expectedb2 = "FR_451_RB";
            Assert.assertEquals(actualb2, expectedb2);

        }




















    }





























