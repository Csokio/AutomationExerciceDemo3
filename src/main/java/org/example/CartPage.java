package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;

public class CartPage extends Pages{

    public CartPage(WebDriver driver)
    {
        super(driver);
    }

    private final By BUTTON_CHECKOUT = By.xpath("//a[@class=\"btn btn-default check_out\"]");
    public void clickCheckoutButton()
    {
        driver.findElement(BUTTON_CHECKOUT).click();
    }
    private final By TEXTAREA = By.xpath("//div[@id=\"ordermsg\"]/textarea");
    private final By BUTTON_PLACE_ORDER = By.xpath("//a[@href=\"/payment\"]");
    public void sendText(String message) throws InterruptedException {
        scrollToElement(TEXTAREA);
        driver.findElement(TEXTAREA).sendKeys(message);
        driver.findElement(BUTTON_PLACE_ORDER).click();
    }

    private final By TABLE_ROW = By.xpath("//div[@id=\"cart_info\"]//tbody/tr[1]");
    public void saveCartItem(String fileName)
    {
        WebElement image = driver.findElement(TABLE_ROW).findElement(By.xpath("./td[1]//img"));

        String logoSrc = image.getAttribute("src");
        try {
            /*File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);*/

            URL imageURL = new URL("https://automationexercise.com/" + logoSrc);
            BufferedImage saveImage = ImageIO.read(imageURL);
            ImageIO.write(saveImage, "png", new File("itemData.txt"));

            //WebElement description = driver.findElement(TABLE_ROW).findElement(By.xpath("./td[2]//a"));

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
