package framework.pages;

import framework.managers.PageManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FindBlock extends BasePage {

    @FindBy(xpath = "//a[@role = 'button' and contains(@class, 'kitt-top-menu')]")
    private List<WebElement> listBaseMenu;

    @FindBy(xpath = "//a[contains(@class, 'menu__link_second')]")
    private List<WebElement> listSubMenu;

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    private WebElement cookiesButton;


    public FindBlock closeCookies() {
        cookiesButton.click();
        return this;
    }

    public FindBlock selectBaseMenuByTest (String nameMenu) {
        for (WebElement itemMenu : listBaseMenu) {
            if(itemMenu.getText().contains(nameMenu)){
                itemMenu.click();
                return this;
            }
        }
        Assertions.fail("Меню с текстом " + nameMenu + " не найдено на стортовой странице");
        return this;
    }

    public PageManager selectSubMenuByTest (String nameMenu) {
        for (WebElement itemMenu : listSubMenu) {
            if(itemMenu.getText().contains(nameMenu)){
                itemMenu.click();
                return pageManager;
            }
        }
        Assertions.fail("Подменю с текстом " + nameMenu + " не найдено на стортовой странице");
        return pageManager;
    }

}
