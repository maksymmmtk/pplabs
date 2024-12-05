package com.pplab4_8.mobileCommunication.tariffs;

/**
 * Клас TariffContract представляє контрактний тариф, який містить додаткові послуги на відміну від тарифу на передоплату.
 * Він розширює клас {@link TariffPrepaid} і додає можливість зберігати додаткові послуги.
 */
public class TariffContract extends TariffPrepaid {
    private String additionalServices;

    /**
     * Конструктор для створення контрактного тарифу.
     *
     * @param name               назва тарифу.
     * @param monthlyFee         щомісячна абонплата.
     * @param callMinutesCount   кількість хвилин дзвінків.
     * @param internetGigabytesCount кількість гігабайтів інтернет-трафіку.
     * @param additionalServices додаткові послуги, доступні в рамках тарифу.
     */
    public TariffContract(String name, float monthlyFee, int callMinutesCount, float internetGigabytesCount, String additionalServices){
        super(name, monthlyFee, callMinutesCount, internetGigabytesCount);
        this.additionalServices = additionalServices;
    }

    /**
     * Повертає додаткові послуги, доступні в рамках тарифу.
     *
     * @return рядок з описом додаткових послуг.
     */
    public String getAdditionalServices(){
        return additionalServices;
    }

    /**
     * Встановлює додаткові послуги для цього тарифу.
     *
     * @param additionalServices рядок з описом додаткових послуг.
     */
    public void setAdditionalServices(String additionalServices){
        this.additionalServices = additionalServices;
    }

    /**
     * Повертає рядкове представлення тарифу, яке включає основні параметри з {@link TariffPrepaid} та додаткові послуги.
     *
     * @return рядок з інформацією про тариф.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(", Додаткові послуги: %s", additionalServices);
    }
}