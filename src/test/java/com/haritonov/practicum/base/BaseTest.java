package com.haritonov.practicum.base;

import com.haritonov.practicum.utils.AllureUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * Базовый класс для всех UI тестов.
 * <p>
 * Отвечает за жизненный цикл WebDriver:
 * <ul>
 *   <li>Инициализация драйвера перед каждым тестом (@BeforeEach).</li>
 *   <li>Настройка браузера (полноэкранный режим, таймауты).</li>
 *   <li>Завершение работы и очистка ресурсов после каждого теста (@AfterEach).</li>
 *   <li>Автоматическое создание скриншотов при падении или завершении теста для Allure отчета.</li>
 * </ul>
 * </p>
 */
public class BaseTest {
    protected WebDriver driver;

    /**
     * Настраивает WebDriverManager для автоматического управления драйверами.
     * Выполняется один раз перед всеми тестами.
     */
    @BeforeAll
    public static void setUpDriver() {
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Инициализирует экземпляр браузера Chrome.
     * Устанавливает полноэкранный режим и неявные (implicit) ожидания.
     * Выполняется перед каждым тестом.
     */
    @BeforeEach
    public void initDriver() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    /**
     * Закрывает браузер и делает скриншот (даже если тест упал).
     * Выполняется после каждого теста.
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            AllureUtils.takeScreenshot(driver);
            driver.quit();
        }
    }
}
