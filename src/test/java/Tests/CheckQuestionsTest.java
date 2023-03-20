package Tests;

import PageObject.HomePageScooter;
import PageObject.OrderPageScooter;
import PageObject.RentPageScooter;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CheckQuestionsTest {

    private WebDriver driver;
    private final String driverName;

    public CheckQuestionsTest(String driverName) {
        this.driverName = driverName;
    }

    @Parameterized.Parameters(name = "Тестовые данные для оформления заказа")
    public static Object[][] newOrderParams() {
        return new Object[][]{
                {"Chrome"},
                {"Firefox"}
        };

    }

    private final String[] expectedAnswersList = new String[]{
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    };

    @Before
    public void websiteLaunch() {
        if (driverName == "Chrome") {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        } else if (driverName == "Firefox") {
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
//        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            driver = new FirefoxDriver(options);
        }
    }

    @Test
    public void CheckQuestionsTest() {
        HomePageScooter homePage = new HomePageScooter(driver);

        driver.get("https://qa-scooter.praktikum-services.ru/");
        homePage.clickCookieButton();
        homePage.scrollPageToEndList();
        for (int i = 0; i < 8; i++) {
            homePage.clickQuestion(i);
            homePage.checkAnswerText(expectedAnswersList[i], i);
        }
    }

    @After
    public void teardown() {
        this.driver.quit();
    }
}
