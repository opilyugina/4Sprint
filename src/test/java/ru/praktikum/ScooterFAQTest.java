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
                {0, ANSWER_0},
                {1, ANSWER_1},
                {2, ANSWER_2},
                {3, ANSWER_3},
                {4, ANSWER_4},
                {5, ANSWER_5},
                {6, ANSWER_6},
                {7, ANSWER_7}
        };
    }

    @Test
    public void checkFAQItem() {
        String actualAnswer = faqPage.getFaqAnswerText(questionIndex);
        assertEquals("Текст ответа не совпадает", expectedAnswer, actualAnswer);
    }

    // константы
    public static final String ANSWER_0 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String ANSWER_1 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с " +
            "друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String ANSWER_2 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение " +
            "дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат" +
            " 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String ANSWER_3 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String ANSWER_4 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку" +
            " по красивому номеру 1010.";
    public static final String ANSWER_5 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток " +
            "— даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String ANSWER_6 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже " +
            "не попросим. Все же свои.";
    public static final String ANSWER_7 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
}