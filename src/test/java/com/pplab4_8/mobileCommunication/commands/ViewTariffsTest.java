package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тестовий клас для перевірки функціональності перегляду наявних тарифів у мобільній мережі.
 * Тести перевіряють різні варіанти перегляду тарифів, включаючи порожній список, наявність тарифів,
 * підрахунок клієнтів та сортування тарифів за платою.
 */
class ViewTariffsTest {
    private MobileNetwork network;
    private MobileNetworkService service;
    private ViewTariffs viewTariffs;
    private ByteArrayOutputStream outputStream;

    /**
     * Ініціалізація перед кожним тестом.
     * Створюється мережа, сервіс та команда для перегляду тарифів.
     * Ініціалізується потік виведення для перевірки результатів виконання.
     */
    @BeforeEach
    void setUp() {
        network = new MobileNetwork();
        service = new MobileNetworkService(network);
        viewTariffs = new ViewTariffs(service);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Тест для перевірки правильності виведення повідомлення, коли список тарифів порожній.
     * Перевіряється, чи правильно виводиться повідомлення "Список тарифів порожній."
     */
    @Test
    void testViewTariffsWithEmptyList() {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));

        viewTariffs.execute();

        String expectedOutput = "Список тарифів порожній.";
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    /**
     * Тест для перевірки виведення списку тарифів, коли в мережі є тарифи.
     * Перевіряється, чи виводиться правильний список тарифів.
     */
    @Test
    void testViewTariffsWithItems() {
        network.addTariff(new TariffPrepaid("Basic", 10.0f, 100, 5.0f));
        network.addTariff(new TariffPrepaid("Premium", 20.0f, 200, 10.0f));

        System.setIn(new ByteArrayInputStream("3\n".getBytes()));

        viewTariffs.execute();

        String output = outputStream.toString();
        assertEquals(true, output.contains("Список тарифів:"));
        assertEquals(true, output.contains("Basic"));
        assertEquals(true, output.contains("Premium"));
    }

    /**
     * Тест для перевірки підрахунку кількості клієнтів для тарифу.
     * Перевіряється, чи правильно виводиться кількість клієнтів для тарифу.
     */
    @Test
    void testCalculateTotalClients() {
        TariffPrepaid basic = new TariffPrepaid("Basic", 10.0f, 100, 5.0f);
        basic.addSubscriber();
        basic.addSubscriber();
        network.addTariff(basic);

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        viewTariffs.execute();

        String expectedOutput = "Клієнтів: 2";
        assertEquals(true, outputStream.toString().contains(expectedOutput));
    }

    /**
     * Тест для перевірки правильності сортування тарифів за платою.
     * Перевіряється, чи тариф "Basic" йде перед "Premium" після сортування.
     */
    @Test
    void testSortTariffsByFee() {
        network.addTariff(new TariffPrepaid("Basic", 10.0f, 100, 5.0f));
        network.addTariff(new TariffPrepaid("Premium", 20.0f, 200, 10.0f));

        System.setIn(new ByteArrayInputStream("2\n".getBytes()));

        viewTariffs.execute();

        String expectedOutput = "Тарифи відсортовано.";
        assertEquals(true, outputStream.toString().contains(expectedOutput));

        List<TariffPrepaid> sortedTariffs = network.getTariffs();
        assertEquals("Basic", sortedTariffs.get(0).getName());
        assertEquals("Premium", sortedTariffs.get(1).getName());
    }

    /**
     * Тест для перевірки правильної реакції при виборі опції завершення.
     * Перевіряється, чи програма коректно завершується при виборі опції "3".
     */
    @Test
    void testExitOption() {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));

        viewTariffs.execute();

        String expectedOutput = "Завершено.";
        assertEquals(false, outputStream.toString().contains(expectedOutput));
    }

    /**
     * Тест для методу {@link ViewTariffs#getName()}.
     * Перевіряється, чи повертає метод правильне ім'я команди для перегляду тарифів.
     */
    @Test
    void testGetName() {
        assertEquals("Переглянути наявні тарифи", viewTariffs.getName());
    }
}