package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RentPageScooter {
    private WebDriver driver;
    //Заголовок страницы
    private final By header = By.className("Order_Header__BZXOb");
    //Поле дата начала аренды
    private final By rentalDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Поле Срок аренды
    private final By rentalTimeField = By.className("Dropdown-placeholder");
    //Список срока аренды
    private final By rentalTime = By.xpath(".//*[(@role ='option' and text()='сутки')]");
    //Полце цвета самоката "Черный жемчуг"
    private final By checkBoxColourBlackPearl = By.xpath(".//input[@id='black']");
    //Полце цвета самоката  "Серая безысходность"
    private final By checkBoxColourGreyDespair = By.xpath(".//input[@id='grey']");
    //Поле ввода комментария для курьера
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Кнопка "Заказать"
    private final By orderButton = By.xpath(".//button[(@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать')]");
    //Кнопка "Да" оформления заказа
    private final By orderButtonYes = By.xpath(".//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");
    // Окно Заказ оформлен
    private final By successAlert = By.className("Order_ModalHeader__3FDaJ");

    public RentPageScooter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadHeader() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(header));
    }

    public void setRentalDateField(String rentalDate) {
        driver.findElement(rentalDateField).sendKeys(rentalDate);
        driver.findElement(rentalDateField).sendKeys(Keys.ENTER);
    }

    public void setRentalTimeField() {
        driver.findElement(rentalTimeField).click();
        driver.findElement(rentalTime).click();
    }

    public void clickCheckBoxColourBlackPearl() {
        driver.findElement(checkBoxColourBlackPearl).click();
    }

    public void clickCheckBoxColourGreyDespair() {
        driver.findElement(checkBoxColourGreyDespair).click();
    }

    public void setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    public void clickOrderButtonYes() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(orderButtonYes));
        driver.findElement(orderButtonYes).click();
    }

    public void makingRentPageScooter(String rentalDate, String comment) {
        waitForLoadHeader();
        setRentalDateField(rentalDate);
        setRentalTimeField();
        clickCheckBoxColourBlackPearl();
        clickCheckBoxColourGreyDespair();
        setCommentField(comment);
        clickOrderButton();
        clickOrderButtonYes();
    }

    public String getSuccessAlert() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(successAlert))).isDisplayed();
        return driver.findElement(successAlert).getText();
    }
}
