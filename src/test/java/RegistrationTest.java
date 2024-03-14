import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import page_object.LoginPage;
import page_object.MainPage;
import page_object.RegPage;
import user.UserAPI;
import user.UserCredentials;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends WebDriverShell {

    MainPage objMainPage;
    LoginPage objLoginPage;
    RegPage objRegPage;
    UserAPI userAPI = new UserAPI();

    @Test
    @DisplayName("Регистрация нового пользователя")
    public void regNewUser() {
        objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.waitForLoadHomePage();
        objMainPage.clickPersonalCabinetButton();

        objLoginPage = new LoginPage(driver);
        objLoginPage.waitForLoadLoginPage();
        objLoginPage.clickRegistrationLink();

        objRegPage = new RegPage(driver);
        objRegPage.waitForLoadRegPage();
        objRegPage.setRegData(UserCredentials.fakeName, UserCredentials.fakeEmail, UserCredentials.fakePassword);
        objRegPage.clickRegButton();

        objMainPage.clickPersonalCabinetButton();

        objLoginPage.setUserData(UserCredentials.fakeEmail, UserCredentials.fakePassword);

        boolean isButtonVisible = objMainPage.isCreateOrderButtonDisplayed();
        assertTrue(isButtonVisible);
    }

    @After
    public void deleteUser(){
        Response response = userAPI.login(UserCredentials.from(UserCredentials.user));
        String token = response.then().extract().path("accessToken");
        userAPI.delete(token);
    }
}