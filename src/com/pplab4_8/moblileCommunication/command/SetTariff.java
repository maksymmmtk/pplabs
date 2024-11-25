package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileSubscriber;

/**
 * Клас SetTariff реалізує інтерфейс Command і відповідає за команду
 * для підключення тарифу мобільним абонентом.
 */
public class SetTariff implements Command {

    // Об'єкт мобільного абонента, якому буде підключено тариф
    private MobileSubscriber subscriber;

    /**
     * Конструктор класу, який приймає об'єкт MobileSubscriber.
     * @param subscriber абонент мобільної мережі, якому буде підключено тариф.
     */
    public SetTariff(MobileSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * Метод виконує команду підключення тарифу для конкретного абонента,
     * викликаючи метод setTariff() у об'єкта MobileSubscriber.
     */
    @Override
    public void execute() {
        subscriber.setTariff();
    }

    /**
     * Метод повертає назву команди.
     * @return назва команди "Підключити тариф".
     */
    @Override
    public String getName() {
        return "Підключити тариф";
    }
}