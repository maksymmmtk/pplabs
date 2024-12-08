package com.pplab4_8.mobileCommunication.users;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MobileSubscriber {

    private static final Logger logger = LogManager.getLogger(MobileSubscriber.class);

    private String name;
    private int age;
    private String phoneNumber;
    private MobileNetworkService service;
    private TariffPrepaid tariff;
    private boolean silentMode = false;

    public MobileSubscriber(String name, int age) {
        this.name = name;
        this.age = age;
        logger.info("Створено нового абонента: ім'я = '{}', вік = {}", name, age);
    }

    public MobileSubscriber(String name, int age, String phoneNumber, MobileNetworkService service, TariffPrepaid tariff) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.service = service;
        this.tariff = tariff;
        if (this.tariff != null) {
            this.tariff.addSubscriber();
        }
        logger.info("Створено нового абонента: ім'я = '{}', вік = {}, номер = '{}', тариф = '{}'", name, age, phoneNumber, tariff != null ? tariff.getName() : "не встановлено");
    }

    public String getName() {
        logger.debug("Отримано ім'я абонента: '{}'", name);
        return name;
    }

    public int getAge() {
        logger.debug("Отримано вік абонента '{}': {}", name, age);
        return age;
    }

    public String getPhoneNumber() {
        logger.debug("Отримано номер телефону абонента '{}': {}", name, phoneNumber);
        return phoneNumber;
    }

    public MobileNetworkService getNetworkService() {
        logger.debug("Отримано сервіс мережі для абонента '{}'", name);
        return service;
    }

    public TariffPrepaid getTariff() {
        logger.debug("Отримано тариф абонента '{}': {}", name, tariff != null ? tariff.getName() : "не встановлено");
        return tariff;
    }

    public void setName(String name) {
        logger.info("Змінено ім'я абонента з '{}' на '{}'", this.name, name);
        this.name = name;
    }

    public void setAge(int age) {
        logger.info("Змінено вік абонента '{}': з {} на {}", this.name, this.age, age);
        this.age = age;
    }

    public void setPhoneNumber(String phoneNumber) {
        logger.info("Змінено номер телефону абонента '{}': з '{}' на '{}'", this.name, this.phoneNumber, phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public void setNetworkService(MobileNetworkService service) {
        logger.info("Змінено мережевий сервіс абонента '{}': нова мережа = '{}'", this.name, service != null ? service.getNetwork().getName() : "null");
        this.service = service;
    }

    public void setSilentMode(boolean silentMode) {
        logger.info("Режим тихого режиму для абонента '{}' змінено на '{}'", name, silentMode);
        this.silentMode = silentMode;
    }

    public void setTariff(TariffPrepaid newTariff) {
        if (newTariff == null) {
            if (!silentMode) {
                System.out.println("Новий тариф не може бути null.");
            }
            logger.warn("Спроба встановити тариф 'null' для абонента '{}'", name);
            this.tariff = null;
            return;
        }

        if (this.tariff != null) {
            logger.info("Абонент '{}' скасував тариф '{}'", name, this.tariff.getName());
            this.tariff.subSubscriber();
        }

        this.tariff = newTariff;
        this.tariff.addSubscriber();
        logger.info("Абонент '{}' змінив тариф на '{}'", name, newTariff.getName());

        if (!silentMode) {
            System.out.println("Новий тариф успішно встановлено: " + newTariff.getName());
        }
    }

    public void viewPersonalTariff() {
        if (tariff == null) {
            logger.info("Абонент '{}' переглянув тариф: тариф не встановлено.", name);
            System.out.println("Тариф не встановлено.");
        } else {
            logger.info("Абонент '{}' переглянув свій тариф: '{}'", name, tariff.getName());
            System.out.print("Ваш тариф:\n");
            System.out.println(tariff);
        }
    }
}