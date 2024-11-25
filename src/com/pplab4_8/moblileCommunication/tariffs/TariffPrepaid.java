package com.pplab4_8.moblileCommunication.tariffs;

/**
 * Клас TariffPrepaid представляє тарифний план без контракту (передплачений тариф).
 * Він містить інформацію про назву тарифу, місячну плату, кількість хвилин дзвінків,
 * кількість гігабайт інтернету та кількість підписників цього тарифу.
 */
public class TariffPrepaid {
    private String name;
    private float monthlyFee;
    private int callMinutesCount;
    private float internetGigabytesCount;
    private int subscribersCount = 0;

    /**
     * Конструктор для ініціалізації передплаченого тарифу.
     *
     * @param name Назва тарифу.
     * @param monthlyFee Місячна плата за тариф.
     * @param callMinutesCount Кількість хвилин дзвінків, що входять у тариф.
     * @param internetGigabytesCount Кількість гігабайт інтернету, що входять у тариф.
     */
    public TariffPrepaid(String name, float monthlyFee, int callMinutesCount, float internetGigabytesCount) {
        this.name = name;
        this.monthlyFee = monthlyFee;
        this.callMinutesCount = callMinutesCount;
        this.internetGigabytesCount = internetGigabytesCount;
    }

    /**
     * Отримує назву тарифу.
     *
     * @return Назва тарифу.
     */
    public String getName() {
        return name;
    }

    /**
     * Отримує місячну плату за тариф.
     *
     * @return Місячну плату за тариф.
     */
    public float getMonthlyFee() {
        return monthlyFee;
    }

    /**
     * Отримує кількість хвилин дзвінків, що входять у тариф.
     *
     * @return Кількість хвилин дзвінків.
     */
    public int getCallMinutesCount() {
        return callMinutesCount;
    }

    /**
     * Отримує кількість гігабайт інтернету, що входять у тариф.
     *
     * @return Кількість гігабайт інтернету.
     */
    public float getInternetGigabytesCount() {
        return internetGigabytesCount;
    }

    /**
     * Отримує кількість підписників цього тарифу.
     *
     * @return Кількість підписників.
     */
    public int getSubscribersCount() {
        return subscribersCount;
    }

    /**
     * Встановлює нову назву тарифу.
     *
     * @param name Нова назва тарифу.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Встановлює нову місячну плату за тариф.
     *
     * @param monthlyFee Нова місячна плата за тариф.
     */
    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    /**
     * Встановлює нову кількість хвилин дзвінків, що входять у тариф.
     *
     * @param callMinutesCount Нова кількість хвилин дзвінків.
     */
    public void setCallMinutesCount(int callMinutesCount) {
        this.callMinutesCount = callMinutesCount;
    }

    /**
     * Встановлює нову кількість гігабайт інтернету, що входять у тариф.
     *
     * @param internetGigabytesCount Нова кількість гігабайт інтернету.
     */
    public void setInternetGigabytesCount(float internetGigabytesCount) {
        this.internetGigabytesCount = internetGigabytesCount;
    }

    /**
     * Додає одного підписника до тарифу.
     * Збільшує лічильник підписників на одиницю.
     */
    public void addSubscriber() {
        this.subscribersCount++;
    }

    /**
     * Віднімає одного підписника від тарифу.
     * Зменшує лічильник підписників на одиницю.
     */
    public void subSubscriber() {
        this.subscribersCount--;
    }

    /**
     * Переопреділяє метод toString для відображення детальної інформації про тариф.
     *
     * @return Форматований рядок з інформацією про тариф.
     */
    @Override
    public String toString() {
        return String.format(
                "Тариф: %s, Місячна плата: %.2f, Хвилини дзвінків: %d, Гігабайти інтернету: %.2f, Кількість клієнтів: %d",
                name, monthlyFee, callMinutesCount, internetGigabytesCount, subscribersCount
        );
    }
}