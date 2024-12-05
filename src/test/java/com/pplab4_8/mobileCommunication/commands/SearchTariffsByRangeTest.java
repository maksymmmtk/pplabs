package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;
import com.pplab4_8.mobileCommunication.users.MobileSubscriber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тестовий клас для перевірки функціональності пошуку тарифів в заданому діапазоні цін та підключення їх до абонента.
 * Тести перевіряють сценарії знаходження тарифу в діапазоні, підключення тарифу або відмову від підключення,
 * а також ситуації, коли тариф не знайдений або введення некоректне.
 */
public class SearchTariffsByRangeTest {
    private MobileNetwork network;
    private MobileNetworkService service;
    private MobileSubscriber subscriber;
    private ByteArrayOutputStream outputStream;
    private SearchTariffsByRange searchTariffsByRange;

    /**
     * Ініціалізація перед кожним тестом.
     * Створюється нова мобільна мережа, сервіс, абонент, а також команда для пошуку тарифів за діапазоном цін.
     * Підготовка поточного потоку виведення для тестів.
     */
    @BeforeEach
    void setUp() {
        network = new MobileNetwork();
        service = new MobileNetworkService(network);
        subscriber = new MobileSubscriber("John Doe", 18);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        searchTariffsByRange = new SearchTariffsByRange(subscriber, service);
    }

    /**
     * Тест для знаходження тарифу в межах діапазону та підключення його до абонента.
     * Перевіряється, чи тариф знаходиться в діапазоні та чи правильно підключається до абонента.
     */
    @Test
    void testFindTariffWithinRangeAndSubscribe() {
        TariffPrepaid tariff = new TariffPrepaid("Standard", 150, 300, 20);
        service.addTariff(tariff);

        String userInput = "100\n200\n1\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        searchTariffsByRange.execute();

        assertEquals(tariff, subscriber.getTariff());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Знайдено тариф:"));
        assertEquals(true, output.contains("Підключити цей тариф? (1 - так, 2 - ні):"));
    }

    /**
     * Тест для знаходження тарифу в межах діапазону, але без підключення.
     * Перевіряється, чи тариф не підключається до абонента, якщо користувач вибрав відмову від підключення.
     */
    @Test
    void testFindTariffWithinRangeButDoNotSubscribe() {
        TariffPrepaid tariff = new TariffPrepaid("Standard", 150, 300, 20);
        service.addTariff(tariff);

        String userInput = "100\n200\n2\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        searchTariffsByRange.execute();

        assertEquals(null, subscriber.getTariff());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Знайдено тариф:"));
        assertEquals(true, output.contains("Тариф не змінено."));
    }

    /**
     * Тест для ситуації, коли тариф не знайдений в заданому діапазоні.
     * Перевіряється, чи відображається повідомлення про відсутність тарифу в межах діапазону.
     */
    @Test
    void testNoTariffFoundWithinRange() {
        TariffPrepaid tariff = new TariffPrepaid("Standard", 300, 300, 20);
        service.addTariff(tariff);

        String userInput = "100\n200\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        searchTariffsByRange.execute();

        assertEquals(null, subscriber.getTariff());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Тариф у заданому діапазоні не знайдено."));
    }

    /**
     * Тест для некоректного введення діапазону цін.
     * Перевіряється, чи правильно система обробляє помилку при некоректному введенні (нечислове значення).
     */
    @Test
    void testInvalidInputForRange() {
        String userInput = "abc\n200\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        try {
            searchTariffsByRange.execute();
        } catch (Exception e) {
            assertEquals(true, e instanceof java.util.InputMismatchException);
        }
    }

    /**
     * Тест для методу {@link SearchTariffsByRange#getName()}.
     * Перевіряється, чи повертає метод правильне ім'я команди.
     */
    @Test
    void testGetName() {
        assertEquals("Знайти тариф", searchTariffsByRange.getName());
    }
}