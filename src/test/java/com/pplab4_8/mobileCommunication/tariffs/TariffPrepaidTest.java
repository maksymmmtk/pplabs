package com.pplab4_8.mobileCommunication.tariffs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовий клас для перевірки функціональності класу TariffPrepaid.
 * Тести перевіряють методи доступу до полів, додавання/видалення абонентів та інші операції з тарифами.
 */
class TariffPrepaidTest {

    private TariffPrepaid tariff;

    /**
     * Ініціалізація тестових даних перед виконанням кожного тесту.
     * Створюється тарифний план з передоплатою.
     */
    @BeforeEach
    void setUp() {
        tariff = new TariffPrepaid("Test Tariff", 50.0f, 100, 10.0f);
    }

    /**
     * Тест для перевірки коректності роботи геттерів.
     * Перевіряється, чи правильно працюють методи для отримання значень полів.
     */
    @Test
    void testGetters() {
        assertEquals("Test Tariff", tariff.getName());
        assertEquals(50.0f, tariff.getMonthlyFee());
        assertEquals(100, tariff.getCallMinutesCount());
        assertEquals(10.0f, tariff.getInternetGigabytesCount());
        assertEquals(0, tariff.getSubscribersCount(), "Кількість абонентів повинна бути 0");
    }

    /**
     * Тест для перевірки коректності роботи сеттерів.
     * Перевіряється, чи правильно працюють методи для встановлення значень полів.
     */
    @Test
    void testSetters() {
        tariff.setName("New Name");
        tariff.setMonthlyFee(60.0f);
        tariff.setCallMinutesCount(200);
        tariff.setInternetGigabytesCount(15.0f);

        assertEquals("New Name", tariff.getName());
        assertEquals(60.0f, tariff.getMonthlyFee());
        assertEquals(200, tariff.getCallMinutesCount());
        assertEquals(15.0f, tariff.getInternetGigabytesCount());
    }

    /**
     * Тест для перевірки додавання абонента до тарифу.
     * Перевіряється, чи збільшується кількість абонентів після додавання.
     */
    @Test
    void testAddSubscriber() {
        tariff.addSubscriber();
        assertEquals(1, tariff.getSubscribersCount(), "Кількість абонентів повинна бути 1 після додавання");
    }

    /**
     * Тест для перевірки видалення абонента з тарифу.
     * Перевіряється, чи зменшується кількість абонентів після видалення.
     */
    @Test
    void testSubSubscriber() {
        tariff.addSubscriber(); // Додавання одного абонента
        tariff.subSubscriber(); // Видалення одного абонента
        assertEquals(0, tariff.getSubscribersCount(), "Кількість абонентів повинна бути 0 після видалення");
    }

    /**
     * Тест для перевірки методу toString.
     * Перевіряється, чи правильно формується рядок, що описує тариф.
     */
    @Test
    void testToString() {
        String expected = "Тариф: Test Tariff, Місячна плата: 50,00, Хвилини дзвінків: 100, Гігабайти інтернету: 10,00, Кількість клієнтів: 0";
        assertEquals(expected, tariff.toString(), "Метод toString має повертати правильний опис тарифу");
    }
}