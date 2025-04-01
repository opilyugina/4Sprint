package ru.praktikum;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ScooterFAQTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        /// драйвер
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        /// сайт+прокрутка
        driver.get("https://qa-scooter.praktikum-services.ru/");
        WebElement faqSection = driver.findElement(By.className("Home_FAQ__3uVm4"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faqSection);
    }

    private void checkFAQItem(int index, String expectedAnswer) {
        /// ожидание+клик
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("accordion__heading-" + index)));
        question.click();

        /// проверка
        WebElement answer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("accordion__panel-" + index)));
        assertEquals("Текст ответа не совпадает", expectedAnswer, answer.getText());
    }

    @Test
    public void checkFirstFAQItem() {
        checkFAQItem(0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.");
    }

    @Test
    public void checkSecondFAQItem() {
        checkFAQItem(1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься" +
                " с друзьями, можете просто сделать несколько заказов — один за другим.");
    }

    @Test
    public void checkThirdFAQItem() {
        checkFAQItem(2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая" +
                " в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. " +
                "Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.");
    }

    @Test
    public void checkFourthFAQItem() {
        checkFAQItem(3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.");
    }

    @Test
    public void checkFifthFAQItem() {
        checkFAQItem(4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в " +
                "поддержку по красивому номеру 1010.");
    }

    @Test
    public void checkSixthFAQItem() {
        checkFAQItem(5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь" +
                " суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.");
    }

    @Test
    public void checkSeventhFAQItem() {
        checkFAQItem(6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки" +
                " тоже не попросим. Все же свои.");
    }

    @Test
    public void checkEighthFAQItem() {
        checkFAQItem(7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.");
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}