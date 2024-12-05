package com.pplab4_8.mobileCommunication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * Тестовий клас для перевірки виконання методу {@link Main#executeApp(InputStream)}.
 * Цей клас містить тести, які перевіряють, чи коректно працює додаток з введеними даними.
 */
public class MainTest {

    /** Вхідний потік для тестових даних */
    private InputStream inputStream;

    /**
     * Підготовка тестових даних перед виконанням кожного тесту.
     * Створюється потік вводу з тестовими даними для симуляції введення користувача.
     */
    @BeforeEach
    public void setUp() {
        // Симульоване введення користувача
        String testInput = "1\nkyivstar\n1234\n0\n";
        inputStream = new ByteArrayInputStream(testInput.getBytes());
    }

    /**
     * Тест для перевірки роботи методу {@link Main#executeApp(InputStream)}.
     * Цей тест симулює введення команд користувача та перевіряє, як програма виконує ці команди.
     *
     * @see Main#executeApp(InputStream)
     */
    @Test
    public void testExecuteApp() {
        // Виконання методу з тестовим потоком вводу
        Main.executeApp(inputStream);
    }
}