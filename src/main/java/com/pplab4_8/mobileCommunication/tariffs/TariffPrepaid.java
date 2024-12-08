package com.pplab4_8.mobileCommunication.tariffs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TariffPrepaid {

    private static final Logger logger = LogManager.getLogger(TariffPrepaid.class);

    private String name;
    private float monthlyFee;
    private int callMinutesCount;
    private float internetGigabytesCount;
    private int subscribersCount = 0;

    public TariffPrepaid(String name, float monthlyFee, int callMinutesCount, float internetGigabytesCount) {
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.callMinutesCount = callMinutesCount;
        this.internetGigabytesCount = internetGigabytesCount;
        logger.info("Створено новий тариф передплати: ім'я = '{}', місячна плата = {}, хвилини дзвінків = {}, гігабайти інтернету = {}",
                name, monthlyFee, callMinutesCount, internetGigabytesCount);
    }

    public String getName() {
        logger.debug("Отримано ім'я тарифу: '{}'", name);
        return name;
    }

    public float getMonthlyFee() {
        logger.debug("Отримано місячну плату тарифу '{}': {}", name, monthlyFee);
        return monthlyFee;
    }

    public int getCallMinutesCount() {
        logger.debug("Отримано кількість хвилин дзвінків тарифу '{}': {}", name, callMinutesCount);
        return callMinutesCount;
    }

    public float getInternetGigabytesCount() {
        logger.debug("Отримано кількість гігабайт інтернету тарифу '{}': {}", name, internetGigabytesCount);
        return internetGigabytesCount;
    }

    public int getSubscribersCount() {
        logger.debug("Отримано кількість підписників тарифу '{}': {}", name, subscribersCount);
        return subscribersCount;
    }

    public void setName(String name) {
        logger.info("Змінено ім'я тарифу з '{}' на '{}'", this.name, name);
        this.name = name;
    }

    public void setMonthlyFee(float monthlyFee) {
        logger.info("Змінено місячну плату тарифу '{}': з {} на {}", name, this.monthlyFee, monthlyFee);
        this.monthlyFee = monthlyFee;
    }

    public void setCallMinutesCount(int callMinutesCount) {
        logger.info("Змінено кількість хвилин дзвінків тарифу '{}': з {} на {}", name, this.callMinutesCount, callMinutesCount);
        this.callMinutesCount = callMinutesCount;
    }

    public void setInternetGigabytesCount(float internetGigabytesCount) {
        logger.info("Змінено кількість гігабайт інтернету тарифу '{}': з {} на {}", name, this.internetGigabytesCount, internetGigabytesCount);
        this.internetGigabytesCount = internetGigabytesCount;
    }

    public void addSubscriber() {
        this.subscribersCount++;
        logger.info("До тарифу '{}' додано нового абонента. Поточна кількість абонентів: {}", name, subscribersCount);
    }

    public void subSubscriber() {
        if (subscribersCount > 0) {
            this.subscribersCount--;
            logger.info("Від тарифу '{}' від'єднався абонент. Поточна кількість абонентів: {}", name, subscribersCount);
        } else {
            logger.warn("Спроба зменшити кількість абонентів тарифу '{}', але кількість абонентів вже 0", name);
        }
    }

    @Override
    public String toString() {
        logger.debug("Виклик toString для тарифу передплати'{}'", name);
        return String.format(
                "Тариф: %s, Місячна плата: %.2f, Хвилини дзвінків: %d, Гігабайти інтернету: %.2f, Кількість клієнтів: %d",
                name, monthlyFee, callMinutesCount, internetGigabytesCount, subscribersCount
        );
    }
}