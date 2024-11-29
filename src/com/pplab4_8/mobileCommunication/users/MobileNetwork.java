package com.pplab4_8.mobileCommunication.users;

import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє мобільну мережу з тарифами.
 * Містить основні методи для керування тарифами в мережі.
 */
public class MobileNetwork {
    private String name; // Назва мобільної мережі
    private ArrayList<TariffPrepaid> tariffs; // Список тарифів, доступних в мережі

    /**
     * Конструктор для ініціалізації мобільної мережі з заданими параметрами.
     *
     * @param name назва мережі
     * @param tariffs список тарифів, доступних в мережі
     */
    public MobileNetwork(String name, ArrayList<TariffPrepaid> tariffs) {
        this.name = name; // Ініціалізація назви мережі
        this.tariffs = tariffs != null ? tariffs : new ArrayList<>(); // Якщо тарифів не передано, ініціалізуємо порожній список
    }

    /**
     * Отримує назву мережі.
     *
     * @return назва мережі
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву мережі.
     *
     * @param name нова назва мережі
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Отримує список тарифів, доступних у мережі.
     *
     * @return список тарифів
     */
    public List<TariffPrepaid> getTariffs() {
        return tariffs;
    }

    /**
     * Встановлює новий список тарифів для мережі.
     *
     * @param tariffs новий список тарифів
     */
    public void setTariffs(ArrayList<TariffPrepaid> tariffs) {
        this.tariffs = tariffs;
    }

    /**
     * Шукає тариф за його назвою в списку тарифів.
     *
     * @param name назва тарифу
     * @return тариф з відповідною назвою або null, якщо тариф не знайдений
     */
    public TariffPrepaid getTariffByName(String name) {
        for (TariffPrepaid tariff : tariffs) {
            if (tariff.getName().equals(name)) {
                return tariff; // Повертаємо тариф, якщо знайшли відповідність
            }
        }
        return null; // Повертаємо null, якщо тариф не знайдений
    }

    /**
     * Додає новий тариф до списку тарифів мережі.
     *
     * @param tariff тариф, який додається до мережі
     */
    public void addTariff(TariffPrepaid tariff) {
        tariffs.add(tariff); // Додаємо тариф у список
    }

    /**
     * Видаляє тариф з мережі.
     *
     * @param tariff тариф, який потрібно видалити
     */
    public void removeTariff(TariffPrepaid tariff) {
        tariffs.remove(tariff); // Видаляємо тариф із списку
    }
}