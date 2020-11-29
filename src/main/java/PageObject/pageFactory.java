package PageObject;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class pageFactory {
    public class GoogleSearchPage {
        @FindBy(how = How.ID_OR_NAME, using = "q")
        public SelenideElement search;}

    public class GoogleResultsPage {
        @FindBy(how = How.CSS, using = "#ires li.g")
        public SelenideElement searchBox;}
}
