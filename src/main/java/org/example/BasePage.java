package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage extends Pages{

    public BasePage(WebDriver driver)
    {
        super(driver);
    }

    private final String url = "https://automationexercise.com";

    public void navigate()
    {
        driver.navigate().to(url);
    }

    private final By LOGO = By.xpath("//img[@alt=\"Website for automation practice\"]");
    public boolean isLogoDisplayed()
    {
        return driver.findElement(LOGO).isDisplayed();
    }
    private final By BUTTON_LOGIN = By.xpath("//a[@href=\"/login\"]");
    public void clickLoginButton()
    {
        driver.findElement(BUTTON_LOGIN).click();
    }
    private final By FIELD_EMAIL = By.xpath("(//input[@name=\"email\"])[1]");
    public void fillEmail(String email)
    {
        driver.findElement(FIELD_EMAIL).sendKeys(email);
    }
    private final By FIELD_PASSWORD = By.xpath("//input[@name=\"password\"]");
    public void fillPassword(String password)
    {
        driver.findElement(FIELD_PASSWORD).sendKeys(password);
    }
    private final By BUTTON_LOGIN_TO_ACCOUNT = By.xpath("//button[@data-qa=\"login-button\"]");
    public void clickLoginToAccount()
    {
        driver.findElement(BUTTON_LOGIN_TO_ACCOUNT).click();
    }
    private final By TEXT_USERNAME = By.xpath("//i[@class=\"fa fa-user\"]/following-sibling::b");
    public boolean getUserName(String name)
    {
        String result = driver.findElement(TEXT_USERNAME).getText();
        return result.equals(name);
    }
    private final By BUTTON_ADD_TO_CART  = By.xpath("(//div[@class=\"features_items\"]/div[@class=\"col-sm-4\"][1]//a[@class=\"btn btn-default add-to-cart\"])[1]");
    private final By OVERLAY = By.xpath("(//div[@class=\"features_items\"]/div[@class=\"col-sm-4\"][1]//a[@class=\"btn btn-default add-to-cart\"])[2]");
    public void clickButtonAddToCart() throws InterruptedException {
        /*JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView", driver.findElement(BUTTON_ADD_TO_CART);*/

        scrollToElement(BUTTON_ADD_TO_CART);

        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(BUTTON_ADD_TO_CART)).perform();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(OVERLAY))).click();
    }

    private final By BUTTON_VIEW_CART = By.xpath("//div[@class=\"modal-dialog modal-confirm\"]//a[@href=\"/view_cart\"]");
    public CartPage clickViewCart()
    {
        driver.findElement(BUTTON_VIEW_CART).click();
        return new CartPage(driver);
    }
}
