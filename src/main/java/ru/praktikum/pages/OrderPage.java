package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;

    // локаторы для 1 стр. заказа
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By firstMetroStation = By.xpath(".//div[@class='select-search__select']//div[1]");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // локаторы для 2 стр. заказа
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By todayDate = By.xpath(".//div[contains(@class, 'react-datepicker__day--today')]");
    private final By rentalPeriodDropdown = By.className("Dropdown-arrow");
    private final By oneDayPeriod = By.xpath(".//div[@class='Dropdown-option' and text()='сутки']");
    private final By blackColorCheckbox = By.id("black");
    private final By greyColorCheckbox = By.id("grey");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[text()='Заказать']");
    private final By confirmButton = By.xpath(".//button[text()='Да']");
    private final By successMessage = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // базовые методы
    private WebElement waitForElementToBeVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void waitForElementToBeClickable(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void clickElement(By locator) {
        waitForElementToBeClickable(locator);
        driver.findElement(locator).click();
    }

    private void sendKeysToElement(By locator, String text) {
        WebElement element = waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    // методы для работы с полями формы
    public void enterName(String name) {
        sendKeysToElement(nameField, name);
    }

    public void enterSurname(String surname) {
        sendKeysToElement(surnameField, surname);
    }

    public void enterAddress(String address) {
        sendKeysToElement(addressField, address);
    }

    public void enterPhone(String phone) {
        sendKeysToElement(phoneField, phone);
    }

    public void selectMetroStation() {
        clickElement(metroField);
        clickElement(firstMetroStation);
    }

    public void clickNextButton() {
        clickElement(nextButton);
    }

    public void selectDeliveryDate() {
        clickElement(dateField);
        clickElement(todayDate);
    }

    public void selectRentalPeriod() {
        clickElement(rentalPeriodDropdown);
        clickElement(oneDayPeriod);
    }

    public void selectScooterColor(String color) {
        if ("black".equals(color)) {
            clickElement(blackColorCheckbox);
        } else {
            clickElement(greyColorCheckbox);
        }
    }

    public void enterComment(String comment) {
        sendKeysToElement(commentField, comment);
    }

    public void clickOrderButton() {
        clickElement(orderButton);
    }

    public void confirmOrder() {
        clickElement(confirmButton);
    }

    // оформление заказа
    public void fillFirstOrderPage(String name, String surname, String address, String phone) {
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        enterPhone(phone);
        selectMetroStation();
        clickNextButton();
    }

    public void fillSecondOrderPage(String color, String comment) {
        selectDeliveryDate();
        selectRentalPeriod();
        selectScooterColor(color);
        enterComment(comment);
        clickOrderButton();
        confirmOrder();
    }

    public boolean isOrderSuccessful() {
        WebElement message = waitForElementToBeVisible(successMessage);
        return message.isDisplayed();
    }
}