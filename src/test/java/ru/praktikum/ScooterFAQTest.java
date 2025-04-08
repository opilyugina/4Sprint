package ru.praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ScooterFAQTest extends BaseTest {

    private final int questionIndex;
    private final String expectedAnswer;

    public ScooterFAQTest(int questionIndex, String expectedAnswer) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getFAQData() {
        return new Object[][] {
                {0, ANSWER_COST},
                {1, ANSWER_SCOOTERS},
                {2, ANSWER_RENTAL_TIME},
                {3, ANSWER_TODAY_ORDER},
                {4, ANSWER_EXTEND_RETURN},
                {5, ANSWER_CHARGING},
                {6, ANSWER_CANCEL_ORDER},
                {7, ANSWER_DELIVERY_AREA}
        };
    }

    @Test
    public void checkFAQItem() {
        String actualAnswer = faqPage.getFaqAnswerText(questionIndex);
        assertEquals("Текст ответа не совпадает", expectedAnswer, actualAnswer);
    }

    // константы
    public static final String ANSWER_COST = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String ANSWER_SCOOTERS = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с " +
            "друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String ANSWER_RENTAL_TIME = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение " +
            "дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат" +
            " 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String ANSWER_TODAY_ORDER = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String ANSWER_EXTEND_RETURN = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку" +
            " по красивому номеру 1010.";
    public static final String ANSWER_CHARGING = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток " +
            "— даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String ANSWER_CANCEL_ORDER = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже " +
            "не попросим. Все же свои.";
    public static final String ANSWER_DELIVERY_AREA = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
}