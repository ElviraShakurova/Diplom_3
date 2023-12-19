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

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//a[@href='/register']")
    private WebElement registerButton;

    @FindBy(linkText = "Восстановить пароль")
    private WebElement resetPasswordButton;

    @FindBy(xpath = "//h2[text()='Вход']")
    private WebElement entryTitle;

    @FindBy(name = "name")
    private WebElement emailLoginInput;

    @FindBy(name = "Пароль")
    private WebElement passwordLoginInput;

    @FindBy(css = ".button_button_type_primary__1O7Bx")
    private WebElement entryLoginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, EnvHelper.getExplicitWaitDuration());
    }

    @Step("Нажатие на кнопку Зарегистрироваться на странице авторизации")
    public RegisterPage clickRegisterButton() {
        wait.until(ExpectedConditions.visibilityOf(registerButton));
        registerButton.click();
        return new RegisterPage(driver);
    }

    @Step("Проверка отображения заголовка Вход на странице авторизации")
    public LoginPage entryTitlePresent() {
        wait.until(ExpectedConditions.visibilityOf(entryTitle));
        assertTrue(entryTitle.isDisplayed());
        return this;
    }

    @Step("Ввод значения в поле Email")
    public LoginPage setEmailLoginInput(String email) {
        emailLoginInput.sendKeys(email);
        return this;
    }

    @Step("Ввод значения в поле Пароль")
    public LoginPage setPasswordLoginInput(String password) {
        passwordLoginInput.sendKeys(password);
        return this;
    }

    @Step("Нажатие на кнопку Войти на странице авторизации")
    public MainPage clickEntryLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(entryLoginButton));
        entryLoginButton.click();
        return new MainPage(driver);
    }

    @Step("Нажатие на кнопку Восстановить пароль на странице авторизации")
    public ForgotPasswordPage clickResetPasswordButton() {
        resetPasswordButton.click();
        return new ForgotPasswordPage(driver);
    }
}