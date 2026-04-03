package com.haritonov.practicum.pages;

import com.haritonov.practicum.data.ExpectedData;
import com.haritonov.practicum.utils.AllureUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page Object класс, представляющий страницу "Form Fields".
 * <p>
 * URL: <a href="https://practice-automation.com/form-fields/">...</a>
 * </p>
 * <p>
 * Предоставляет методы для взаимодействия с элементами формы
 * </p>
 */
public class FormFieldsPage {

    private final WebDriver driver;

    @FindBy(id = "name-input")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(css = "#email")
    private WebElement emailInput;

    @FindBy(css = "#message")
    private WebElement messageField;

    @FindBy(css = "#submit-btn")
    private WebElement submitButton;

    @FindBy(css = "#automation")
    private WebElement automationDropdown;

    @FindBy(xpath = "//input[@name='fav_drink']")
    private List<WebElement> drinkCheckboxes;

    @FindBy(xpath = "//input[@name='fav_color']")
    private List<WebElement> colorRadios;

    @FindBy(xpath = "//label[text()='Automation tools']/following-sibling::ul/li")
    private List<WebElement> automationToolsList;

    public FormFieldsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Открывает страницу формы в браузере
     * @return текущий экземпляр страницы для цепочки вызовов
     */
    public FormFieldsPage openPage() {
        driver.get(ExpectedData.PAGE_URL);
        return this;
    }

    /**
     * Вводит имя в поле Name
     * @param name имя пользователя
     * @return текущий экземпляр страницы
     */
    public FormFieldsPage setName(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    /**
     * Вводит пароль в поле Password
     * @param password пароль
     * @return текущий экземпляр страницы
     */
    public FormFieldsPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    /**
     * Выбирает напитки из списка чекбоксов по их значениям.
     * Позволяет передать несколько напитков сразу
     * @param drinkNames массив названий напитков
     * @return текущий экземпляр страницы
     */
    public FormFieldsPage selectDrink(String... drinkNames) {
        for (String name : drinkNames) {
            for (WebElement checkbox : drinkCheckboxes) {
                if (checkbox.getAttribute("value").equals(name)) {
                    if (!checkbox.isSelected()) {
                        checkbox.click();
                    }
                    break;
                }
            }
        }
        return this;
    }

    /**
     * Выбирает цвет из списка радио-кнопок по значению
     * @param colorName название цвета
     * @return текущий экземпляр страницы
     */
    public FormFieldsPage selectColor(String colorName) {
        for (WebElement radio : colorRadios) {
            if (radio.getAttribute("value").equals(colorName)) {
                radio.click();
                break;
            }
        }
        return this;
    }

    /**
     * Выбирает ответ в выпадающем списке "Do you like automation?"
     * @param optionText видимый текст опции
     * @return текущий экземпляр страницы
     */
    public FormFieldsPage setLikeAutomation(String optionText) {
        Select select = new Select(automationDropdown);
        select.selectByVisibleText(optionText);
        return this;
    }

    /**
     * Вводит email адрес
     * @param email email
     * @return текущий экземпляр страницы
     */
    public FormFieldsPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    /**
     * Вводит текст в поле Message
     * @param message текст сообщения
     * @return текущий экземпляр страницы
     */
    public FormFieldsPage setMessage(String message) {
        messageField.sendKeys(message);
        return this;
    }

    /**
     * Нажимает кнопку Submit без дополнительных проверок
     */
    public void submitForm() {
        submitButton.click();
    }

    /**
     * Нажимает кнопку Submit, обрабатывает появившийся Alert и возвращает его текст
     * Делает скриншот перед кликом для отчета
     * @return текст сообщения из Alert
     */
    public String submitAndGetAlertText() {
        AllureUtils.takeScreenshot(driver);
        submitButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    /**
     * Проверяет, присутствует ли на странице всплывающее окно Alert
     * Используется для негативного теста
     * @return true если алерт открыт, false если нет
     */
    public String getAutomationToolsMessage() {
        int count = automationToolsList.size();
        String longestTool = "";
        for (WebElement tool : automationToolsList) {
            String textTool = tool.getText();
            if (textTool.length() > longestTool.length()) {
                longestTool = textTool;
            }
        }

        return String.format("Количество инструметов: %d. Инструмент, содержащий наибольшее количество символов: %s.", count, longestTool);
    }

    /**
     * Анализирует список инструментов на странице и формирует строку с их количеством
     * и названием самого длинного инструмента
     * @return строка с результатами анализа
     */
    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
