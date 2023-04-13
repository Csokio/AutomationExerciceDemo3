import com.google.gson.JsonParser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.BasePage;
import org.example.CartPage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.asserts.SoftAssert;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public void testAutomationPage() throws InterruptedException, IOException {
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
        cartPage.saveCartItem();
        cartPage.saveCartItemTwo();
        /*cartPage.clickCheckoutButton();
        cartPage.sendText("text");*/

        softAssert.assertAll();
    }


    @Test
    public void testCategories() throws IOException, ParseException {
        BasePage basePage = new BasePage(driver);
        basePage.navigate();
        Map<String, List<String>> actualResult = basePage.getCategoriesMap();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("expected.json"));
        JSONArray categories = (JSONArray) obj;

        Map<String, List<String>> expectedResult = new HashMap<>();
        for(Object category: categories){
            String key = (String) ((JSONObject) category).get("name");
            JSONArray subCategoryArray = (JSONArray) ((JSONObject)category).get("categories");
            List<String> values = new ArrayList<>();
            for(Object subcategory: subCategoryArray){
                String subcat = (String) subcategory;
                values.add(subcat);
            }
            expectedResult.put(key, values);
        }

        Assertions.assertEquals(expectedResult, actualResult);

    }

}
