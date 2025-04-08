package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    protected WebDriver driver;
    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    // локаторы для основной страницы
    private final By orderTopButton = By.className("Button_Button__ra12g");
    private final By orderBottomButton = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]");
    private final By cookieButton = By.id("rcc-confirm-button");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // базовые методы
    public void open() {
        driver.get(URL);
    }

    protected WebElement waitForElementToBeVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // методы для основной страницы
    public void clickCookieButton() {
        waitForElementToBeVisible(cookieButton).click();
    }

    public void clickOrderTopButton() {
        waitForElementToBeVisible(orderTopButton).click();
    }

    public void clickOrderBottomButton() {
        waitForElementToBeVisible(orderBottomButton).click();
    }
}