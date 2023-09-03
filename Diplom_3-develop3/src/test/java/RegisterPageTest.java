import base.BaseTest;
import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import rest.UserClient;

public class RegisterPageTest extends BaseTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private MainPage mainPage;
    private Header header;
    private UserCabinetPage userCabinetPage;
    Faker faker = new Faker();
    User userToRegister;
    private UserClient api = new UserClient();


    @Before
    public void setUp(){
        driver = new ChromeDriver(getOptions());
        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        header = new Header(driver);
        userCabinetPage = new UserCabinetPage(driver);
    }

    @After
    public void tearDown(){
        String authToken = api.getAuthToken(userToRegister);
        if(authToken != null){
            api.delete(authToken);
        }
        driver.quit();
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void registerUserSuccess(){
        driver.get(RegisterPage.REGISTER_PAGE_URL);
        userToRegister = new User(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(6, 10));
        registerPage.register(userToRegister.getName(), userToRegister.getEmail(), userToRegister.getPassword());

        loginPage.assertThatLoginPageOpened();
    }

    @Test
    @DisplayName("Вывод ошибки при регистрации с некорректным паролем. Минимальный пароль — шесть символов.")
    public void registerUserWhenPasswordNotValidFails(){
        driver.get(RegisterPage.REGISTER_PAGE_URL);
        userToRegister = new User(faker.name().firstName(), faker.internet().emailAddress(), faker.internet().password(1, 5));
        registerPage.register(userToRegister.getName(), userToRegister.getEmail(), userToRegister.getPassword());

        registerPage.assertThatIncorrectPasswordMessageShows();
    }
}
