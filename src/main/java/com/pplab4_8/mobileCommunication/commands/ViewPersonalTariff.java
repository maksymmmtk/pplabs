package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.users.MobileSubscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Команда для перегляду особистого тарифу абонента.
 */
public class ViewPersonalTariff implements Command {

    private static final Logger logger = LogManager.getLogger(ViewPersonalTariff.class);
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
        logger.info("Початок виконання команди 'Переглянути особистий тариф' для абонента: {}", subscriber.getName());

        // Виклик методу для перегляду тарифу
        subscriber.viewPersonalTariff();

        logger.info("Завершення виконання команди 'Переглянути особистий тариф' для абонента: {}", subscriber.getName());
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