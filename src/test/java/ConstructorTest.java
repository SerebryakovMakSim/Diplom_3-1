import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.testng.annotations.Test;
import pageobject.MainPage;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends WebDriverShell {

    MainPage objMainPage;

    @Before
    public void createUser() {
        objMainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переход к секции Булки")
    public void openSectionBunsTest() {
        objMainPage.openMainPage();
        objMainPage.waitForLoadHomePage();
        objMainPage.clickConstructorButton();
        objMainPage.clickSectionSauce(); // Сперва нужно перейти на любой другой раздел, иначе "незачет"
        objMainPage.clickSectionBuns();
        assertEquals("Булки", objMainPage.getCurrentSectionType());
    }

    @Test
    @DisplayName("Переход к секции Соусы")
    public void openSectionSauceTest() {
        objMainPage.openMainPage();
        objMainPage.waitForLoadHomePage();
        objMainPage.clickConstructorButton();
        objMainPage.clickSectionSauce();
        assertEquals("Соусы", objMainPage.getCurrentSectionType());
    }

    @Test
    @DisplayName("Переход к секции Начинки")
    public void openSectionFillingTest() {
        objMainPage.openMainPage();
        objMainPage.waitForLoadHomePage();
        objMainPage.clickConstructorButton();
        objMainPage.clickSectionFilling();
        assertEquals("Начинки", objMainPage.getCurrentSectionType());
    }

}