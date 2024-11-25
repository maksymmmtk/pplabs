package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас SearchTariff реалізує інтерфейс Command і представляє команду
 * для пошуку тарифу в мобільній мережі.
 */
public class SearchTariff implements Command {

    // Сервіс мобільної мережі, який використовується для виконання пошуку тарифу
    private MobileNetworkService service;

    /**
     * Конструктор класу, який приймає об'єкт MobileNetworkService.
     * @param service сервіс мобільної мережі, який виконує операцію пошуку тарифу.
     */
    public SearchTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконує команду пошуку тарифу, викликаючи відповідний метод у MobileNetworkService.
     */
    @Override
    public void execute() {
        service.searchTariff();
    }

    /**
     * Метод повертає назву команди.
     * @return назва команди "Знайти тариф".
     */
    @Override
    public String getName() {
        return "Знайти тариф";
    }
}