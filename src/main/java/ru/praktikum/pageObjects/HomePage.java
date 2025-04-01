package ru.praktikum.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    protected WebDriver driver;

    // главная стр
    private final By faqSection = By.className("Home_FAQ__3uVm4");
    private final By orderTopButton = By.className("Button_Button__ra12g");
    private final By orderBottomButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]");
    private final By cookieButton = By.id("rcc-confirm-button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // базовые методы работы с элементами

    public void waitForElementToBeClickable(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement waitForElementToBeVisible(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return null;
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickElement(By locator) {
        waitForElementToBeClickable(locator);
        driver.findElement(locator).click();
    }

    public void sendKeysToElement(By locator, String text) {
        waitForElementToBeVisible(locator);
        driver.findElement(locator).sendKeys(text);
    }

    // методы главной страницы

    public void clickCookieButton() {
        clickElement(cookieButton);
    }

    public WebElement getFaqSection() {
        return driver.findElement(faqSection);
    }

    public void clickOrderTopButton() {
        clickElement(orderTopButton);
    }

    public void clickOrderBottomButton() {
        clickElement(orderBottomButton);
    }

    public void scrollToFaqSection() {
        scrollToElement(getFaqSection());
    }
}