package GroupTest;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import PageObject.selenideElementPO;

public class SimpleTest {
    selenideElementPO test1 = new selenideElementPO();

    @BeforeEach
    public void start() throws Exception {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.browser ="chrome";
        Configuration.baseUrl="https://www.rbc.ru";
        //Configuration.driverManagerEnabled = false; //отключение автоматического управления драйвером, чтобы драйвер не запускался на локальном ПК
        //Configuration.remote = "http://localhost:4444/wd/hub"; //подключение к удаленному серверу Selenium. Сервер поднят в докере
        Configuration.browserSize = "1366x768";
        Configuration.timeout = 6000;
        Configuration.fastSetValue = true;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(false)
                .savePageSource(true)
        );
    }

    @Test
    public void SearchEmpty() throws InterruptedException {
        open("/companies/");
        getWebDriver().findElement(By.cssSelector(".home__search-form .search-form__btn")).click(); //использование selenium в selenide. Тоже самое в selenide $(".home__search-form .search-form__btn");
        $("h3").shouldHave(Condition.text("Все результаты")); //тест
        Thread.sleep(1000);
    }

    @Test
    public void SearchSpecificCompany() throws InterruptedException {
        open("/companies/");
        $(".home__search-form #query").val("Сбербанк");
        test1.buttonSearch();
        $$(".company-card").shouldHave(CollectionCondition.size(20)); //проверяем коллекцию отобразившихся карточек. .company-card:nth-child(1)
        Thread.sleep(1000);
    }

    @Test
    public void selectCardSpecificCompany() throws InterruptedException { //https://selenide.gitbooks.io/user-guide/content/en/selenide-api/elements-collection.html
        open("/companies/search/?query=Сбербанк"); //сразу переходим к нужной странице проверки
        $$(".company-name").findBy(Condition.ownText("ТЕЛЕКОМ")).click(usingJavaScript()); //используем функцию js для нажатия на кнопку - не по координатам
        $("strong:nth-child(1)").shouldHave(Condition.text("Гурджиян Руслан Валерьевич"));
        Thread.sleep(1000);
    }

    @AfterEach
    public void tearDown() {
        SelenideLogger.removeListener("allure"); //remove listener
    }
}
