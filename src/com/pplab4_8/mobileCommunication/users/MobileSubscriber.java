package com.pplab4_8.mobileCommunication.users;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;

/**
 * Клас, що представляє мобільного абонента.
 * Містить інформацію про абонента та його тариф, а також методи для зміни тарифу та перегляду особистої інформації.
 */
public class MobileSubscriber {
    private String name; // Ім'я абонента
    private int age; // Вік абонента
    private String phoneNumber; // Номер телефону абонента
    private MobileNetworkService service; // Сервіс мобільного оператора, який обслуговує абонента
    private TariffPrepaid tariff; // Тариф, підключений абоненту

    /**
     * Конструктор для ініціалізації абонента без номера телефону та тарифу.
     *
     * @param name ім'я абонента
     * @param age вік абонента
     */
    public MobileSubscriber(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Конструктор для ініціалізації абонента з номером телефону, мобільною мережею та тарифом.
     *
     * @param name ім'я абонента
     * @param age вік абонента
     * @param phoneNumber номер телефону абонента
     * @param network мережа, яку обслуговує абонент
     * @param tariff тариф, який підключено абоненту
     */
    public MobileSubscriber(String name, int age, String phoneNumber, MobileNetwork network, TariffPrepaid tariff) {
        this.name = name; // Ініціалізація імені
        this.age = age; // Ініціалізація віку
        this.phoneNumber = phoneNumber; // Ініціалізація номеру телефону
        this.service = service; // Встановлення мобільного оператора
        this.tariff = tariff; // Встановлення тарифу
        this.tariff.addSubscriber(); // Додавання абонента до тарифу
    }

    /**
     * Отримує ім'я абонента.
     *
     * @return ім'я абонента
     */
    public String getName() {
        return name;
    }

    /**
     * Отримує вік абонента.
     *
     * @return вік абонента
     */
    public int getAge() {
        return age;
    }

    /**
     * Отримує номер телефону абонента.
     *
     * @return номер телефону абонента
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Отримує сервіс мобільного оператора, який обслуговує абонента.
     *
     * @return мобільний сервіс
     */
    public MobileNetworkService getNetworkService() {
        return service;
    }

    /**
     * Отримує тариф, підключений абоненту.
     *
     * @return тариф абонента
     */
    public TariffPrepaid getTariff() {
        return tariff;
    }

    /**
     * Встановлює нове ім'я абонента.
     *
     * @param name нове ім'я абонента
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Встановлює новий вік абонента.
     *
     * @param age новий вік абонента
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Встановлює новий номер телефону абонента.
     *
     * @param phoneNumber новий номер телефону абонента
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Встановлює новий мобільний сервіс для абонента.
     *
     * @param service новий мобільний сервіс
     */
    public void setNetworkService(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Встановлює новий тариф для абонента.
     * При цьому кількість підключених абонентів для старого тарифу зменшується, а для нового тарифу збільшується.
     *
     * @param newTariff новий тариф
     */
    public void setTariff(TariffPrepaid newTariff) {
        if (newTariff == null) { // Перевірка на null
            System.out.println("Новий тариф не може бути null.");
            return;
        }

        if (this.tariff != null) {
            this.tariff.subSubscriber(); // Зменшуємо кількість підключених абонентів у старому тарифі
        }

        this.tariff = newTariff; // Встановлюємо новий тариф
        this.tariff.addSubscriber(); // Збільшуємо кількість підключених абонентів для нового тарифу

        System.out.println("Новий тариф успішно встановлено: " + newTariff.getName());
    }

    /**
     * Переглядає особистий тариф абонента.
     * Виводить інформацію про тариф, якщо він встановлений, або повідомлення про відсутність тарифу.
     */
    public void viewPersonalTariff() {
        if (tariff == null) { // Якщо тариф не встановлено
            System.out.println("Тариф не встановлено.");
        } else {
            System.out.println("Ваш тариф: ");
            System.out.println(tariff); // Виводимо інформацію про тариф
        }
    }
}