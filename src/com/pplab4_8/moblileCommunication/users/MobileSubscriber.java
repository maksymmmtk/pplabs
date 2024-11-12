package com.pplab4_8.moblileCommunication.users;

import com.pplab4_8.moblileCommunication.tariffs.TariffPrepaid;

public class MobileSubscriber {
    private String name;
    private int age;
    private String phoneNumber;
    private MobileNetworkOperator operator;
    private TariffPrepaid tariff;

    public MobileSubscriber(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public MobileSubscriber(String name, int age, String phoneNumber, MobileNetworkOperator operator, TariffPrepaid tariff) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.operator = operator;
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
    public MobileNetworkOperator getOperator() {
        return operator;
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
    public void setOperator(MobileNetworkOperator operator) {
        this.operator = operator;
    }

    public void setTariff() {
        this.tariff.addSubscriber();
    }

    public void viewPersonalTariff() {
        System.out.println(tariff.toString());
    }
}