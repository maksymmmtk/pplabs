package com.pplab4_8.moblileCommunication.tariffs;

/**
 * Клас TariffContract розширює клас TariffPrepaid і представляє тарифний план з контрактом.
 * Він додатково зберігає інформацію про додаткові послуги, які надаються разом з тарифом.
 */
public class TariffContract extends TariffPrepaid {
    private String additionalServices;

    /**
     * Конструктор для ініціалізації тарифу з контрактом.
     *
     * @param name Назва тарифу.
     * @param monthlyFee Місячний платіж за тариф.
     * @param callMinutesCount Кількість хвилин дзвінків, що входять у тариф.
     * @param internetGigabytesCount Кількість гігабайт інтернету, що входять у тариф.
     * @param additionalServices Опис додаткових послуг, що входять у тариф.
     */
    public TariffContract(String name, float monthlyFee, int callMinutesCount, float internetGigabytesCount, String additionalServices){
        super(name, monthlyFee, callMinutesCount, internetGigabytesCount);
        this.additionalServices = additionalServices;
    }

    /**
     * Отримує додаткові послуги, які включені в тариф.
     *
     * @return Опис додаткових послуг.
     */
    public String getAdditionalServices(){
        return additionalServices;
    }

    /**
     * Встановлює новий опис додаткових послуг для тарифу.
     *
     * @param additionalServices Опис нових додаткових послуг.
     */
    public void setAdditionalServices(String additionalServices){
        this.additionalServices = additionalServices;
    }

    /**
     * Переопреділяє метод toString для відображення інформації про тариф.
     * Включає додаткові послуги в опис тарифу.
     *
     * @return Форматований рядок з інформацією про тариф, включаючи додаткові послуги.
     */
    @Override
    public String toString() {
        return super.toString() + String.format(", Додаткові послуги: %s", additionalServices);
    }
}