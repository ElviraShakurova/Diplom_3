package tests;

import helpers.DriverRule;
import io.qameta.allure.Description;
import org.junit.Rule;
import org.junit.Test;
import pages.MainPage;

public class MainTest {
    @Rule
    public DriverRule driverRule = new DriverRule();

    @Description("Тест проводит проверку перехода к разделам Булки, Соусы, Начинки")
    @Test
    public void testIngredientTabs() {
        MainPage basePage = new MainPage(driverRule.getDriver())
                .clickSouses()
                .assertSousesIngredient()
                .clickFillings()
                .assertFillingIngredient()
                .clickBuns()
                .assertBunsIngredient();
    }
}