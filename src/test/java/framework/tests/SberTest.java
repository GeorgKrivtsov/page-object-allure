package framework.tests;

import framework.managers.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SberTest extends BaseTests {

    @Test
    public void testScenario() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        pageManager.getFindBlock().selectBaseMenuByTest("Ипотека");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        pageManager.getFindBlock().selectSubMenuByTest("Ипотека на вторичное жильё");


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement h2 = DriverManager.getInstance().getDriver().findElement(By.xpath("//h2[contains(text(), 'Рассчитайте ипотеку')]"));
        pageManager.getMortgageForSecondaryPage().scrollWithOffset(h2,0, 300);


        pageManager.getMortgageForSecondaryPage().clickOnServicesCheckbox("Страхование жизни и здоровья");

//        pageManager.getMortgageForSecondaryPage().setInputFields("Стоимость недвижимости", "5180000");
//        pageManager.getMortgageForSecondaryPage().setInputFields("Первоначальный взнос", "3058000");
//        pageManager.getMortgageForSecondaryPage().setInputFields("Срок кредита", "30");



//        pageManager.getMortgageForSecondaryPage().setCount("5180000");
//        pageManager.getMortgageForSecondaryPage().setFirstPay("3058000");
//        pageManager.getMortgageForSecondaryPage().setTerm("30");
//



        DriverManager.getInstance().getDriver().switchTo().frame("iFrameResizer0");
        Assertions.assertAll(
                ()-> pageManager.getMortgageForSecondaryPage().checkCreditDetails("Сумма кредита", 2122000),
                ()-> pageManager.getMortgageForSecondaryPage().checkCreditDetails("Ежемесячный платеж", 21664),
                ()-> pageManager.getMortgageForSecondaryPage().checkCreditDetails("Необходимый доход", 36829),
                ()-> pageManager.getMortgageForSecondaryPage().checkCreditDetails("Процентная ставка", 11)
                );
    }



}
