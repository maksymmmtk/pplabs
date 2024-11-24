package com.pplab4_8.moblileCommunication.command;

/**
 * Інтерфейс Command представляє команду, яку можна виконати.
 * Його реалізують класи, які представляють конкретні команди.
 */
public interface Command {
    /**
     * Метод execute() виконує команду.
     * Його реалізація міститиме специфічну логіку для кожної команди.
     */
    public void execute();

    /**
     * Повертає назву команди.
     * @return Назва команди
     */
    default String getName() {
        return this.getClass().getSimpleName();
    }
}