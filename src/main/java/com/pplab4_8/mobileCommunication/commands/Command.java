package com.pplab4_8.mobileCommunication.commands;

/**
 * Інтерфейс, що представляє команду, яку можна виконати.
 * Використовується для реалізації патерна "Команда".
 */
public interface Command {

    /**
     * Метод для виконання команди.
     * Реалізація цього методу повинна містити основну логіку команди.
     */
    void execute();

    /**
     * Метод для отримання назви команди.
     * За замовчуванням повертає просте ім'я класу.
     *
     * @return назва команди.
     */
    default String getName() {
        return this.getClass().getSimpleName();
    }
}