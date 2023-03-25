package tests;

import page_object.HomePageScooter;
import page_object.OrderPageScooter;
import page_object.RentPageScooter;
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

import static org.junit.Assert.assertTrue;
import static page_object.constants.Stands.SCOOTER_SERVICES;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStationFromOrder;
    private final String phoneNumber;
    private final String rentalDate;
    private final String comment;

    public CreateOrderTest(String name, String surname, String address, String metroStationFromOrder, String phoneNumber, String rentalDate, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStationFromOrder = metroStationFromOrder;
        this.phoneNumber = phoneNumber;
        this.rentalDate = rentalDate;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Тестовые данные для оформления заказа")
    public static Object[][] newOrderParams() {
        return new Object[][]{
                {"Имя", "Фамилия", "Адрес", "Сокольники", "79800989090", "10.10.2023", "мне не нужен самокат"},
                {"Василий", "Васильевич", "Красная улица 1 дом 55", "Лубянка", "+79808900909", "01012023", "мне нужен самокат"},
        };
    }

    @Before
    public void websiteLaunch() {
        String driverName = System.getenv("driverName");
        if (driverName.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
        } else if (driverName.equals("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        }
    }

    @Test
    public void createOrderWithHeaderOrderButtonTest() {

        HomePageScooter homePage = new HomePageScooter(driver);

        driver.get(SCOOTER_SERVICES);
        homePage.waitForLoadOrderButton();
        homePage.openOrderPageWithHeaderOrderButton();

        OrderPageScooter orderPage = new OrderPageScooter(driver);
        orderPage.makeOrder(name, surname, address, metroStationFromOrder, phoneNumber);
        orderPage.clickNextButton();
        RentPageScooter rentPage = new RentPageScooter(driver);
        rentPage.makingRentPageScooter(rentalDate, comment);
        assertTrue(rentPage.getSuccessAlert().contains("Заказ оформлен"));
    }

    @Test
    public void createOrderMiddleOrderButtonTest() {

        HomePageScooter homePage = new HomePageScooter(driver);

        driver.get(SCOOTER_SERVICES);
        homePage.waitForLoadOrderButton();
        homePage.openOrderPageWithMiddleOrderButton();

        OrderPageScooter orderPage = new OrderPageScooter(driver);
        orderPage.makeOrder(name, surname, address, metroStationFromOrder, phoneNumber);
        orderPage.clickNextButton();
        RentPageScooter rentPage = new RentPageScooter(driver);
        rentPage.makingRentPageScooter(rentalDate, comment);
        assertTrue(rentPage.getSuccessAlert().contains("Заказ оформлен"));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
