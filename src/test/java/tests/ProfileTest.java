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
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegisterPage;

public class ProfileTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    private String accessToken;
    private MainPage basePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private MainPage mainPage;
    private ProfilePage profilePage;
    public static String generatedEmail;

    @Before
    public void setUp() throws Exception {
        generatedEmail = EnvHelper.getEmail();

        basePage = new MainPage(driverRule.getDriver());
        loginPage = basePage.clickPersonalAccountButton();
        registerPage = loginPage.clickRegisterButton()
                .setInputName(EnvHelper.getName())
                .setInputEmail(generatedEmail)
                .setInputPassword(EnvHelper.getPassword());
        loginPage = registerPage.clickRegisterButton()
                .entryTitlePresent()
                .setEmailLoginInput(generatedEmail)
                .setPasswordLoginInput(EnvHelper.getPassword());
        mainPage = loginPage.clickEntryLoginButton()
                .orderButtonPresent();
        profilePage = mainPage.clickPersonalAccountButtonToProfile();
    }

    @Description("Тест проводит проверку перехода по клику на «Личный кабинет»")
    @Test
    public void testClickPersonalAccountButton() {
        profilePage.accountButtonsPresent();
    }

    @Description("Тест проводит проверку перехода из личного кабинета в конструктор по клику на «Конструктор»")
    @Test
    public void testConstructor() {
        mainPage = profilePage.clickConstructorButton()
                .orderButtonPresent();
    }

    @Description("Тест проводит проверку перехода из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    @Test
    public void testLogoHeader() {
        mainPage = profilePage.clickLogoHeader()
                .orderButtonPresent();
    }

    @Description("Тест проводит проверку выхода из профиля по кнопке «Выйти» в личном кабинете")
    @Test
    public void testExitProfile() {
        loginPage = profilePage.clickExitProfile()
                .entryTitlePresent();
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