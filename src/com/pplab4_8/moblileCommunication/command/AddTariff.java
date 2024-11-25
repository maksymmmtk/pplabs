package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас AddTariff реалізує інтерфейс Command і представляє команду для додавання тарифу
 * в мобільну мережу.
 */
public class AddTariff implements Command {
    // Об'єкт сервісу мобільної мережі, який виконує дію додавання тарифу
    private MobileNetworkService service;

    /**
     * Конструктор, який приймає об'єкт MobileNetworkService.
     * @param service сервіс мобільної мережі, який буде використовуватись для виконання команди
     */
    public AddTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконує команду додавання тарифу, використовуючи об'єкт MobileNetworkService.
     */
    @Override
    public void execute() {
        service.addTariff();
    }

    /**
     * Метод повертає назву команди.
     * @return назва команди "Додати тариф".
     */
    @Override
    public String getName() {
        return "Додати тариф";
    }
}