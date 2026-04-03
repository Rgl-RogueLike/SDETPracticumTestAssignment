package com.haritonov.practicum.tests;

import com.haritonov.practicum.base.BaseTest;
import com.haritonov.practicum.data.ExpectedData;
import com.haritonov.practicum.pages.FormFieldsPage;
import com.haritonov.practicum.utils.DataGenerator;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Practice Automation Forms")
@Feature("Form Fields Interaction")
public class FormFieldsTest extends BaseTest {

    @Test
    @Story("Positive scenario: Submit form with valid data")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Успешная отправка формы с валидными данными")
    public void positiveSubmitFormTest() {
        String randomName = DataGenerator.getRandomName();
        String randomPassword = DataGenerator.getRandomPassword();
        String randomEmail = DataGenerator.getRandomEmail();

        FormFieldsPage page = new FormFieldsPage(driver);
        page.openPage();
        String toolsMessage = page.getAutomationToolsMessage();

        page.setName(randomName)
                .setPassword(randomPassword)
                .selectDrink(ExpectedData.DRINK_MILK, ExpectedData.DRINK_COFFEE)
                .selectColor(ExpectedData.COROL_YELLOW)
                .setLikeAutomation(ExpectedData.LIKE_AUTOMATION_YES)
                .setEmail(randomEmail)
                .setMessage(toolsMessage);

        String alertText = page.submitAndGetAlertText();
        Assertions.assertEquals("Message received!", alertText);

    }

    @Test
    @Story("Negative scenario: Submit form without required Name")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Попытка отправки поля без обязательного поля Name")
    public void negativeSubmitFormTest() {
        FormFieldsPage page = new FormFieldsPage(driver);
        page.openPage();
        String toolsMessage = page.getAutomationToolsMessage();

        page.setPassword(DataGenerator.getRandomPassword())
                .selectDrink(ExpectedData.DRINK_MILK, ExpectedData.DRINK_COFFEE)
                .selectColor(ExpectedData.COROL_YELLOW)
                .setLikeAutomation(ExpectedData.LIKE_AUTOMATION_YES)
                .setEmail(DataGenerator.getRandomEmail())
                .setMessage(toolsMessage);

        page.submitForm();
        boolean isAlertThere = page.isAlertPresent();
        Assertions.assertFalse(isAlertThere, "Форма не должна отправляться без обязательного поля Name");
    }
}
