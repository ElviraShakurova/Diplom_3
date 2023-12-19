package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

public class EnvHelper {
    private static final String ENV_FILE = "src/test/env_local.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        try {
            PROPERTIES.load(new FileInputStream(ENV_FILE));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load environment properties file: " + ENV_FILE, e);
        }
    }

    public static String getChromeDriverPath() {
        return PROPERTIES.getProperty("chromeDriver.path");
    }

    public static String getYandexDriverPath() {
        return PROPERTIES.getProperty("yandexDriver.path");
    }

    public static String getYandexPath() {
        return PROPERTIES.getProperty("yandexBrowser.path");
    }

    public static String getChromeBrowserPath() {
        return PROPERTIES.getProperty("chromeBrowser.path");
    }

    public static String getBaseUrl() {
        return PROPERTIES.getProperty("base.url");
    }

    public static Duration getImplicitWaitDuration() {
        int seconds = Integer.parseInt(PROPERTIES.getProperty("implicitly.wait.seconds", "5"));
        return Duration.ofSeconds(seconds);
    }

    public static Duration getExplicitWaitDuration() {
        int seconds = Integer.parseInt(PROPERTIES.getProperty("explicit.wait.seconds", "10"));
        return Duration.ofSeconds(seconds);
    }

    public static String getName() {
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        return "Harry" + randomNumber;
    }

    public static String getEmail() {
        Random random = new Random();
        int randomNumber = random.nextInt(10000);
        return "Harry" + randomNumber + "@mai.ru";
    }

    public static String getPassword() {
        return PROPERTIES.getProperty("password");
    }
}