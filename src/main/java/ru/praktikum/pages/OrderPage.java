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

    public void fillOrderForm(String name, String surname, String address,
                              String phone, String color, String comment) {
        fillFirstOrderPage(name, surname, address, phone);
        fillSecondOrderPage(color, comment);
    }

    private void fillFirstOrderPage(String name, String surname, String address, String phone) {
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        enterPhone(phone);
        selectMetroStation();
        clickNextButton();
    }

    private void fillSecondOrderPage(String color, String comment) {
        selectDeliveryDate();
        selectRentalPeriod();
        selectScooterColor(color);
        enterComment(comment);
        clickOrderButton();
        confirmOrder();
    }

    public void checkOrderSuccess() {
        waitForElementToBeVisible(successMessage);
    }

    // методы для работы с полями формы
    private void enterName(String name) {
        sendKeysToElement(nameField, name);
    }

    private void enterSurname(String surname) {
        sendKeysToElement(surnameField, surname);
    }

    private void enterAddress(String address) {
        sendKeysToElement(addressField, address);
    }

    private void enterPhone(String phone) {
        sendKeysToElement(phoneField, phone);
    }

    private void selectMetroStation() {
        clickElement(metroField);
        clickElement(firstMetroStation);
    }

    private void clickNextButton() {
        clickElement(nextButton);
    }

    private void selectDeliveryDate() {
        clickElement(dateField);
        clickElement(todayDate);
    }

    private void selectRentalPeriod() {
        clickElement(rentalPeriodDropdown);
        clickElement(oneDayPeriod);
    }

    private void selectScooterColor(String color) {
        if ("black".equals(color)) {
            clickElement(blackColorCheckbox);
        } else {
            clickElement(greyColorCheckbox);
        }
    }

    private void enterComment(String comment) {
        sendKeysToElement(commentField, comment);
    }

    private void clickOrderButton() {
        clickElement(orderButton);
    }

    private void confirmOrder() {
        clickElement(confirmButton);
    }
}