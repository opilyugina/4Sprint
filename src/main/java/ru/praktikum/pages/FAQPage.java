package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FAQPage extends HomePage {

    // локаторы для FAQ
    private final By faqSection = By.className("Home_FAQ__3uVm4");
    private final By faqAnswers = By.cssSelector(".accordion__panel");

    public FAQPage(WebDriver driver) {
        super(driver);
    }

    // прокрутка до FAQ
    public void scrollToFaqSection() {
        WebElement faq = waitForElementToBeVisible(faqSection);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faq);
    }

    // клик по вопросу FAQ
    public void clickFaqQuestion(int index) {
        By questionLocator = By.id("accordion__heading-" + index);
        waitForElementToBeVisible(questionLocator).click();
    }

    // получение текста ответа на вопрос FAQ
    public String getFaqAnswerText(int index) {
        scrollToFaqSection();
        clickFaqQuestion(index);
        By answerLocator = By.id("accordion__panel-" + index);
        WebElement answerElement = waitForElementToBeVisible(answerLocator);
        return answerElement.getText();
    }
}