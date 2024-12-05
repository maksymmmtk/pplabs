package com.pplab4_8.mobileCommunication.tariffs;

/**
 * Клас TariffPrepaid представляє тариф на передоплату, який включає базову інформацію про тариф,
 * таку як назва, місячна плата, кількість хвилин дзвінків та обсяг інтернет-трафіку.
 * Цей клас також дозволяє відслідковувати кількість підключених абонентів.
 */
public class TariffPrepaid {
    private String name;  // Назва тарифу
    private float monthlyFee;  // Місячна абонплата
    private int callMinutesCount;  // Кількість хвилин дзвінків
    private float internetGigabytesCount;  // Кількість гігабайтів інтернету
    private int subscribersCount = 0;  // Кількість підключених абонентів (за замовчуванням 0)

    /**
     * Конструктор для створення тарифу на передоплату.
     *
     * @param name               назва тарифу.
     * @param monthlyFee         місячна абонплата.
     * @param callMinutesCount   кількість хвилин дзвінків.
     * @param internetGigabytesCount кількість гігабайтів інтернет-трафіку.
     */
    public TariffPrepaid(String name, float monthlyFee, int callMinutesCount, float internetGigabytesCount) {
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.callMinutesCount = callMinutesCount;
        this.internetGigabytesCount = internetGigabytesCount;
    }

    /**
     * Повертає назву тарифу.
     *
     * @return назва тарифу.
     */
    public String getName() {
        return name;
    }

    /**
     * Повертає щомісячну абонплату.
     *
     * @return щомісячна абонплата.
     */
    public float getMonthlyFee() {
        return monthlyFee;
    }

    /**
     * Повертає кількість хвилин для дзвінків.
     *
     * @return кількість хвилин дзвінків.
     */
    public int getCallMinutesCount() {
        return callMinutesCount;
    }

    /**
     * Повертає кількість гігабайтів інтернету.
     *
     * @return кількість гігабайтів інтернету.
     */
    public float getInternetGigabytesCount() {
        return internetGigabytesCount;
    }

    /**
     * Повертає кількість підключених абонентів.
     *
     * @return кількість підключених абонентів.
     */
    public int getSubscribersCount() {
        return subscribersCount;
    }

    /**
     * Встановлює назву тарифу.
     *
     * @param name назва тарифу.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Встановлює щомісячну абонплату.
     *
     * @param monthlyFee місячна абонплата.
     */
    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    /**
     * Встановлює кількість хвилин для дзвінків.
     *
     * @param callMinutesCount кількість хвилин дзвінків.
     */
    public void setCallMinutesCount(int callMinutesCount) {
        this.callMinutesCount = callMinutesCount;
    }

    /**
     * Встановлює кількість гігабайтів інтернету.
     *
     * @param internetGigabytesCount кількість гігабайтів інтернету.
     */
    public void setInternetGigabytesCount(float internetGigabytesCount) {
        this.internetGigabytesCount = internetGigabytesCount;
    }

    /**
     * Додає одного підключеного абонента.
     */
    public void addSubscriber() {
        this.subscribersCount++;
    }

    /**
     * Видаляє одного підключеного абонента.
     */
    public void subSubscriber() {
        this.subscribersCount--;
    }

    /**
     * Повертає строкове представлення тарифу.
     *
     * @return рядок, який містить усі основні характеристики тарифу.
     */
    @Override
    public String toString() {
        return String.format(
                "Тариф: %s, Місячна плата: %.2f, Хвилини дзвінків: %d, Гігабайти інтернету: %.2f, Кількість клієнтів: %d",
                name, monthlyFee, callMinutesCount, internetGigabytesCount, subscribersCount
        );
    }
}