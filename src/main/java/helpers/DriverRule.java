package helpers;

import helpers.EnvHelper;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class DriverRule extends ExternalResource {
    private WebDriver driver;

    @Override
    protected void before() throws Throwable {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        String browser = System.getProperty("browser", "chrome");
        switch (browser) {
            case "chrome":
                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(EnvHelper.getChromeDriverPath()))
                        .build();
                ChromeOptions chromeOptions = new ChromeOptions()
                        .setBinary(EnvHelper.getChromeBrowserPath());
                driver = new ChromeDriver(service, chromeOptions);
                driver.manage().timeouts().implicitlyWait(EnvHelper.getImplicitWaitDuration());
                break;
            case "yandex":
                ChromeDriverService yandexService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(EnvHelper.getYandexDriverPath()))
                        .build();
                ChromeOptions yandexOptions = new ChromeOptions()
                        .setBinary(EnvHelper.getYandexPath());
                driver = new ChromeDriver(yandexService, yandexOptions);
                driver.manage().timeouts().implicitlyWait(EnvHelper.getImplicitWaitDuration());
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый браузер: " + browser);
        }
        driver.get(EnvHelper.getBaseUrl());
    }

    @Override
    protected void after() {
        driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}