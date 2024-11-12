package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileSubscriber;

/**
 * Клас setTariffCommand реалізує команду для встановлення тарифу
 * мобільному абоненту.
 */
public class setTariffCommand implements Command {
    private MobileSubscriber subscriber;

    /**
     * Конструктор, який приймає мобільного абонента для виконання команди.
     *
     * @param subscriber об'єкт мобільного абонента, якому буде встановлено тариф
     */
    public setTariffCommand(MobileSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * Виконує команду, встановлюючи тариф для заданого мобільного абонента.
     */
    @Override
    public void execute() {
        subscriber.setTariff();
    }
}