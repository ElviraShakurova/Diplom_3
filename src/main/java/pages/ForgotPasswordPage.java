package pages;

import helpers.EnvHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(className = "Auth_link__1fOlj")
    private WebElement entryForgotPasswordButton;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, EnvHelper.getExplicitWaitDuration());
    }

    @Step("Нажатие на кнопку входа на странице формы восстановления пароля")
    public LoginPage clickEntryForgotPasswordButton() {
        wait.until(ExpectedConditions.visibilityOf(entryForgotPasswordButton));
        entryForgotPasswordButton.click();
        return new LoginPage(driver);
    }
}