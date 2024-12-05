package com.pplab4_8.mobileCommunication.tariffs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовий клас для перевірки функціональності класу TariffContract.
 * Тести перевіряють методи доступу до полів, додавання/видалення абонентів та інші операції з тарифами.
 */
class TariffContractTest {

    private TariffContract tariff;

    /**
     * Ініціалізація тестових даних перед виконанням кожного тесту.
     * Створюється тарифний план з контрактом.
     */
    @BeforeEach
    void setUp() {
        tariff = new TariffContract("Contract Tariff", 100.0f, 200, 20.0f, "International Calls");
    }

    /**
     * Тест для перевірки коректності роботи геттерів та сеттерів.
     * Перевіряється, чи правильно працюють методи для отримання та встановлення значень полів.
     */
    @Test
    void testGettersAndSetters() {
        // Перевірка початкових значень
        assertEquals("Contract Tariff", tariff.getName());
        assertEquals(100.0f, tariff.getMonthlyFee());
        assertEquals(200, tariff.getCallMinutesCount());
        assertEquals(20.0f, tariff.getInternetGigabytesCount());
        assertEquals("International Calls", tariff.getAdditionalServices());

        // Зміна значень за допомогою сеттерів
        tariff.setName("Updated Tariff");
        tariff.setMonthlyFee(120.0f);
        tariff.setCallMinutesCount(250);
        tariff.setInternetGigabytesCount(25.0f);
        tariff.setAdditionalServices("Unlimited Data");

        // Перевірка змінених значень
        assertEquals("Updated Tariff", tariff.getName());
        assertEquals(120.0f, tariff.getMonthlyFee());
        assertEquals(250, tariff.getCallMinutesCount());
        assertEquals(25.0f, tariff.getInternetGigabytesCount());
        assertEquals("Unlimited Data", tariff.getAdditionalServices());
    }

    /**
     * Тест для перевірки методу toString.
     * Перевіряється, чи коректно формується рядок, що описує тариф.
     */
    @Test
    void testToString() {
        String expected = "Тариф: Contract Tariff, Місячна плата: 100,00, Хвилини дзвінків: 200, Гігабайти інтернету: 20,00, Кількість клієнтів: 0, Додаткові послуги: International Calls";
        assertEquals(expected, tariff.toString());
    }

    /**
     * Тест для перевірки додавання абонента до тарифу.
     * Перевіряється, чи правильно збільшується кількість абонентів після додавання.
     */
    @Test
    void testAddSubscriber() {
        tariff.addSubscriber();
        assertEquals(1, tariff.getSubscribersCount(), "Кількість абонентів має бути 1");
    }

    /**
     * Тест для перевірки видалення абонента з тарифу.
     * Перевіряється, чи правильно зменшується кількість абонентів після видалення.
     */
    @Test
    void testSubSubscriber() {
        tariff.addSubscriber(); // Додавання одного абонента
        tariff.subSubscriber(); // Видалення одного абонента
        assertEquals(0, tariff.getSubscribersCount(), "Кількість абонентів має бути 0");
    }
}