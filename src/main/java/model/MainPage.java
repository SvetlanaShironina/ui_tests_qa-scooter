package model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Set;

import static model.Constants.*;

public class MainPage {
    private static WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor)driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public MainPage clickPopUpCookies(){
        driver.findElement(CLOSE_POPUP_WINDOW).click();
        return this;
    }
    public MainPage clickLogoSamokat(){
        driver.findElement(BUTTON_LOGO_SCOOTER).click();
        return this;
    }
    public MainPage clickLogoYandex(){
        driver.findElement(BUTTON_LOGO_YANDEX).click();
        String window1 = driver.getWindowHandle();
        js.executeScript("window.open");
        Set<String> currentWindows = driver.getWindowHandles();
        String window2 = null;

        for (String window : currentWindows){
            if(!window.equals(window1) ){
                window2 = window;
                break;
            }
        }

        driver.switchTo().window(window2);
        return this;
    }
    public MainPage scrollPageToEndOfList() {
        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL, Keys.END);
        return this;
    }
    public MainPage clickQuestionsAboutImportant(String questionsIndex) {
        WebElement questions = driver.findElement(By.id(String.format("accordion__heading-%s", questionsIndex)));
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(questions));
        questions.click();
        return this;
    }
    public MainPage checkAnswersAboutImportantThings(String answersIndex, String expectedText ) {
        clickQuestionsAboutImportant(answersIndex);
        WebElement answers = driver.findElement(By.id(String.format("accordion__panel-%s", answersIndex)));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfAllElements(answers));
        driver.findElement(By.id(String.format("accordion__panel-%s", answersIndex))).getText();
        return this;
    }
    public MainPage checkTextInOpenPanel (String questionsIndex, String answersIndex, String expectedText) {
        scrollPageToEndOfList();
        clickQuestionsAboutImportant(questionsIndex);
        checkAnswersAboutImportantThings(answersIndex, expectedText);
        return null;
    }
}