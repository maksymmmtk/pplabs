package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileSubscriber;

/**
 * Клас ViewPersonalTariff реалізує інтерфейс Command і представляє команду
 * для перегляду особистого тарифу мобільним абонентом.
 */
public class ViewPersonalTariff implements Command {

    // Об'єкт мобільного абонента, для якого буде переглянуто особистий тариф
    private MobileSubscriber subscriber;

    /**
     * Конструктор класу, який приймає об'єкт MobileSubscriber.
     * @param subscriber абонент мобільної мережі, для якого буде виконано перегляд особистого тарифу.
     */
    public ViewPersonalTariff(MobileSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * Метод виконує команду перегляду особистого тарифу для конкретного абонента,
     * викликаючи метод viewPersonalTariff() у об'єкта MobileSubscriber.
     */
    @Override
    public void execute() {
        subscriber.viewPersonalTariff();
    }

    /**
     * Метод повертає назву команди.
     * @return назва команди "Переглянути особистий тариф".
     */
    @Override
    public String getName() {
        return "Переглянути особистий тариф";
    }
}