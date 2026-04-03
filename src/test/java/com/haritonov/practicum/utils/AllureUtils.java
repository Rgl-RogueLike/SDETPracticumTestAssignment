package com.haritonov.practicum.utils;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * Утилитарный класс для интеграции с отчетами Allure.
 */
public class AllureUtils {

    /**
     * Делает скриншот текущего состояния браузера и прикрепляет его к отчету Allure.
     *
     * @param driver экземпляр WebDriver.
     * @return скриншот в виде массива байтов (для Allure).
     */
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
