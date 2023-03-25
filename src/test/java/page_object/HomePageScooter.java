package page_object;

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

    private final By questionsList = By.className("accordion");

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
        WebElement element = driver.findElement(questionsList);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickQuestion(String questionNumber) {
        String questionsList = String.format(".//div[@class='accordion']//*[text()='" + questionNumber + "']");
        driver.findElement(By.xpath(questionsList)).click();
    }

    public String checkAnswerText(String expectedText) {
        String answersList = ".//div[@class='accordion']//*[text()='" + expectedText + "']";

        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answersList)));

        return driver.findElement(By.xpath(answersList)).getText();
    }
}
