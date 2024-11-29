package com.pplab4_8.mobileCommunication.service;

import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;

import java.util.Comparator;
import java.util.List;

/**
 * Клас, який надає послуги для керування тарифами в мережі мобільного оператора.
 * Здійснює додавання, редагування, видалення тарифів, а також перегляд та пошук тарифів за різними критеріями.
 */
public class MobileNetworkService {
    private MobileNetwork network; // Мережа мобільного оператора

    /**
     * Конструктор для ініціалізації сервісу з конкретною мережею.
     *
     * @param network мережа мобільного оператора
     */
    public MobileNetworkService(MobileNetwork network) {
        this.network = network;
    }

    /**
     * Метод для отримання мережі мобільного оператора.
     *
     * @return об'єкт мережі мобільного оператора
     */
    public MobileNetwork getNetwork() {
        return network;
    }

    /**
     * Додає новий тариф у мережу.
     *
     * @param tariff тариф для додавання
     */
    public void addTariff(TariffPrepaid tariff) {
        network.addTariff(tariff); // Додаємо тариф в мережу
    }

    /**
     * Оновлює тариф в мережі: видаляє старий тариф і додає оновлений.
     *
     * @param oldTariff старий тариф
     * @param updatedTariff оновлений тариф
     */
    public void editTariff(TariffPrepaid oldTariff, TariffPrepaid updatedTariff) {
        network.removeTariff(oldTariff); // Видаляємо старий тариф
        network.addTariff(updatedTariff); // Додаємо новий тариф
    }

    /**
     * Видаляє тариф з мережі.
     *
     * @param tariff тариф, який потрібно видалити
     */
    public void removeTariff(TariffPrepaid tariff) {
        network.removeTariff(tariff); // Видаляємо тариф з мережі
    }

    /**
     * Повертає список всіх тарифів в мережі.
     *
     * @return список тарифів
     */
    public List<TariffPrepaid> viewTariffs() {
        return network.getTariffs(); // Отримуємо список тарифів з мережі
    }

    /**
     * Шукає тариф за його назвою.
     *
     * @param name назва тарифу
     * @return тариф з заданою назвою, або null, якщо не знайдено
     */
    public TariffPrepaid searchTariffByName(String name) {
        return network.getTariffByName(name); // Шукаємо тариф за назвою
    }

    /**
     * Шукає тариф у заданому діапазоні абонплати.
     *
     * @param minFee мінімальна абонплата
     * @param maxFee максимальна абонплата
     * @return тариф, який потрапляє у заданий діапазон, або null, якщо не знайдено
     */
    public TariffPrepaid searchTariffsByRange(float minFee, float maxFee) {
        return network.getTariffs().stream() // Фільтруємо тарифи в межах діапазону абонплати
                .filter(tariff -> tariff.getMonthlyFee() >= minFee && tariff.getMonthlyFee() <= maxFee)
                .findFirst() // Повертаємо перший знайдений тариф
                .orElse(null); // Якщо не знайдено, повертаємо null
    }

    /**
     * Підраховує загальну кількість клієнтів по всіх тарифах мережі.
     *
     * @return загальна кількість клієнтів
     */
    public int calculateTotalClients() {
        return network.getTariffs().stream() // Підраховуємо кількість абонентів у кожному тарифі
                .mapToInt(TariffPrepaid::getSubscribersCount) // Отримуємо кількість абонентів для кожного тарифу
                .sum(); // Підсумовуємо загальну кількість абонентів
    }

    /**
     * Сортує тарифи в мережі за абонплатою.
     */
    public void sortTariffsByFee() {
        network.getTariffs().sort(Comparator.comparingDouble(TariffPrepaid::getMonthlyFee)); // Сортуємо тарифи за абонплатою в порядку зростання
    }
}