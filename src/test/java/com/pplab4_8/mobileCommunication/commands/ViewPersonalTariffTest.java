package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import com.pplab4_8.mobileCommunication.users.MobileSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тестовий клас для перевірки функціональності перегляду особистого тарифу абонента.
 * Тести перевіряють, чи правильно відображається тариф, коли він встановлений,
 * а також, чи правильно виводиться повідомлення, коли тариф не встановлений.
 */
public class ViewPersonalTariffTest {
    private MobileSubscriber subscriber;
    private ByteArrayOutputStream outputStream;
    private ViewPersonalTariff viewPersonalTariff;

    /**
     * Ініціалізація перед кожним тестом.
     * Створюється новий абонент і команда для перегляду особистого тарифу.
     * Підготовка поточного потоку виведення для тестів.
     */
    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        subscriber = new MobileSubscriber("John Doe", 18);
        viewPersonalTariff = new ViewPersonalTariff(subscriber);
    }

    /**
     * Тест для перевірки правильності виведення тарифу абонента, коли тариф встановлено.
     * Перевіряється, чи правильно виводиться тариф, коли абонент має тариф.
     */
    @Test
    void testViewPersonalTariffWhenTariffExists() {
        TariffPrepaid tariff = new TariffPrepaid("Standard", 100, 300, 20);
        subscriber.setSilentMode(true);
        subscriber.setTariff(tariff);

        viewPersonalTariff.execute();

        String expectedOutput = "Ваш тариф:\n" + tariff.toString();
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    /**
     * Тест для перевірки правильності виведення повідомлення, коли тариф не встановлено.
     * Перевіряється, чи виводиться правильне повідомлення, коли абонент не має тарифу.
     */
    @Test
    void testViewPersonalTariffWhenNoTariffExists() {
        viewPersonalTariff.execute();

        String expectedOutput = "Тариф не встановлено.";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    /**
     * Тест для методу {@link ViewPersonalTariff#getName()}.
     * Перевіряється, чи повертає метод правильне ім'я команди.
     */
    @Test
    void testGetName() {
        assertEquals("Переглянути особистий тариф", viewPersonalTariff.getName());
    }
}