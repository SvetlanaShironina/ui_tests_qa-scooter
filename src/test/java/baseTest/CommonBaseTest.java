package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import model.MainPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.concurrent.TimeUnit;

import static model.Constants.PAGE_URL;

public class CommonBaseTest {
        public WebDriver driver;
        @Before
        public void setUp() {
        //    WebDriverManager.chromedriver().setup();
        //    ChromeOptions options = new ChromeOptions();
        //    options.addArguments("--remote-allow-origins=*");
        //    driver = new ChromeDriver(options);
        //    driver.get(PAGE_URL);
        //    MainPage objMainPage = new MainPage(driver);
        //    objMainPage.clickPopUpCookies();

        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new EdgeDriver(options);
        driver.get(PAGE_URL);
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickPopUpCookies();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

    @After
    public void tearDown() {
        driver.quit();
    }
}
