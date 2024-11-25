package com.pplab4_8.moblileCommunication.users;

import com.pplab4_8.moblileCommunication.tariffs.TariffPrepaid;

import java.util.List;

/**
 * Клас MobileNetworkService представляє мобільного оператора та його тарифи.
 * Містить методи для маніпулювання тарифами та отримання інформації.
 */
public class MobileNetworkService {

    // Назва мобільного оператора
    private String name;

    // Список тарифів, доступних для цього оператора
    private List<TariffPrepaid> tariffs;

    /**
     * Конструктор для ініціалізації мобільного оператора з ім'ям.
     * @param name ім'я мобільного оператора
     */
    public MobileNetworkService(String name) {
        this.name = name;
    }

    /**
     * Конструктор для ініціалізації мобільного оператора з ім'ям та списком тарифів.
     * @param name ім'я мобільного оператора
     * @param tariffs список тарифів, доступних у цьому оператора
     */
    public MobileNetworkService(String name, List<TariffPrepaid> tariffs) {
        this.name = name;
        this.tariffs = tariffs;
    }

    /**
     * Отримати ім'я мобільного оператора.
     * @return ім'я мобільного оператора
     */
    public String getName() {
        return name;
    }

    /**
     * Знайти тариф за його іменем серед доступних тарифів.
     * @param name ім'я тарифу
     * @return тариф, якщо знайдений, або null, якщо не знайдений
     */
    public TariffPrepaid getTariff(String name) {
        for (TariffPrepaid tariff : tariffs) {
            if (tariff.getName().equals(name)) {
                return tariff;
            }
        }
        return null;  // Якщо тариф не знайдений
    }

    /**
     * Встановити нове ім'я для мобільного оператора.
     * @param name нове ім'я мобільного оператора
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Отримати список тарифів цього оператора.
     * @return список тарифів
     */
    public List<TariffPrepaid> getTariffs() {
        return tariffs;
    }

    /**
     * Встановити новий список тарифів для мобільного оператора.
     * @param tariffs новий список тарифів
     */
    public void setTariffs(List<TariffPrepaid> tariffs) {
        this.tariffs = tariffs;
    }

    /**
     * Додати новий тариф до списку тарифів оператора.
     * (Метод поки що виводить лише повідомлення, без реальної реалізації.)
     */
    public void addTariff() {
        System.out.println("Add Tariff");
    }

    /**
     * Редагувати існуючий тариф.
     * (Метод поки що виводить лише повідомлення, без реальної реалізації.)
     */
    public void editTariff() {
        System.out.println("Edit Tariff");
    }

    /**
     * Видалити тариф з списку.
     * (Метод поки що виводить лише повідомлення, без реальної реалізації.)
     */
    public void removeTariff() {
        System.out.println("Remove Tariff");
    }

    /**
     * Переглянути всі доступні тарифи.
     * (Метод поки що виводить лише повідомлення, без реальної реалізації.)
     */
    public void viewTariffs() {
        System.out.println("View Tariffs");
    }

    /**
     * Обчислити загальну кількість клієнтів мобільного оператора.
     * (Метод поки що виводить лише повідомлення, без реальної реалізації.)
     */
    public void calculateTotalClients() {
        System.out.println("Calculate Total Clients");
    }

    /**
     * Відсортувати тарифи за їх вартості.
     * (Метод поки що виводить лише повідомлення, без реальної реалізації.)
     */
    public void sortTariffsByFee() {
        System.out.println("Sort Tariffs By Fee");
    }

    /**
     * Шукати тариф за певними параметрами.
     * (Метод поки що виводить лише повідомлення, без реальної реалізації.)
     * @return null, оскільки метод не реалізовано повністю
     */
    public TariffPrepaid searchTariff() {
        System.out.println("Search Tariff");
        return null;  // Повертається null, оскільки метод не реалізовано повністю
    }
}