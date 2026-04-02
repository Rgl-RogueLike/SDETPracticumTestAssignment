package com.haritonov.practicum.pages;

import com.haritonov.practicum.data.ExpectedData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FormFieldsPage {

    private final WebDriver driver;

    @FindBy(id = "name_input")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(css = "#email")
    private WebElement emailInput;

    @FindBy(css = "#message")
    private WebElement messageField;

    @FindBy(css = "#submit-btn")
    private WebElement submitButton;

    @FindBy(css = "#automantion")
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

    public FormFieldsPage openPage() {
        driver.get(ExpectedData.PAGE_URL);
        return this;
    }

    public FormFieldsPage setName(String name) {
        nameInput.sendKeys(name);
        return this;
    }

    public FormFieldsPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public FormFieldsPage selectDrink(String drinkName) {
        for (WebElement checkbox : drinkCheckboxes) {
            if (checkbox.getAttribute("value").equals(drinkName)) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
                break;
            }
        }
        return this;
    }

    public FormFieldsPage selectColor(String colorName) {
        for (WebElement radio : colorRadios) {
            if (radio.getAttribute("value").equals(colorName)) {
                radio.click();
                break;
            }
        }
        return this;
    }

    public FormFieldsPage setLikeAutomation(String optionText) {
        Select select = new Select(automationDropdown);
        select.selectByVisibleText(optionText);
        return this;
    }

    public FormFieldsPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public FormFieldsPage setMessage(String message) {
        messageField.sendKeys(message);
        return this;
    }

    public void submitForm() {
        submitButton.click();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

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
}
