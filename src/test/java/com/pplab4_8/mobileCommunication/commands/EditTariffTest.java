package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;

import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовий клас для перевірки функціональності команди редагування тарифів ({@link EditTariff}).
 * Він перевіряє правильність оновлення тарифів для передплачених та контрактних тарифів.
 */
class EditTariffTest {
    private MobileNetwork network;
    private MobileNetworkService service;
    private EditTariff editTariff;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Ініціалізація перед кожним тестом.
     * Створюється нова мобільна мережа, сервіс та команда для редагування тарифів.
     * Підготовка поточного потоку виведення для тестів.
     */
    @BeforeEach
    void setUp() {
        network = new MobileNetwork();
        service = new MobileNetworkService(network);
        editTariff = new EditTariff(service);
        System.setOut(new PrintStream(outputStream));
    }

    /**
     * Тест для успішного редагування передплаченого тарифу.
     * Перевіряється, чи вдається оновити тариф з новими значеннями для назви, місячної плати, хвилин дзвінків та гігабайт.
     */
    @Test
    void testEditTariffSuccessForPrepaid() {
        TariffPrepaid tariff = new TariffPrepaid("Basic", 100, 200, 5);
        service.addTariff(tariff);

        String userInput = "Basic\nPremium\n150\n300\n10\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        try {
            editTariff.execute();
        } finally {
            System.setIn(System.in);
        }

        assertEquals(1, network.getTariffs().size());
        TariffPrepaid updatedTariff = network.getTariffs().get(0);
        assertEquals("Premium", updatedTariff.getName());
        assertEquals(150, updatedTariff.getMonthlyFee());
        assertEquals(300, updatedTariff.getCallMinutesCount());
        assertEquals(10, updatedTariff.getInternetGigabytesCount());
        assertTrue(outputStream.toString().contains("Тариф успішно оновлено."));
    }

    /**
     * Тест для успішного редагування контрактного тарифу.
     * Перевіряється, чи вдається оновити тариф з новими значеннями для назви, місячної плати, хвилин дзвінків, гігабайт і додаткових послуг.
     */
    @Test
    void testEditTariffSuccessForContract() {
        TariffContract tariff = new TariffContract("Contract Basic", 200, 400, 15, "Basic Services");
        service.addTariff(tariff);

        String userInput = "Contract Basic\nContract Premium\n250\n500\n20\nPremium Services\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        editTariff.execute();

        assertEquals(1, network.getTariffs().size());
        TariffContract updatedTariff = (TariffContract) network.getTariffs().get(0);
        assertEquals("Contract Premium", updatedTariff.getName());
        assertEquals(250, updatedTariff.getMonthlyFee());
        assertEquals(500, updatedTariff.getCallMinutesCount());
        assertEquals(20, updatedTariff.getInternetGigabytesCount());
        assertEquals("Premium Services", updatedTariff.getAdditionalServices());
        assertTrue(outputStream.toString().contains("Тариф успішно оновлено."));
    }

    /**
     * Тест для редагування тарифу з порожніми полями.
     * Перевіряється, чи не змінюються тарифні значення, якщо введено порожні дані.
     */
    @Test
    void testEditTariffWithEmptyFields() {
        TariffPrepaid tariff = new TariffPrepaid("Basic", 100, 200, 5);
        service.addTariff(tariff);

        String userInput = "Basic\n\n0\n0\n0\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        editTariff.execute();

        assertEquals(1, network.getTariffs().size());
        TariffPrepaid updatedTariff = network.getTariffs().get(0);
        assertEquals("Basic", updatedTariff.getName());
        assertEquals(100, updatedTariff.getMonthlyFee());
        assertEquals(200, updatedTariff.getCallMinutesCount());
        assertEquals(5, updatedTariff.getInternetGigabytesCount());
        assertTrue(outputStream.toString().contains("Тариф успішно оновлено."));
    }

    /**
     * Тест для редагування неіснуючого тарифу.
     * Перевіряється, чи відображається повідомлення про помилку, коли тариф не знайдений.
     */
    @Test
    void testEditNonExistingTariff() {
        String userInput = "NonExistentTariff\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        try {
            editTariff.execute();
        } finally {
            System.setIn(System.in);
        }

        assertEquals(0, network.getTariffs().size());
        assertTrue(outputStream.toString().contains("Тариф не знайдено."));
    }

    /**
     * Тест для редагування тарифу з некоректним введенням.
     * Перевіряється, чи зберігаються старі значення, коли введено некоректні дані.
     */
    @Test
    void testEditTariffWithInvalidInput() {
        TariffPrepaid tariff = new TariffPrepaid("Basic", 100, 200, 5);
        service.addTariff(tariff);

        String userInput = "Basic\n\ninvalid\n0\ninvalid\n0\n";
        System.setIn(new ByteArrayInputStream(userInput.getBytes()));

        editTariff.execute();

        assertEquals(1, network.getTariffs().size());
        TariffPrepaid unchangedTariff = network.getTariffs().get(0);
        assertEquals("Basic", unchangedTariff.getName());
        assertEquals(100, unchangedTariff.getMonthlyFee());
        assertEquals(200, unchangedTariff.getCallMinutesCount());
        assertEquals(5, unchangedTariff.getInternetGigabytesCount());
        assertTrue(outputStream.toString().contains("Тариф успішно оновлено."));
    }

    /**
     * Тест для методу {@link EditTariff#getName()}.
     * Перевіряється, чи повертає метод правильне ім'я команди.
     */
    @Test
    void testGetName() {
        MobileNetwork network = new MobileNetwork();
        MobileNetworkService service = new MobileNetworkService(network);
        EditTariff editTariff = new EditTariff(service);

        assertEquals("Редагувати тариф", editTariff.getName());
    }
}