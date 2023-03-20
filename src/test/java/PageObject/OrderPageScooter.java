package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPageScooter {
    private WebDriver driver;
    //Заголовок страницы
    private By header = By.className("Order_Header__BZXOb");
    //Поле ввода Имени
    private By nameInputField = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода Фамилия
    private By surnameInputField = By.xpath(".//input[@placeholder='* Фамилия']");
    //Поле ввода Адрес
    private By addressInputField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле Станция метро
    private By metroStationField = By.xpath(".//input[@placeholder='* Станция метро']");
    //Поле ввода номера телефона
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private By nextButton = By.className("Button_Middle__1CSJM");

    public OrderPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadHeader() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public void setName(String name) {
        driver.findElement(nameInputField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameInputField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressInputField).sendKeys(address);
    }

    public void setMetroStation(String metroStationFromOrder) {
        driver.findElement(metroStationField).click();
        driver.findElement(metroStationField).sendKeys(metroStationFromOrder);
        driver.findElement(metroStationField).sendKeys(Keys.DOWN, Keys.ENTER);
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public void makingOrder(String name, String surname, String address, String metroStationFromOrder, String phoneNumber) {
        waitForLoadHeader();
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetroStation(metroStationFromOrder);
        setPhoneNumber(phoneNumber);
    }

}
