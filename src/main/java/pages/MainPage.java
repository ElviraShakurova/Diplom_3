package pages;

import helpers.EnvHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@href, '/account')]")
    private WebElement personalAccountButton;

    @FindBy(css = ".button_button__33qZ0")
    private WebElement entryLoginButton;

    @FindBy(css = "button.button_button_type_primary__1O7Bx")
    private WebElement orderButton;

    @FindBy(xpath = "//span[contains(@class, 'text_type_main-default') and contains(text(), 'Булки')]")
    private WebElement buns;

    @FindBy(xpath = "//span[contains(@class, 'text_type_main-default') and contains(text(), 'Соусы')]")
    private WebElement souses;

    @FindBy(xpath = "//span[contains(@class, 'text_type_main-default') and contains(text(), 'Начинки')]")
    private WebElement fillings;

    @FindBy(css = "a[href='/ingredient/61c0c5a71d1f82001bdaaa6d']")
    private WebElement bunIngredient;

    @FindBy(css = "a[href='/ingredient/61c0c5a71d1f82001bdaaa72']")
    private WebElement sousesIngredient;

    @FindBy(css = "a[href='/ingredient/61c0c5a71d1f82001bdaaa6f']")
    private WebElement fillingsIngredient;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, EnvHelper.getImplicitWaitDuration());
    }

    @Step("Нажатие на кнопку Личный кабинет для перехода на страницу авторизации")
    public LoginPage clickPersonalAccountButton() {
        wait.until(ExpectedConditions.visibilityOf(personalAccountButton));
        personalAccountButton.click();
        return new LoginPage(driver);
    }

    @Step("Нажатие на кнопку Личный кабинет для перехода на страницу Профиля, уже после авторизации")
    public ProfilePage clickPersonalAccountButtonToProfile() {
        wait.until(ExpectedConditions.visibilityOf(personalAccountButton));
        personalAccountButton.click();
        return new ProfilePage(driver);
    }

    @Step("Нажатие на кнопку Войти в аккаунт")
    public LoginPage clickEntryButton() {
        entryLoginButton.click();
        return new LoginPage(driver);
    }

    @Step("Проверка отображения Оформить заказ")
    public MainPage orderButtonPresent() {
        wait.until(ExpectedConditions.visibilityOf(orderButton));
        assertTrue(orderButton.isDisplayed());
        return this;
    }

    @Step("Нажатие на таб Булки")
    public MainPage clickBuns() {
        wait.until(ExpectedConditions.visibilityOf(buns));
        buns.click();
        return this;
    }

    @Step("Проверка отображения ингредиента на странице Булки")
    public MainPage assertBunsIngredient() {
        assertTrue(bunIngredient.isDisplayed());
        return this;
    }

    @Step("Проверка отображения ингредиента на странице Соусы")
    public MainPage assertSousesIngredient() {
        assertTrue(sousesIngredient.isDisplayed());
        return this;
    }

    @Step("Проверка отображения ингредиента на странице Начинки")
    public MainPage assertFillingIngredient() {
        assertTrue(fillingsIngredient.isDisplayed());
        return this;
    }

    @Step("Нажатие на таб Соусы")
    public MainPage clickSouses() {
        souses.click();
        return this;
    }

    @Step("Нажатие на таб Начинки")
    public MainPage clickFillings() {
        fillings.click();
        return this;
    }
}