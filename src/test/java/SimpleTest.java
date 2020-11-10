import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


import java.io.File;
import java.net.URL;

public class SimpleTest {

    @BeforeEach
    public void start() throws Exception {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.driverManagerEnabled = false;
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.browserSize = "1366x768";
        Configuration.timeout = 6000;
        Configuration.fastSetValue = true;
    }

    @org.junit.jupiter.api.Test
    public void SearchEmpty() throws InterruptedException {

        open("https://www.rbc.ru/companies/");
        $(".home__search-form .search-form__btn");
        getWebDriver().findElement(By.cssSelector(".home__search-form .search-form__btn")).click(); //использование selenium в selenide
        Thread.sleep(1000);
        $("h3").shouldHave(Condition.text("Все результаты")); //тест
    }
}
