package com.pplab4_8.mobileCommunication.service;

import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовий клас для перевірки функціональності сервісу мобільної мережі.
 * Перевіряються різні операції над тарифами: додавання, видалення, редагування, пошук, сортування та інше.
 */
class MobileNetworkServiceTest {
    private MobileNetwork network;
    private MobileNetworkService service;

    private TariffPrepaid tariff1;
    private TariffPrepaid tariff2;

    /**
     * Ініціалізація тестових даних перед виконанням кожного тесту.
     * Створюються мережа та тарифи для подальших тестів.
     */
    @BeforeEach
    void setUp() {
        network = new MobileNetwork();
        service = new MobileNetworkService(network);

        tariff1 = new TariffPrepaid("Basic Tariff", 50.0f, 100, 5.0f);
        tariff2 = new TariffPrepaid("Premium Tariff", 100.0f, 200, 10.0f);
    }

    /**
     * Тест для перевірки додавання тарифу до мережі.
     * Перевіряється, чи тариф успішно додається до мережі.
     */
    @Test
    void testAddTariff() {
        service.addTariff(tariff1);
        assertTrue(network.getTariffs().contains(tariff1), "Тариф має бути доданий до мережі");
    }

    /**
     * Тест для перевірки видалення тарифу з мережі.
     * Перевіряється, чи тариф успішно видаляється з мережі.
     */
    @Test
    void testRemoveTariff() {
        service.addTariff(tariff1);
        service.removeTariff(tariff1);
        assertFalse(network.getTariffs().contains(tariff1), "Тариф має бути видалений з мережі");
    }

    /**
     * Тест для перевірки редагування тарифу.
     * Перевіряється, чи старий тариф замінюється новим.
     */
    @Test
    void testEditTariff() {
        service.addTariff(tariff1);
        service.editTariff(tariff1, tariff2);
        assertTrue(network.getTariffs().contains(tariff2), "Має бути доданий новий тариф");
        assertFalse(network.getTariffs().contains(tariff1), "Старий тариф має бути видалений");
    }

    /**
     * Тест для перевірки перегляду всіх тарифів.
     * Перевіряється, чи відображаються всі тарифи, що були додані.
     */
    @Test
    void testViewTariffs() {
        service.addTariff(tariff1);
        service.addTariff(tariff2);

        List<TariffPrepaid> tariffs = service.viewTariffs();
        assertEquals(2, tariffs.size(), "Має бути два тарифи");
        assertTrue(tariffs.contains(tariff1), "Перевірка наявності першого тарифу");
        assertTrue(tariffs.contains(tariff2), "Перевірка наявності другого тарифу");
    }

    /**
     * Тест для перевірки пошуку тарифу за назвою.
     * Перевіряється, чи тариф можна знайти за його ім'ям.
     */
    @Test
    void testSearchTariffByName() {
        service.addTariff(tariff1);

        TariffPrepaid result = service.searchTariffByName("Basic Tariff");
        assertEquals(tariff1, result, "Тариф має бути знайдений за ім'ям");
    }

    /**
     * Тест для перевірки пошуку тарифів у заданому діапазоні.
     * Перевіряється, чи можна знайти тарифи, що знаходяться в межах вказаного діапазону.
     */
    @Test
    void testSearchTariffsByRange() {
        service.addTariff(tariff1);
        service.addTariff(tariff2);

        TariffPrepaid result = service.searchTariffsByRange(40.0f, 60.0f);
        assertEquals(tariff1, result, "Тариф з діапазону 40-60 має бути знайдений");
    }

    /**
     * Тест для перевірки підрахунку загальної кількості клієнтів.
     * Перевіряється, чи правильно підраховується загальна кількість абонентів у мережі.
     */
    @Test
    void testCalculateTotalClients() {
        tariff1.addSubscriber();
        tariff2.addSubscriber();
        tariff2.addSubscriber();

        service.addTariff(tariff1);
        service.addTariff(tariff2);

        int totalClients = service.calculateTotalClients();
        assertEquals(3, totalClients, "Загальна кількість клієнтів має бути 3");
    }

    /**
     * Тест для перевірки сортування тарифів за платою.
     * Перевіряється, чи тарифи сортуються за вартістю (від меншої до більшої).
     */
    @Test
    void testSortTariffsByFee() {
        service.addTariff(tariff2);
        service.addTariff(tariff1);

        service.sortTariffsByFee();
        List<TariffPrepaid> sortedTariffs = service.viewTariffs();

        assertEquals(tariff1, sortedTariffs.get(0), "Тариф з меншою платою має бути першим");
        assertEquals(tariff2, sortedTariffs.get(1), "Тариф з більшою платою має бути другим");
    }
}