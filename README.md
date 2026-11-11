# Practice Automation: Form Fields

Автотесты для страницы [Form Fields](https://practice-automation.com/form-fields/) на Java. Проект проверяет отправку формы с валидными данными и поведение формы при пропуске обязательного поля.

## Что проверяется

| Тест | Сценарий | Ожидаемый результат |
|---|---|---|
| `positiveSubmitFormTest` | Заполнение всех полей случайными данными, выбор напитков, цвета и ответа «Do you like automation?», отправка формы | Появляется Alert с текстом `Message received!` |
| `negativeSubmitFormTest` | Отправка формы без обязательного поля `Name`, остальные поля заполнены | Форма не отправляется, Alert отсутствует |

В позитивном тесте количество инструментов и самый длинный инструмент вычисляются со страницы, а не задаются константами.

## Стек

- Java 17
- Maven
- Selenium WebDriver 4
- JUnit 6 (Jupiter)
- Page Object + PageFactory + Fluent-цепочки
- Allure (Epic / Feature / Story / Severity, скриншоты)
- JavaFaker — генерация тестовых данных
- WebDriverManager — автоматическая установка драйвера

## Структура проекта

```text
src/test/java/com/haritonov/practicum/
├── base/BaseTest.java          # жизненный цикл WebDriver, скриншоты
├── data/ExpectedData.java      # URL и ожидаемые значения
├── pages/FormFieldsPage.java   # Page Object страницы формы
├── tests/FormFieldsTest.java   # позитивный и негативный тесты
└── utils/
    ├── AllureUtils.java        # прикрепление скриншота к отчёту
    └── DataGenerator.java      # генерация имени, пароля и email
```

## Запуск

Требования: JDK 17, Maven, установленный Google Chrome.

```bash
mvn clean test
```

Отчёт Allure:

```bash
mvn allure:serve
```

Пример отчёта — в [`screenshots/ScreenshotAllure.png`](screenshots/ScreenshotAllure.png).

## Особенности

- Браузер — Chrome, драйвер поднимается через WebDriverManager.
- Скриншот делается после каждого теста и прикрепляется к Allure-отчёту.
- Внешний сайт может менять разметку — при падении сначала проверяйте актуальность локаторов в `FormFieldsPage`.
