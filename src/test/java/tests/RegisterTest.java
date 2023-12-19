package tests;

import helpers.DriverRule;
import helpers.EnvHelper;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

public class RegisterTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    private String accessToken;

    @Description("Тест проводит проверку успешной регистрации пользователя")
    @Test
    public void testSuccessfullyRegister() {
        MainPage basePage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = basePage.clickPersonalAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterButton()
                .setInputName(EnvHelper.getName())
                .setInputEmail(EnvHelper.getEmail())
                .setInputPassword(EnvHelper.getPassword());
        LoginPage loginPageAfterRegistration = registerPage.clickRegisterButton()
                .entryTitlePresent()
                .setEmailLoginInput(EnvHelper.getEmail())
                .setPasswordLoginInput(EnvHelper.getPassword());
        MainPage mainPage = loginPageAfterRegistration.clickEntryLoginButton()
                .orderButtonPresent();
    }

    @Description("Тест проводит проверку неуспешной регистрации пользователя при вводе некорректного пароля")
    @Test
    public void testErrorRegistration() {
        MainPage basePage = new MainPage(driverRule.getDriver());
        LoginPage loginPage = basePage.clickPersonalAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterButton()
                .setInputName(EnvHelper.getName())
                .setInputEmail(EnvHelper.getEmail())
                .setInvalidPassword();
        LoginPage loginPageAfterRegistration = registerPage.clickEntryButton();
        RegisterPage registerPageAfterRegistration = registerPage.errorPasswordPresent();
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            WebStorage webStorage = (WebStorage) driverRule.getDriver();
            LocalStorage localStorage = webStorage.getLocalStorage();
            accessToken = localStorage.getItem("accessToken");
            System.out.println(accessToken);

            Response response = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .header("authorization", accessToken)
                    .when()
                    .delete(EnvHelper.getBaseUrl() + "/api/auth/user");
            response.then().statusCode(202);
        }
    }
}