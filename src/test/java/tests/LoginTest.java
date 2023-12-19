package tests;

import helpers.DriverRule;
import helpers.EnvHelper;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

public class LoginTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    public static String generatedEmail;
    private String accessToken;

    @Before
    public void setUp() {
        generatedEmail = EnvHelper.getEmail();
        MainPage basePage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = basePage.clickPersonalAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterButton()
                .setInputName(EnvHelper.getName())
                .setInputEmail(generatedEmail)
                .setInputPassword(EnvHelper.getPassword());
        LoginPage loginPageAfterRegistration = registerPage.clickRegisterButton()
                .entryTitlePresent();
    }

    @Description("Тест проводит проверку входа в аккаунт через кнопку «Личный кабинет»")
    @Test
    public void testLoginByPersonalAccountButton() {
        MainPage basePage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = basePage.clickPersonalAccountButton()
                .setEmailLoginInput(generatedEmail)
                .setPasswordLoginInput(EnvHelper.getPassword());
        MainPage loggedInMainPage = loginPage.clickEntryLoginButton()
                .orderButtonPresent();
    }

    @Description("Тест проводит проверку входа в аккаунт по кнопке «Войти в аккаунт» на главной")
    @Test
    public void testLoginByEntryAccountButton() {
        MainPage basePage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = basePage.clickEntryButton()
                .setEmailLoginInput(generatedEmail)
                .setPasswordLoginInput(EnvHelper.getPassword());
        MainPage basePageAfterLogin = loginPage.clickEntryLoginButton()
                .orderButtonPresent();
    }

    @Description("Тест проводит проверку входа в аккаунт кнопку Войти в форме восстановления пароля")
    @Test
    public void testLoginResetPasswordForm() {
        MainPage basePage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = basePage.clickPersonalAccountButton();
        ForgotPasswordPage forgotPasswordPage = loginPage.clickResetPasswordButton();
        LoginPage loginPageAfterResetPassword = forgotPasswordPage.clickEntryForgotPasswordButton()
                .setEmailLoginInput(generatedEmail)
                .setPasswordLoginInput(EnvHelper.getPassword());
        MainPage basePageAfterLogin = loginPageAfterResetPassword.clickEntryLoginButton()
                .orderButtonPresent();
    }

    @Description("Тест проводит проверку входа в аккаунт через кнопку Войти в форме регистрации")
    @Test
    public void testLoginByRegisterForm() {
        MainPage basePage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = basePage.clickPersonalAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterButton();
        LoginPage loginPageAfterRegistration = registerPage.clickEntryButton()
                .setEmailLoginInput(generatedEmail)
                .setPasswordLoginInput(EnvHelper.getPassword());
        MainPage basePageAfterLogin = loginPageAfterRegistration.clickEntryLoginButton()
                .orderButtonPresent();
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            WebStorage webStorage = (WebStorage) driverRule.getDriver();
            LocalStorage localStorage = webStorage.getLocalStorage();
            accessToken = localStorage.getItem("accessToken");

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .header("authorization", accessToken)
                    .when()
                    .delete(EnvHelper.getBaseUrl() + "/api/auth/user");
            response.then().statusCode(202);
        }
    }
}