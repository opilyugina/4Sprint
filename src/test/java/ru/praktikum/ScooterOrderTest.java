package ru.praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@RunWith(Parameterized.class)
public class ScooterOrderTest {
    private WebDriver driver;
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
                {"Иван", "Иванов", "ул. Ленина, 1", "89998887766", "1 тест", "black"},
                {"Петр", "Петров", "ул. Пушкина, 10", "87776665544", "2 тест", "grey"}
        };
    }

    @Before
    public void setUp() {
        // драйвер
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        // сайт + куки
        driver.get("https://qa-scooter.praktikum-services.ru/");
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement cookieButton = shortWait.until(ExpectedConditions.presenceOfElementLocated(
                    By.id("rcc-confirm-button")));
            cookieButton.click();
        } catch (TimeoutException ignored) {
        }
    }

    @Test
    public void testOrderScooterTopButton() {
        // верхняя кнопка "Заказать"
        clickOrderButton(By.className("Button_Button__ra12g"));
        fillOrderForm();
        checkSuccessOrder();
    }

    @Test
    public void testOrderScooterBottomButton() {
        // нижняя кнопка "Заказать"
        clickOrderButton(By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]"));
        fillOrderForm();
        checkSuccessOrder();
    }

    private void clickOrderButton(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    private void fillOrderForm() {
        // 1 страница формы, текстовые плейсхолдеры
        driver.findElement(By.xpath(".//input[@placeholder='* Имя']")).sendKeys(name);
        driver.findElement(By.xpath(".//input[@placeholder='* Фамилия']")).sendKeys(surname);
        driver.findElement(By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']")).sendKeys(address);
        driver.findElement(By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']")).sendKeys(phone);
        // метро
        driver.findElement(By.xpath(".//input[@placeholder='* Станция метро']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath(".//div[@class='select-search__select']//div[1]"))).click();
        // "далее"
        driver.findElement(By.xpath(".//button[text()='Далее']")).click();

        // 2 страница формы
        // дата
        driver.findElement(By.xpath(".//input[@placeholder='* Когда привезти самокат']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath(".//div[contains(@class, 'react-datepicker__day--today')]"))).click();
        // срок аренды
        driver.findElement(By.className("Dropdown-arrow")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath(".//div[@class='Dropdown-option' and text()='сутки']"))).click();
        // цвет
        driver.findElement(By.id(color)).click();
        // комментарий
        driver.findElement(By.xpath(".//input[@placeholder='Комментарий для курьера']")).sendKeys(comment);
        // кнопка "Заказать"
        driver.findElement(By.xpath(".//button[text()='Заказать']")).click();
        // подтверждение заказа
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath(".//button[text()='Да']"))).click();
    }

    private void checkSuccessOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath(".//div[contains(text(), 'Заказ оформлен')]")));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}