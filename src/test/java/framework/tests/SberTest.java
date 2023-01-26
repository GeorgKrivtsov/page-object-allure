package framework.tests;

import framework.managers.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SberTest extends BaseTests {

    @Test
    @DisplayName("проверка ипотечного калькулятора")

    public void testScenario() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        pageManager.getFindBlock().closeCookies()
                .selectBaseMenuByTest("Ипотека")
                .selectSubMenuByTest("Ипотека на вторичное жильё")
                .getMortgageForSecondaryPage()
                .skrollToElement()
                .clickOnServicesCheckbox("Страхование жизни и здоровья")
                .findForm("Стоимость недвижимости", "5180000")
                .findForm("Первоначальный взнос", "3058000")
                .findForm("Срок кредита", "30")
                .checkCreditDetails("Сумма кредита", 2122000)
                .checkCreditDetails("Ежемесячный платеж", 21664)
                .checkCreditDetails("Необходимый доход", 36829)
                .checkCreditDetails("Процентная ставка", 11);
    }



}
