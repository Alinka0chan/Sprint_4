package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class HomePageScooter {
    private static WebDriver driver;
    //Кнопка заказать в шапке страницы
    private By headerOrderButton = By.className("Button_Button__ra12g");
    //Кнопка заказать в середине страницы
    private By middleOrderButton = By.className("Button_Middle__1CSJM");
    //Кнопка о куки "да все привыкли"
    private final By cookieButton = By.id("rcc-confirm-button");
    //Список вопросов
    private static final String[] dropDownQuestionsArray = new String[]{
            "accordion__heading-0",
            "accordion__heading-1",
            "accordion__heading-2",
            "accordion__heading-3",
            "accordion__heading-4",
            "accordion__heading-5",
            "accordion__heading-6",
            "accordion__heading-7"};
    //Список панелей с текстом ответов
    private static final String[] dropDownAnswersArray = new String[]{
            "accordion__panel-0",
            "accordion__panel-1",
            "accordion__panel-2",
            "accordion__panel-3",
            "accordion__panel-4",
            "accordion__panel-5",
            "accordion__panel-6",
            "accordion__panel-7"};

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadOrderButton() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(headerOrderButton));
    }

    public void clickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void openOrderPageWithHeaderOrderButton() {
        driver.findElement(headerOrderButton).click();
    }

    public void openOrderPageWithMiddleOrderButton() {
        WebElement middleButton = driver.findElement(middleOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", middleButton);
        driver.findElement(middleOrderButton).click();
    }

    public void scrollPageToEndList() {
        WebElement lastQuestionArrow = driver.findElement(By.id(dropDownQuestionsArray[7]));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", lastQuestionArrow);
    }

    public void clickQuestion(int questionNumber) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(By.id(dropDownQuestionsArray[questionNumber])));
        driver.findElement(By.id(dropDownQuestionsArray[questionNumber])).click();
    }

    public static void checkAnswerText(String expectedText, int answerNumber) {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(dropDownAnswersArray[answerNumber])));
        String answerText = driver.findElement(By.id(dropDownAnswersArray[answerNumber])).getText();
        assertEquals(expectedText, answerText);
    }
}
