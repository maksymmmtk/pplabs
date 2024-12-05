package com.pplab4_8.mobileCommunication.users;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;

public class MobileSubscriber {
    private String name;
    private int age;
    private String phoneNumber;
    private MobileNetworkService service;
    private TariffPrepaid tariff;
    private boolean silentMode = false;

    public MobileSubscriber(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public MobileSubscriber(String name, int age, String phoneNumber, MobileNetworkService network, TariffPrepaid tariff) {
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

    public MobileNetworkService getNetworkService() {
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

    public void setNetworkService(MobileNetworkService service) {
        this.service = service;
    }

    public void setSilentMode(boolean silentMode) {
        this.silentMode = silentMode;
    }

    public void setTariff(TariffPrepaid newTariff) {
        if (newTariff == null) {
            if (!silentMode) {
                System.out.println("Новий тариф не може бути null.");
            }
            this.tariff = null;
            return;
        }

        if (this.tariff != null) {
            this.tariff.subSubscriber();
        }

        this.tariff = newTariff;
        this.tariff.addSubscriber();

        if (!silentMode) {
            System.out.println("Новий тариф успішно встановлено: " + newTariff.getName());
        }
    }

    public void viewPersonalTariff() {
        if (tariff == null) {
            System.out.println("Тариф не встановлено.");
        } else {
            System.out.print("Ваш тариф:\n");
            System.out.println(tariff);
        }
    }
}