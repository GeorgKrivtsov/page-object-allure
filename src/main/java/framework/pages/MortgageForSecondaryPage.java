package framework.pages;

import framework.managers.DriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MortgageForSecondaryPage extends BasePage {

    @FindBy(xpath = "//label[contains(text(), 'Стоимость недвижимости')]/..")
    private WebElement count;

    @FindBy(xpath = "//label[contains(text(), 'Первоначальный взнос')]/../input")
    private WebElement firstPay;

    @FindBy(xpath = "//label[contains(text(), 'Срок кредита')]/../input")
    private WebElement term;

    @FindBy(xpath = "//div[@class='_2RwsL_oKFrEK9oh3oTaIT0']")
    private List<WebElement> listServices;

    @FindBy(xpath = "//span[@class='_2K-vC4nTzrGG1TQHQ2HGL']")
    private List<WebElement> listCreditDetails;

    @FindBy(xpath = "//div[@class='inpt-root__input-container-6-3-4-beta-1-for-calculator']")
    private List<WebElement> listOfInputFields;


//    public void setInputFields(String name, String count){
//        driverManager.getDriver().switchTo().frame("iFrameResizer0");
//        for (WebElement field : inputFields) {
//            if(field.findElement(By.xpath(".//label")).getText().equalsIgnoreCase(name)) {
//                field.findElement(By.xpath(".//input")).sendKeys(Keys.ENTER);
//                ((JavascriptExecutor) driverManager.getDriver()).executeScript("arguments[0].value = '" + count +
//                        "'", field.findElement(By.xpath(".//input")));
//                field.findElement(By.xpath(".//input")).sendKeys(Keys.ENTER);
//
//            }
//        }
//        driverManager.getDriver().switchTo().defaultContent();
//
//    }

    public MortgageForSecondaryPage skrollToElement(){
        WebElement h2 = DriverManager.getInstance().getDriver().findElement(By.xpath("//h2[contains(text(), 'Рассчитайте ипотеку')]"));
        pageManager.getMortgageForSecondaryPage().scrollWithOffset(h2,0, 350);
        return this;
    }

    @Step("Заполняем поле '{nameOfForm}' значением '{parametersToFill}'")
    public MortgageForSecondaryPage findForm(String nameOfForm, String parametersToFill){
        driverManager.getDriver().switchTo().frame("iFrameResizer0");
        for(WebElement form :listOfInputFields) {
            if(form.findElement(By.xpath("./label")).getText().contains(nameOfForm)) {
                WebElement field = form.findElement(By.xpath(".//input"));
                field.click();
                field.sendKeys(Keys.COMMAND + "a");
                field.sendKeys(Keys.BACK_SPACE);
                sendKeysByOneChar(field, parametersToFill);
                waitStabilityPage(5000, 250);
                Assertions.assertEquals(parametersToFill, field.getAttribute("value").replaceAll("[^0-9]", ""),
                        "Поле заполнилось не корректно");

                driverManager.getDriver().switchTo().defaultContent();
                return this;
            }
        }
        Assertions.fail("Поле не найдено");
        return this;
    }


    public void sendKeysByOneChar(WebElement element, String value){
        String[] string = value.split("");
        Actions actions = new Actions(driverManager.getDriver());
        for(String charItem : string) {
            actions.moveToElement(element).pause(100).sendKeys(charItem).pause(100).build().perform();
        }
    }

    protected void waitStabilityPage (int maxWaitMillis, int pollDelimiter) {
        double startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < startTime + maxWaitMillis) {
            String prevState = driverManager.getDriver().getPageSource();
            wait(pollDelimiter);
            if(prevState.equals(driverManager.getDriver().getPageSource()));
        }
    }

    public void wait(int mlSec) {
        try {
            Thread.sleep(mlSec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Step("Кликаем чекбокс '{nameServices}'")
    public MortgageForSecondaryPage clickOnServicesCheckbox(String nameServices) {
        driverManager.getDriver().switchTo().frame("iFrameResizer0");
        for (WebElement itemService: listServices) {
            WebElement nameService = itemService.findElement(By.xpath("./span"));
            if(nameService.getText().contains(nameServices)) {
                WebElement checkbox = itemService.findElement(By.xpath("./span/label/div/input"));
                ((JavascriptExecutor) driverManager.getDriver()).executeScript("arguments[0].click();", checkbox);
                driverManager.getDriver().switchTo().defaultContent();
                return this;
            }
        }
        return this;
    }


    @Step("Проверяем значение '{name}' c введенным значение '{count}' ")
    public MortgageForSecondaryPage checkCreditDetails(String name, double count) {
        driverManager.getDriver().switchTo().frame("iFrameResizer0");
        for (WebElement creditDetail : listCreditDetails) {
            if(creditDetail.getText().equalsIgnoreCase(name)) {
                WebElement countCreditDetail = creditDetail.findElement(By.xpath("./../div/span/span"));
                countCreditDetail.getText().replaceAll("[^0-9]", "");
                Assertions.assertEquals(Integer.parseInt(countCreditDetail.getText().replaceAll("[^0-9]", "")), count, "Значения \"" +
                        creditDetail.getText() + "\" не равны");
                driverManager.getDriver().switchTo().defaultContent();
                return this;
            }
        }

        Assertions.fail("Элемент не найден");
        return this;
    }

    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(code, element, x, y);
        return element;
    }

}
