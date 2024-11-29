package com.pplab4_8.mobileCommunication.tariffs;

/**
 * Клас, що представляє тариф за контрактом, який є підтипом тарифу передплати.
 * Додає до стандартного тарифу можливість зберігати інформацію про додаткові послуги.
 */
public class TariffContract extends TariffPrepaid {
    private String additionalServices; // Додаткові послуги для цього тарифу

    /**
     * Конструктор для ініціалізації тарифу за контрактом з усіма необхідними параметрами.
     *
     * @param name назва тарифу
     * @param monthlyFee щомісячна абонплата
     * @param callMinutesCount кількість хвилин для дзвінків
     * @param internetGigabytesCount кількість гігабайт інтернету
     * @param additionalServices додаткові послуги, що входять у тариф
     */
    public TariffContract(String name, float monthlyFee, int callMinutesCount, float internetGigabytesCount, String additionalServices){
        super(name, monthlyFee, callMinutesCount, internetGigabytesCount); // Викликаємо конструктор батьківського класу TariffPrepaid
        this.additionalServices = additionalServices; // ініціалізація додаткових послуг
    }

    /**
     * Отримує додаткові послуги для тарифу.
     *
     * @return додаткові послуги
     */
    public String getAdditionalServices(){
        return additionalServices; // Повертаємо додаткові послуги
    }

    /**
     * Встановлює нові додаткові послуги для тарифу.
     *
     * @param additionalServices нові додаткові послуги
     */
    public void setAdditionalServices(String additionalServices){
        this.additionalServices = additionalServices; // Оновлюємо значення додаткових послуг
    }

    /**
     * Перевизначений метод toString для виведення інформації про тариф у вигляді рядка.
     * Додається інформація про додаткові послуги.
     *
     * @return рядок, що містить інформацію про тариф та додаткові послуги
     */
    @Override
    public String toString() {
        return super.toString() + String.format(", Додаткові послуги: %s", additionalServices); // Викликаємо toString батьківського класу і додаємо додаткові послуги
    }
}