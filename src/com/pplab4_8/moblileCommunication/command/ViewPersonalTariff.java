package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileSubscriber;

/**
 * Клас viewPersonalTariffCommand реалізує команду для перегляду
 * персонального тарифу мобільного абонента.
 */
public class ViewPersonalTariff implements Command {
    private MobileSubscriber subscriber;

    /**
     * Конструктор, який приймає мобільного абонента для виконання команди.
     *
     * @param subscriber об'єкт мобільного абонента, чий персональний тариф буде переглянуто
     */
    public ViewPersonalTariff(MobileSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * Виконує команду, показуючи персональний тариф для заданого мобільного абонента.
     */
    @Override
    public void execute() {
        subscriber.viewPersonalTariff();
    }

    @Override
    public String getName() {
        return "Переглянути особистий тариф";
    }
}