package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class ProfilePage {
    private final WebDriver driver;

    @FindBy(css = "a[href='/account/profile'][aria-current='page']")
    private WebElement accountButtons;

    @FindBy(linkText = "Конструктор")
    private WebElement constructorButton;

    @FindBy(className = "AppHeader_header__logo__2D0X2")
    private WebElement logoHeader;

    @FindBy(className = "Account_button__14Yp3")
    private WebElement exitButton;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Проверка отображения кнопки Профиль")
    public ProfilePage accountButtonsPresent() {
        assertTrue(accountButtons.isDisplayed());
        return this;
    }

    @Step("Нажатие на кнопку Конструктор на странице профиля")
    public MainPage clickConstructorButton() {
        constructorButton.click();
        return new MainPage(driver);
    }

    @Step("Нажатие на кнопку логотип Stellar Burgers на странице Профиля")
    public MainPage clickLogoHeader() {
        logoHeader.click();
        return new MainPage(driver);
    }

    @Step("Нажатие на кнопку Выход на странице профиля")
    public LoginPage clickExitProfile() {
        exitButton.click();
        return new LoginPage(driver);
    }
}