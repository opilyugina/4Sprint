package ru.praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.pages.OrderPage;

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
                {TEST_NAME_IVAN, TEST_SURNAME_IVANOV, TEST_ADDRESS_LENINA,
                        TEST_PHONE_8999, TEST_COMMENT_SHORT, COLOR_BLACK},
                {TEST_NAME_PETR, TEST_SURNAME_PETROV, TEST_ADDRESS_PUSHKINA,
                        TEST_PHONE_8777, TEST_COMMENT_LONG, COLOR_GREY}
        };
    }

    @Test
    public void testOrderScooterTopButton() {
        OrderPage orderPage = new OrderPage(driver);
        homePage.clickOrderTopButton();
        orderPage.fillOrderForm(name, surname, address, phone, color, comment);
        orderPage.checkOrderSuccess();
    }

    @Test
    public void testOrderScooterBottomButton() {
        OrderPage orderPage = new OrderPage(driver);
        homePage.clickOrderBottomButton();
        orderPage.fillOrderForm(name, surname, address, phone, color, comment);
        orderPage.checkOrderSuccess();
    }

    // константы для тестовых данных
    private static final String TEST_NAME_IVAN = "Иван";
    private static final String TEST_SURNAME_IVANOV = "Иванов";
    private static final String TEST_ADDRESS_LENINA = "ул. Ленина, 1";
    private static final String TEST_PHONE_8999 = "89998887766";
    private static final String TEST_COMMENT_SHORT = "1 тест";
    private static final String COLOR_BLACK = "black";

    private static final String TEST_NAME_PETR = "Петр";
    private static final String TEST_SURNAME_PETROV = "Петров";
    private static final String TEST_ADDRESS_PUSHKINA = "ул. Пушкина, 10";
    private static final String TEST_PHONE_8777 = "87776665544";
    private static final String TEST_COMMENT_LONG = "тесттесттесттесттесттест";
    private static final String COLOR_GREY = "grey";
}