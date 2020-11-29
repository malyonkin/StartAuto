package PageObject;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class selenideElementPO {

    public void buttonSearch() {
        SelenideElement buttonSearch = $(".home__search-form .search-form__btn");
    }
}
