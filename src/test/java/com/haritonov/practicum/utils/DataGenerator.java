package com.haritonov.practicum.utils;

import com.github.javafaker.Faker;
import com.haritonov.practicum.data.ExpectedData;

import java.util.Random;

public class DataGenerator {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    public static String getRandomName() {
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getRandomPassword() {
        return faker.internet().password(ExpectedData.PASSWORD_MIN_LENGTH, ExpectedData.PASSWORD_MAX_LENGTH);
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static int getRandomNumber(int minValue, int maxValue) {
        return random.nextInt(minValue, maxValue);
    }
}
