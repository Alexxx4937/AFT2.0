import org.junit.After;
import org.junit.Assert;
import org.junit.Before;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class SberTest {

    WebDriver driver;
    String baseUrl;
  String driverName;


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Тестов", "Тест", "Тестович"},
                {"Тихонов", "Андрей", "Валерьевич"},
                {"Титов", "Егор", "Ильич"}
        });
    }
    @Parameterized.Parameter
    public String lastName;

    @Parameterized.Parameter(1)
    public String firstName;

    @Parameterized.Parameter(2)
    public String middleName;







    @Before
    public void setUp()  {

        System.setProperty("webdriver.gecko.driver", "drv/geckodriver.exe");
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");

        driver = new ChromeDriver();

        //Для вызова тестов из командной строки с выбором браузера раскомментировать данный блок
        //не смог понять почему в мозилле на середине теста, тест может упасть, а может дойти до конца и успешно выполнится.

/*driverName = System.getProperty("browser").toLowerCase();
        if (driverName.equals("chrome")) {
            driver = new ChromeDriver();
        }
        else if (driverName.equals("firefox")) {
            driver = new FirefoxDriver();
        }*/




        baseUrl = "https://sberbank.ru/ru/person";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    @Test
    public void testInsurance()  {


        driver.get(baseUrl + "/");
        Wait<WebDriver> wait = new WebDriverWait(driver, 5, 1000);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[contains(text(),'Страхование')]"))));
        driver.findElement(By.xpath("//span[contains(text(),'Страхование')]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//ul[@class='lg-menu__list']//a[.='Страхование путешественников']"))));
        driver.findElement(By.xpath("//ul[@class='lg-menu__list']//a[.='Страхование путешественников']")).click();


        Assert.assertEquals("Страхование путешественников", driver.findElement(By.xpath("//h1[text()='Страхование путешественников']")).getText());
        driver.findElement(By.xpath("//img[@src='/portalserver/content/atom/contentRepository/content/person/travel/banner-zashita-traveler.jpg?id=f6c836e1-5c5c-4367-b0d0-bbfb96be9c53']")).click();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='Страхование путешественников']"))));
        Assert.assertEquals("Страхование путешественников", driver.findElement(By.xpath("//h2[text()='Страхование путешественников']")).getText());
        driver.findElement(By.xpath("//section[@class='b-form-main-section']/section[2]/div[@class='b-form-box-block']/div[1]")).click();
        driver.findElement(By.xpath("//span[@class='b-continue-btn']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(" //span[text()='Введите данные застрахованных латинскими буквами, как в загранпаспорте']"))));
        Assert.assertEquals("Введите данные застрахованных латинскими буквами, как в загранпаспорте", driver.findElement(By.xpath(" //span[text()='Введите данные застрахованных латинскими буквами, как в загранпаспорте']")).getText());


        fillField(By.xpath("//input[@name='insured0_surname']"),"Petrov");
        fillField(By.xpath("//input[@name='insured0_name']"),"Alex");
        fillField(By.xpath("//input[@name='insured0_birthDate']"),"20111980");

        fillField(By.xpath("//input[@name='surname']"),lastName);

        fillField(By.xpath("//input[@name='name']"),firstName);

        fillField(By.xpath("//input[@name='middlename']"),middleName);
        fillField(By.xpath("//input[@name='birthDate']"),"20111980");
        driver.findElement(By.xpath("//input[@name='male']")).click();
        fillField(By.xpath(" //input[@name='passport_series']"),"4105");
        fillField(By.xpath("//input[@name='passport_number']"),"123456");
        fillField(By.xpath("//input[@name='issueDate']"),"15102005");
        fillField(By.xpath("//textarea[@name='issuePlace']"),"УФМС по г. Москве");

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='insured0_surname']")).getAttribute("value"),"Petrov");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='insured0_name']")).getAttribute("value"),"Alex");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='insured0_birthDate']")).getAttribute("value"),"20.11.1980");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='surname']")).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"),firstName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='middlename']")).getAttribute("value"),middleName);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='birthDate']")).getAttribute("value"),"20.11.1980");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='passport_series']")).getAttribute("value"),"4105");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='passport_number']")).getAttribute("value"),"123456");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='issueDate']")).getAttribute("value"),"15.10.2005");
        Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='issuePlace']")).getAttribute("value"),"УФМС по г. Москве");


        driver.findElement(By.xpath("//span[@class='b-continue-btn']")).click();
        Assert.assertEquals( "Заполнены не все обязательные поля",driver.findElement(By.xpath("//div[text()='Заполнены не все обязательные поля']")).getText());





    }
    @After
    public void tearDown() {
        driver.quit();
    }

    private void fillField(By locator, String value) {
        driver.findElement(locator).clear();
        driver.manage().timeouts().implicitlyWait((long) 0.2, TimeUnit.SECONDS);
        driver.findElement(locator).sendKeys(value);
    }
}

