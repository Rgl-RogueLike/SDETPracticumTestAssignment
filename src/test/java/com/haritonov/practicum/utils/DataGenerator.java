package com.haritonov.practicum.utils;

import com.github.javafaker.Faker;
import com.haritonov.practicum.data.ExpectedData;

import java.util.Random;

/**
 * Утилитарный класс для генерации случайных тестовых данных.
 * <p>
 * Использует библиотеку JavaFaker для создания валидных email, паролей и имен.
 * </p>
 */
public class DataGenerator {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    /**
     * Генерирует случайное имя (First Name + Last Name) с использованием Faker
     * @return случайное имя
     */
    public static String getRandomName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    /**
     * Генерирует случайный пароль указанной длины
     * @return случайный пароль
     */
    public static String getRandomPassword() {
        return faker.internet().password(ExpectedData.PASSWORD_MIN_LENGTH, ExpectedData.PASSWORD_MAX_LENGTH);
    }

    /**
     * Генерирует случайный email адрес
     * @return случайный email
     */
    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

}
