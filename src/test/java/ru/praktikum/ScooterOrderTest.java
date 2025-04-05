package ru.praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Parameterized.class)
public class ScooterOrderTest extends BaseTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String phone;
    private final String comment;
    private final String color;

    public ScooterOrderTest(String name, String surname, String address,
                            String phone, String comment, String color) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {NAME_1, SURNAME_1, ADDRESS_1, PHONE_1, COMMENT_1, COLOR_BLACK},
                {NAME_2, SURNAME_2, ADDRESS_2, PHONE_2, COMMENT_2, COLOR_GREY}
        };
    }

    @Test
    public void testOrderScooter() {
        // тест для обеих кнопок "Заказать"
        placeOrder(BUTTON_ORDER_UP);
        placeOrder(BUTTON_ORDER_DOWN);
    }

    private void placeOrder(By buttonLocator) {
        click(buttonLocator);
        fillOrderForm();
        checkSuccessOrder();
    }

    private void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    private void fillOrderForm() {
        fillField(INPUT_NAME, name);
        fillField(INPUT_SURNAME, surname);
        fillField(INPUT_ADDRESS, address);
        fillField(INPUT_PHONE, phone);
        selectMetroStation();
        click(NEXT_BUTTON);
        selectDate();
        selectPeriod(PERIOD_24H);
        selectColor();
        fillField(INPUT_COMMENT, comment);
        submitOrder();
    }

    private void fillField(By locator, String value) {
        driver.findElement(locator).sendKeys(value);
    }

    private void selectMetroStation() {
        click(METRO_STATION_INPUT);
        waitForElementToBeClickable(METRO_STATION_OPTION).click();
    }

    private void selectDate() {
        click(DATE_INPUT);
        waitForElementToBeClickable(DATE_TODAY).click();
    }

    private void selectPeriod(String period) {
        click(By.className(DROP_DOWN_ARROW));
        waitForElementToBeClickable(By.xpath(DROP_DOWN_OPTION + period + "']")).click();
    }

    private void selectColor() {
        click(By.id(color));
    }

    private void submitOrder() {
        click(SUBMIT_ORDER_BUTTON);
        waitForElementToBeClickable(CONFIRM_BUTTON).click();
    }

    private void checkSuccessOrder() {
        waitForElementToBeVisible(SUCCESS_MESSAGE);
    }

    // ожидания
    private WebElement waitForElementToBeClickable(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    private WebElement waitForElementToBeVisible(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // константы для данных пользователя
    public static final String NAME_1 = "Иван";
    public static final String SURNAME_1 = "Иванов";
    public static final String ADDRESS_1 = "ул. Ленина, 1";
    public static final String PHONE_1 = "89998887766";
    public static final String COMMENT_1 = "1 тест";
    public static final String COLOR_BLACK = "black";

    public static final String NAME_2 = "Петр";
    public static final String SURNAME_2 = "Петров";
    public static final String ADDRESS_2 = "ул. Пушкина, 10";
    public static final String PHONE_2 = "87776665544";
    public static final String COMMENT_2 = "2 тест";
    public static final String COLOR_GREY = "grey";

    // константы для локаторов
    public static final By BUTTON_ORDER_UP = By.className("Button_Button__ra12g");
    public static final By BUTTON_ORDER_DOWN = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]");

    public static final By INPUT_NAME = By.xpath(".//input[@placeholder='* Имя']");
    public static final By INPUT_SURNAME = By.xpath(".//input[@placeholder='* Фамилия']");
    public static final By INPUT_ADDRESS = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    public static final By INPUT_PHONE = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    public static final By INPUT_COMMENT = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    public static final By METRO_STATION_INPUT = By.xpath(".//input[@placeholder='* Станция метро']");
    public static final By METRO_STATION_OPTION = By.xpath(".//div[@class='select-search__select']//div[1]");

    public static final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");

    public static final By DATE_INPUT = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    public static final By DATE_TODAY = By.xpath(".//div[contains(@class, 'react-datepicker__day--today')]");

    public static final String DROP_DOWN_ARROW = "Dropdown-arrow";
    public static final String DROP_DOWN_OPTION = ".//div[@class='Dropdown-option' and text()='";

    public static final By SUBMIT_ORDER_BUTTON = By.xpath(".//button[text()='Заказать']");
    public static final By CONFIRM_BUTTON = By.xpath(".//button[text()='Да']");
    public static final By SUCCESS_MESSAGE = By.xpath(".//div[contains(text(), 'Заказ оформлен')]");

    public static final String PERIOD_24H = "сутки";
}