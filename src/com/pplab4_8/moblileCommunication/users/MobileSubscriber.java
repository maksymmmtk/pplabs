package com.pplab4_8.moblileCommunication.users;

import com.pplab4_8.moblileCommunication.tariffs.TariffPrepaid;

/**
 * Клас MobileSubscriber представляє абонента мобільного оператора.
 * Містить дані абонента, а також його тариф та оператора.
 */
public class MobileSubscriber {

    // Ім'я абонента
    private String name;

    // Вік абонента
    private int age;

    // Номер телефону абонента
    private String phoneNumber;

    // Мобільний оператор, який обслуговує абонента
    private MobileNetworkService service;

    // Тариф, підключений до абонента
    private TariffPrepaid tariff;

    /**
     * Конструктор для створення абонента з ім'ям та віком.
     * @param name ім'я абонента
     * @param age вік абонента
     */
    public MobileSubscriber(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Конструктор для створення абонента з усіма даними: ім'ям, віком, номером телефону,
     * оператором і тарифом. Також додається абонент до тарифу.
     * @param name ім'я абонента
     * @param age вік абонента
     * @param phoneNumber номер телефону абонента
     * @param service мобільний оператор
     * @param tariff тариф абонента
     */
    public MobileSubscriber(String name, int age, String phoneNumber, MobileNetworkService service, TariffPrepaid tariff) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.service = service;
        this.tariff = tariff;
        this.tariff.addSubscriber(); // Додаємо абонента до тарифу
    }

    /**
     * Отримати ім'я абонента.
     * @return ім'я абонента
     */
    public String getName() {
        return name;
    }

    /**
     * Отримати вік абонента.
     * @return вік абонента
     */
    public int getAge() {
        return age;
    }

    /**
     * Отримати номер телефону абонента.
     * @return номер телефону абонента
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Отримати мобільного оператора, який обслуговує абонента.
     * @return мобільний оператор
     */
    public MobileNetworkService getService() {
        return service;
    }

    /**
     * Отримати тариф абонента.
     * @return тариф абонента
     */
    public TariffPrepaid getTariff() {
        return tariff;
    }

    /**
     * Встановити ім'я абонента.
     * @param name нове ім'я абонента
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Встановити вік абонента.
     * @param age новий вік абонента
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Встановити номер телефону абонента.
     * @param phoneNumber новий номер телефону абонента
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Встановити мобільного оператора для абонента.
     * @param service новий мобільний оператор
     */
    public void setService(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Встановити тариф для абонента.
     * (Метод поки що лише виводить повідомлення, без реальної реалізації.)
     */
    public void setTariff() {
        System.out.println("Set Tariff");
    }

    /**
     * Переглянути особистий тариф абонента.
     * (Метод поки що лише виводить повідомлення, без реальної реалізації.)
     */
    public void viewPersonalTariff() {
        System.out.println("View Personal Tariff");
    }
}