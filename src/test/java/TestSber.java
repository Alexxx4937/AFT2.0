import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestSber {

        WebDriver driver;
        String baseUrl;



        @Before
        public void setUp() {
            System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

            driver = new ChromeDriver();
            baseUrl = "https://sberbank.ru/ru/person";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.manage().window().maximize();



        }


        @Test
        public void testInsurance() {
            driver.get(baseUrl + "/");
            Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

            driver.findElement(By.xpath("//span[text()='Страхование']")).click();
            driver.findElement(By.xpath("//a[text()='Страхование путешественников' and @class='lg-menu__sub-link']")).click();
            Assert.assertEquals("Страхование путешественников", driver.findElement(By.xpath("//h1[text()='Страхование путешественников']")).getText());
            driver.findElement(By.xpath(" //div[@id='SBRF_TabContainer_sb_bundle-47610460'] //img[@src='/portalserver/content/atom/contentRepository/content/person/travel/banner-zashita-traveler.jpg?id=f6c836e1-5c5c-4367-b0d0-bbfb96be9c53']")).click();
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='Страхование путешественников']"))));
            Assert.assertEquals("Страхование путешественников", driver.findElement(By.xpath("//h2[text()='Страхование путешественников']")).getText());



        }
    private void fillField(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);

    }
    }
