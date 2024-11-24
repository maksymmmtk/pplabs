package com.pplab4_8.moblileCommunication.users;

import com.pplab4_8.moblileCommunication.tariffs.TariffPrepaid;

public class MobileSubscriber {
    private String name;
    private int age;
    private String phoneNumber;
    private MobileNetworkService service;
    private TariffPrepaid tariff;

    public MobileSubscriber(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public MobileSubscriber(String name, int age, String phoneNumber, MobileNetworkService service, TariffPrepaid tariff) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.service = service;
        this.tariff = tariff;
        this.tariff.addSubscriber();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public MobileNetworkService getService() {
        return service;
    }

    public TariffPrepaid getTariff() {
        return tariff;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setService(MobileNetworkService service) {
        this.service = service;
    }

    public void setTariff() {
        // Реалізація
        if (service == null) {
            System.out.println("Абонент не прив'язаний до мобільного оператора. Змініть послугу.");
            return;
        }

        TariffPrepaid foundTariff = service.searchTariff(); // Викликаємо функцію пошуку тарифу
        if (foundTariff == null) {
            System.out.println("Тариф не знайдено.");
            return;
        }

        // Якщо у абонента був тариф, зменшити кількість клієнтів у старому тарифі
        if (this.tariff != null) {
            this.tariff.subSubscriber();
        }

        // Встановити новий тариф
        this.tariff = foundTariff;
        this.tariff.addSubscriber(); // Додаємо до нового тарифу
        System.out.println("Тариф успішно змінено на: " + this.tariff.getName());
    }

    public void viewPersonalTariff() {
        // Реалізація
        if (tariff == null) {
            System.out.println("Тариф не встановлено.");
        } else {
            System.out.println("Ваш тариф: ");
            System.out.println(tariff);
        }
    }
}