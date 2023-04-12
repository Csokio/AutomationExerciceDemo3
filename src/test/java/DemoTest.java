import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.BasePage;
import org.example.CartPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;


import java.time.Duration;

public class DemoTest {

    WebDriver driver;

    @BeforeEach
    public void init()
    {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        //options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testAutomationPage() throws InterruptedException {
        BasePage basePage = new BasePage(driver);
        SoftAssert softAssert = new SoftAssert();
        basePage.navigate();
        softAssert.assertTrue(basePage.isLogoDisplayed(), "logo is not displayed");

        basePage.clickLoginButton();
        String email = "maya1@gmail.com";
        basePage.fillEmail(email);
        String password = "maya1";
        basePage.fillPassword(password);
        basePage.clickLoginToAccount();

        softAssert.assertTrue(basePage.getUserName("maya"), "username is not visible");

        basePage.clickButtonAddToCart();

        CartPage cartPage = basePage.clickViewCart();
        cartPage.saveCartItem("itemData");
        /*cartPage.clickCheckoutButton();
        cartPage.sendText("text");*/

        softAssert.assertAll();
    }

}
