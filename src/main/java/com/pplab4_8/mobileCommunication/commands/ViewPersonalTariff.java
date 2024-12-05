package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.users.MobileSubscriber;

/**
 * Команда для перегляду особистого тарифу абонента.
 */
public class ViewPersonalTariff implements Command {

    private MobileSubscriber subscriber;

    /**
     * Конструктор класу ViewPersonalTariff.
     *
     * @param subscriber абонент, чий особистий тариф буде переглянуто.
     */
    public ViewPersonalTariff(MobileSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * Виконує команду перегляду особистого тарифу.
     * Викликає відповідний метод у {@link MobileSubscriber}.
     */
    @Override
    public void execute() {
        subscriber.viewPersonalTariff();
    }

    /**
     * Отримує назву команди.
     *
     * @return назва команди.
     */
    @Override
    public String getName() {
        return "Переглянути особистий тариф";
    }
}