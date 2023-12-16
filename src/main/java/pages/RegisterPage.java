package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

public class RegisterPage {
    private final WebDriver driver;

    @FindBy(css = "input.text.input__textfield.text_type_main-default:nth-of-type(1)")
    private WebElement nameInput;

    @FindBy(xpath = "//label[contains(text(), 'Email')]/following-sibling::input")
    private WebElement emailInput;

    @FindBy(css = "input[name='Пароль']")
    private WebElement passwordInput;

    @FindBy(css = "button.button_button__33qZ0")
    private WebElement registerButton;

    @FindBy(css = "a.Auth_link__1fOlj[href='/login']")
    private WebElement entryButton;

    @FindBy(xpath = "//p[@class='input__error text_type_main-default' and text()='Некорректный пароль']")
    private WebElement errorPassword;


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод значения в поле Имя на странице регистрации")
    public RegisterPage setInputName(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    @Step("Ввод значения в поле Email на странице регистрации")
    public RegisterPage setInputEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Ввод значения в поле Пароль на станице регистрации")
    public RegisterPage setInputPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Нажатие на кнопку Зарегистрироваться")
    public LoginPage clickRegisterButton() {
        registerButton.click();
        return new LoginPage(driver);
    }

    @Step("Ввод невалидного значения в поле Пароль на станице регистрации")
    public RegisterPage setInvalidPassword() {
        passwordInput.sendKeys("12345");
        return this;
    }

    @Step("Нажатие на кнопку Войти на странице регистрации")
    public LoginPage clickEntryButton() {
        entryButton.click();
        return new LoginPage(driver);
    }

    @Step("Проверка отображения ошибки при вводе некорректного пароля")
    public RegisterPage errorPasswordPresent() {
        assertTrue(errorPassword.isDisplayed());
        return this;
    }
}