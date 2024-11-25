package com.pplab4_8.moblileCommunication.users;

import com.pplab4_8.moblileCommunication.tariffs.TariffPrepaid;

/**
 * Клас MobileSubscriber представляє абонента мобільного зв'язку.
 * Він зберігає інформацію про абонента, його тариф та мобільного оператора.
 */
public class MobileSubscriber {
    private String name;
    private int age;
    private String phoneNumber;
    private MobileNetworkService service;
    private TariffPrepaid tariff;

    /**
     * Конструктор для створення абонента з ім'ям та віком.
     *
     * @param name Ім'я абонента.
     * @param age Вік абонента.
     */
    public MobileSubscriber(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Конструктор для створення абонента з повними даними, включаючи номер телефону, мобільного оператора і тариф.
     * Після створення абонента, його тариф додається до кількості клієнтів.
     *
     * @param name Ім'я абонента.
     * @param age Вік абонента.
     * @param phoneNumber Номер телефону абонента.
     * @param service Мобільний оператор абонента.
     * @param tariff Тариф абонента.
     */
    public MobileSubscriber(String name, int age, String phoneNumber, MobileNetworkService service, TariffPrepaid tariff) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.service = service;
        this.tariff = tariff;
        this.tariff.addSubscriber(); // Додаємо абонента до кількості клієнтів тарифу
    }

    /**
     * Отримує ім'я абонента.
     *
     * @return Ім'я абонента.
     */
    public String getName() {
        return name;
    }

    /**
     * Отримує вік абонента.
     *
     * @return Вік абонента.
     */
    public int getAge() {
        return age;
    }

    /**
     * Отримує номер телефону абонента.
     *
     * @return Номер телефону абонента.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Отримує мобільного оператора абонента.
     *
     * @return Мобільний оператор.
     */
    public MobileNetworkService getService() {
        return service;
    }

    /**
     * Отримує тариф абонента.
     *
     * @return Тариф абонента.
     */
    public TariffPrepaid getTariff() {
        return tariff;
    }

    /**
     * Встановлює нове ім'я абонента.
     *
     * @param name Нова назва абонента.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Встановлює новий вік абонента.
     *
     * @param age Новий вік абонента.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Встановлює новий номер телефону абонента.
     *
     * @param phoneNumber Новий номер телефону абонента.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Встановлює нового мобільного оператора для абонента.
     *
     * @param service Новий мобільний оператор абонента.
     */
    public void setService(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Змінює тариф абонента на новий, знайдений через пошук у мобільного оператора.
     * Після зміни тарифу абонент додається до кількості клієнтів нового тарифу.
     */
    public void setTariff() {
        if (service == null) {
            System.out.println("Абонент не прив'язаний до мобільного оператора. Змініть послугу.");
            return;
        }

        TariffPrepaid foundTariff = service.searchTariff(); // Викликаємо функцію пошуку тарифу
        if (foundTariff == null) {
            System.out.println("Тариф не знайдено.");
            return;
        }

        if (this.tariff != null) {
            this.tariff.subSubscriber(); // Зменшуємо кількість клієнтів старого тарифу
        }

        this.tariff = foundTariff; // Встановлюємо новий тариф
        this.tariff.addSubscriber(); // Додаємо абонента до кількості клієнтів нового тарифу
        System.out.println("Тариф успішно змінено на: " + this.tariff.getName());
    }

    /**
     * Переглядає та виводить інформацію про особистий тариф абонента.
     * Якщо тариф не встановлений, виводиться повідомлення.
     */
    public void viewPersonalTariff() {
        if (tariff == null) {
            System.out.println("Тариф не встановлено.");
        } else {
            System.out.println("Ваш тариф: ");
            System.out.println(tariff);
        }
    }
}