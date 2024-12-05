package com.pplab4_8.mobileCommunication.service;

import com.pplab4_8.mobileCommunication.tariffs.*;
import com.pplab4_8.mobileCommunication.users.MobileNetwork;

import java.util.Comparator;
import java.util.List;

/**
 * Клас MobileNetworkService представляє сервіс для управління тарифами та користувачами мобільної мережі.
 */
public class MobileNetworkService {
    private MobileNetwork network;

    /**
     * Конструктор MobileNetworkService.
     *
     * @param network об'єкт мобільної мережі {@code MobileNetwork}, яким управляє сервіс.
     */
    public MobileNetworkService(MobileNetwork network) {
        this.network = network;
    }

    /**
     * Повертає мобільну мережу, яка керується цим сервісом.
     *
     * @return об'єкт {@code MobileNetwork}.
     */
    public MobileNetwork getNetwork() {
        return network;
    }

    /**
     * Додає новий тариф до мобільної мережі.
     *
     * @param tariff тариф, який необхідно додати.
     */
    public void addTariff(TariffPrepaid tariff) {
        network.addTariff(tariff);
    }

    /**
     * Редагує існуючий тариф, замінюючи його новою версією.
     *
     * @param oldTariff    існуючий тариф, який необхідно замінити.
     * @param updatedTariff новий тариф, який замінить старий.
     */
    public void editTariff(TariffPrepaid oldTariff, TariffPrepaid updatedTariff) {
        network.removeTariff(oldTariff);
        network.addTariff(updatedTariff);
    }

    /**
     * Видаляє тариф з мобільної мережі.
     *
     * @param tariff тариф, який необхідно видалити.
     */
    public void removeTariff(TariffPrepaid tariff) {
        network.removeTariff(tariff);
    }

    /**
     * Повертає список всіх тарифів у мобільній мережі.
     *
     * @return список тарифів {@code List<TariffPrepaid>}.
     */
    public List<TariffPrepaid> viewTariffs() {
        return network.getTariffs();
    }

    /**
     * Пошук тарифу за його назвою.
     *
     * @param name назва тарифу для пошуку.
     * @return знайдений тариф або {@code null}, якщо тариф не знайдено.
     */
    public TariffPrepaid searchTariffByName(String name) {
        return network.getTariffByName(name);
    }

    /**
     * Пошук тарифу в діапазоні абонплати.
     *
     * @param minFee мінімальна абонплата.
     * @param maxFee максимальна абонплата.
     * @return знайдений тариф або {@code null}, якщо тариф не знайдено.
     */
    public TariffPrepaid searchTariffsByRange(float minFee, float maxFee) {
        return network.getTariffs().stream()
                .filter(tariff -> tariff.getMonthlyFee() >= minFee && tariff.getMonthlyFee() <= maxFee)
                .findFirst()
                .orElse(null);
    }

    /**
     * Розраховує загальну кількість клієнтів у мобільній мережі.
     *
     * @return кількість клієнтів.
     */
    public int calculateTotalClients() {
        return network.getTariffs().stream().mapToInt(TariffPrepaid::getSubscribersCount).sum();
    }

    /**
     * Сортує тарифи у мобільній мережі за абонплатою (у порядку зростання).
     */
    public void sortTariffsByFee() {
        network.getTariffs().sort(Comparator.comparingDouble(TariffPrepaid::getMonthlyFee));
    }
}