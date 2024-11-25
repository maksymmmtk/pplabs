package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас SortTariffsByFee реалізує інтерфейс Command і представляє команду
 * для сортування тарифів за оплатою в мобільній мережі.
 */
public class SortTariffsByFee implements Command {

    // Об'єкт сервісу мобільної мережі, який використовується для сортування тарифів
    private MobileNetworkService service;

    /**
     * Конструктор класу, який приймає об'єкт MobileNetworkService.
     * @param service сервіс мобільної мережі, який виконує операцію сортування тарифів.
     */
    public SortTariffsByFee(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконує команду сортування тарифів за оплатою, викликаючи відповідний метод у MobileNetworkService.
     */
    @Override
    public void execute() {
        service.sortTariffsByFee();
    }

    /**
     * Метод повертає назву команди.
     * @return назва команди "Відсортувати тарифи за оплатою".
     */
    @Override
    public String getName() {
        return "Відсортувати тарифи за оплатою";
    }
}