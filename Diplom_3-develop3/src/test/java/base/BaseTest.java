package base;

import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.Constructor;
import pages.MainPage;

public class BaseTest {
    protected ChromeOptions options;
    @Before
    public void setUp(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless");

    }
}
