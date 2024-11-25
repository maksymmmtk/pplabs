package com.pplab4_8.moblileCommunication.users;

import com.pplab4_8.moblileCommunication.tariffs.TariffPrepaid;

public class MobileSubscriber {
    private String name;
    private int age;
    private String phoneNumber;
    private TariffPrepaid tariff;

    public MobileSubscriber(String name, int age) {
        this.name = name;
        this.age = age;
    }
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.tariff = tariff;
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
    }

    public void setTariff() {
    }

    public void viewPersonalTariff() {
    }
}