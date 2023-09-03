package base;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
    public static ChromeOptions getOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless");
        return options;
    }
}
