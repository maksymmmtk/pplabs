package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тестовий клас для перевірки функціональності команди видалення тарифу ({@link RemoveTariff}).
 * Тести охоплюють випадки видалення існуючого тарифу, неіснуючого тарифу, а також порожнього введення.
 */
public class RemoveTariffTest {
    private MobileNetwork network;
    private MobileNetworkService service;
    private ByteArrayOutputStream outputStream;
    private RemoveTariff removeTariff;

    /**
     * Ініціалізація перед кожним тестом.
     * Створюється нова мобільна мережа, сервіс та команда для видалення тарифу.
     * Підготовка поточного потоку виведення для тестів.
     */
    @BeforeEach
    void setUp() {
        network = new MobileNetwork();
        service = new MobileNetworkService(network);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        removeTariff = new RemoveTariff(service);
    }

    /**
     * Тест для успішного видалення існуючого тарифу.
     * Перевіряється, чи видаляється тариф з мережі, якщо він існує.
     */
    @Test
    void testRemoveExistingTariff() {
        TariffPrepaid tariff = new TariffPrepaid("Standard", 100, 300, 10);
        service.addTariff(tariff);

        String userInput = "Standard\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        removeTariff.execute();

        assertEquals(0, network.getTariffs().size());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Тариф успішно видалено."));
    }

    /**
     * Тест для видалення неіснуючого тарифу.
     * Перевіряється, чи відображається повідомлення про помилку, коли тариф не знайдений.
     */
    @Test
    void testRemoveNonExistingTariff() {
        String userInput = "NonExistingTariff\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        removeTariff.execute();

        assertEquals(0, network.getTariffs().size());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Тариф не знайдено."));
    }

    /**
     * Тест для видалення тарифу при порожньому введенні.
     * Перевіряється, чи відображається повідомлення про помилку при порожньому введенні.
     */
    @Test
    void testRemoveTariffWithEmptyInput() {
        String userInput = "\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        removeTariff.execute();

        assertEquals(0, network.getTariffs().size());
        String output = outputStream.toString();
        assertEquals(true, output.contains("Тариф не знайдено."));
    }

    /**
     * Тест для методу {@link RemoveTariff#getName()}.
     * Перевіряється, чи повертає метод правильне ім'я команди.
     */
    @Test
    void testGetName() {
        assertEquals("Видалити тариф", removeTariff.getName());
    }
}