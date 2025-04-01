package ru.praktikum.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage extends HomePage {
    // локаторы для первой страницы заказа
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By firstMetroStation = By.xpath(".//div[@class='select-search__select']//div[1]");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // локаторы для второй страницы заказа
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
        super(driver);
    }

    public void fillFirstPage(String name, String surname, String address, String phone) {
        sendKeysToElement(nameField, name);
        sendKeysToElement(surnameField, surname);
        sendKeysToElement(addressField, address);
        sendKeysToElement(phoneField, phone);

        clickElement(metroField);
        clickElement(firstMetroStation);
        clickElement(nextButton);
    }

    public void fillSecondPage(String color, String comment) {
        clickElement(dateField);
        clickElement(todayDate);

        clickElement(rentalPeriodDropdown);
        clickElement(oneDayPeriod);

        if (color.equals("black")) {
            clickElement(blackColorCheckbox);
        } else {
            clickElement(greyColorCheckbox);
        }

        sendKeysToElement(commentField, comment);
        clickElement(orderButton);
        clickElement(confirmButton);
    }

    public boolean isOrderSuccess() {
        WebElement message = waitForElementToBeVisible(successMessage);
        return message.isDisplayed();
    }
}