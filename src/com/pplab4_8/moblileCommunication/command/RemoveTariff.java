package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас RemoveTariff реалізує інтерфейс Command і представляє команду
 * для видалення тарифу з мобільної мережі.
 */
public class RemoveTariff implements Command {

    // Сервіс мобільної мережі, який використовується для виконання дії видалення тарифу
    private MobileNetworkService service;

    /**
     * Конструктор класу, який приймає об'єкт MobileNetworkService.
     * @param service сервіс мобільної мережі, який виконує операцію видалення тарифу
     */
    public RemoveTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконує команду видалення тарифу, викликаючи відповідний метод у MobileNetworkService.
     */
    @Override
    public void execute() {
        service.removeTariff();
    }

    /**
     * Метод повертає назву команди.
     * @return назва команди "Видалити тариф".
     */
    @Override
    public String getName() {
        return "Видалити тариф";
    }
}