package ru.praktikum.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FAQPage extends HomePage {
    public FAQPage(WebDriver driver) {
        super(driver);
    }

    public void clickFaqQuestion(int index) {
        By questionLocator = By.id("accordion__heading-" + index);
        clickElement(questionLocator);
    }

    public String getFaqAnswerText(int index) {
        By answerLocator = By.id("accordion__panel-" + index);
        waitForElementToBeVisible(answerLocator);
        return driver.findElement(answerLocator).getText();
    }
}