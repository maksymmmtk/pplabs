package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.users.MobileSubscriber;

/**
 * Клас для команди перегляду особистого тарифу абонента.
 * Реалізує інтерфейс Command.
 */
public class ViewPersonalTariff implements Command {

    private MobileSubscriber subscriber; // Абонент, для якого переглядається тариф

    /**
     * Конструктор для ініціалізації команди з абонентом.
     *
     * @param subscriber абонент, чий тариф буде переглянуто
     */
    public ViewPersonalTariff(MobileSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * Метод виконання команди перегляду особистого тарифу.
     * Викликається метод перегляду тарифу для заданого абонента.
     */
    @Override
    public void execute() {
        subscriber.viewPersonalTariff(); // Викликає метод перегляду тарифу абонента
    }

    /**
     * Метод для отримання назви команди.
     *
     * @return Назва команди для відображення у меню
     */
    @Override
    public String getName() {
        return "Переглянути особистий тариф"; // Назва команди, яка відображатиметься в меню
    }
}