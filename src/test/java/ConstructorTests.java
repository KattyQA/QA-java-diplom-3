import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ConstructorTests {

    private final By bunsButton = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[1]");
    private final By saucesButton = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[2]");
    private final By fillingsButton = By.xpath("/html/body/div/div/main/section[1]/div[1]/div[3]");
    private final String expectedState = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    private WebDriver webDriver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Переход к разделу 'Булки'")
    public void goToBunsPage() {
        MainPage mainPage = new MainPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        mainPage.goToSaucesPage();
        mainPage.goToBunsPage();
        String actualState = webDriver.findElement(bunsButton).getAttribute("class");
        Assert.assertEquals(expectedState, actualState);

    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Переход к разделу 'Соусы'")
    public void goToSaucesPage() {
        MainPage mainPage = new MainPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        mainPage.goToSaucesPage();
        String actualState = webDriver.findElement(saucesButton).getAttribute("class");
        Assert.assertEquals(expectedState, actualState);

    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Переход к разделу 'Начинки'")
    public void goTofillingsPage() {
        MainPage mainPage = new MainPage(webDriver);
        webDriver.get("https://stellarburgers.nomoreparties.site");
        mainPage.goToFillingsPage();
        String actualState = webDriver.findElement(fillingsButton).getAttribute("class");
        Assert.assertEquals(expectedState, actualState);

    }


    @After
    public void teardown() {
        webDriver.quit();
    }
}
