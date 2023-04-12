package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Pages {


    WebDriver driver;
    public Pages(WebDriver driver){
        this.driver = driver;
    }

    public void scrollToElement(By xpath) throws InterruptedException {
        WebElement element = driver.findElement(xpath);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }
}
