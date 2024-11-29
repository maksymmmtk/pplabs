package com.pplab4_8.mobileCommunication.tariffs;

/**
 * Клас, що представляє тариф передплати.
 * Він містить основні характеристики тарифу, такі як назва, абонплата, кількість хвилин для дзвінків, обсяг інтернету та кількість підписників.
 */
public class TariffPrepaid {
    private String name; // Назва тарифу
    private float monthlyFee; // Місячна абонплата
    private int callMinutesCount; // Кількість хвилин для дзвінків
    private float internetGigabytesCount; // Кількість гігабайт інтернету
    private int subscribersCount = 0; // Кількість підписників, ініціалізовано нулем

    /**
     * Конструктор для створення тарифу передплати з заданими параметрами.
     *
     * @param name назва тарифу
     * @param monthlyFee щомісячна абонплата
     * @param callMinutesCount кількість хвилин для дзвінків
     * @param internetGigabytesCount кількість гігабайт інтернету
     */
    public TariffPrepaid(String name, float monthlyFee, int callMinutesCount, float internetGigabytesCount) {
        this.name = name; // Ініціалізація назви тарифу
        this.monthlyFee = monthlyFee; // Ініціалізація абонплати
        this.callMinutesCount = callMinutesCount; // Ініціалізація кількості хвилин для дзвінків
        this.internetGigabytesCount = internetGigabytesCount; // Ініціалізація кількості гігабайт інтернету
    }

    /**
     * Отримує назву тарифу.
     *
     * @return назва тарифу
     */
    public String getName() {
        return name;
    }

    /**
     * Отримує місячну абонплату.
     *
     * @return місячну абонплату
     */
    public float getMonthlyFee() {
        return monthlyFee;
    }

    /**
     * Отримує кількість хвилин для дзвінків.
     *
     * @return кількість хвилин для дзвінків
     */
    public int getCallMinutesCount() {
        return callMinutesCount;
    }

    /**
     * Отримує кількість гігабайт інтернету.
     *
     * @return кількість гігабайт інтернету
     */
    public float getInternetGigabytesCount() {
        return internetGigabytesCount;
    }

    /**
     * Отримує кількість підписників на тариф.
     *
     * @return кількість підписників
     */
    public int getSubscribersCount() {
        return subscribersCount;
    }

    /**
     * Встановлює назву тарифу.
     *
     * @param name нова назва тарифу
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Встановлює місячну абонплату.
     *
     * @param monthlyFee нова місячна абонплата
     */
    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    /**
     * Встановлює кількість хвилин для дзвінків.
     *
     * @param callMinutesCount нова кількість хвилин
     */
    public void setCallMinutesCount(int callMinutesCount) {
        this.callMinutesCount = callMinutesCount;
    }

    /**
     * Встановлює кількість гігабайт інтернету.
     *
     * @param internetGigabytesCount нова кількість гігабайт інтернету
     */
    public void setInternetGigabytesCount(float internetGigabytesCount) {
        this.internetGigabytesCount = internetGigabytesCount;
    }

    /**
     * Збільшує кількість підписників на тариф.
     * Використовується при підключенні нового абонента до тарифу.
     */
    public void addSubscriber() {
        this.subscribersCount++; // Збільшуємо лічильник підписників
    }

    /**
     * Зменшує кількість підписників на тариф.
     * Використовується при відключенні абонента від тарифу.
     */
    public void subSubscriber() {
        this.subscribersCount--; // Зменшуємо лічильник підписників
    }

    /**
     * Перевизначений метод toString для виведення інформації про тариф.
     * Повертає рядок із основною інформацією про тариф: назва, абонплата, хвилини дзвінків, гігабайти інтернету та кількість підписників.
     *
     * @return рядок з інформацією про тариф
     */
    @Override
    public String toString() {
        return String.format(
                "Тариф: %s, Місячна плата: %.2f, Хвилини дзвінків: %d, Гігабайти інтернету: %.2f, Кількість клієнтів: %d",
                name, monthlyFee, callMinutesCount, internetGigabytesCount, subscribersCount
        );
    }
}