package com.pplab4_8.mobileCommunication.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Інтерфейс, що представляє команду, яку можна виконати.
 * Використовується для реалізації патерна "Команда".
 */
public interface Command {
    Logger logger = LogManager.getLogger(Command.class);

    /**
     * Метод для виконання команди.
     * Реалізація цього методу повинна містити основну логіку команди.
     */
    void execute();

    /**
     * Метод для отримання назви команди.
     * За замовчуванням повертає просте ім'я класу та логує цю дію.
     *
     * @return назва команди.
     */
    default String getName() {
        String name = this.getClass().getSimpleName();
        logger.info("Отримано назву команди: '{}'", name);
        return name;
    }
}