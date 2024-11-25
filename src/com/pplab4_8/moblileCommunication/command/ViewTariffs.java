package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас ViewTariffs реалізує інтерфейс Command і представляє команду
 * для перегляду наявних тарифів у мобільній мережі.
 */
public class ViewTariffs implements Command {

    // Сервіс мобільної мережі, який використовується для перегляду тарифів
    private MobileNetworkService service;

    /**
     * Конструктор класу, який приймає об'єкт MobileNetworkService.
     * @param service сервіс мобільної мережі, який виконує операцію перегляду тарифів.
     */
    public ViewTariffs(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконує команду перегляду тарифів, викликаючи відповідний метод у MobileNetworkService.
     */
    @Override
    public void execute() {
        service.viewTariffs();
    }

    /**
     * Метод повертає назву команди.
     * @return назва команди "Переглянути наявні тарифи".
     */
    @Override
    public String getName() {
        return "Переглянути наявні тарифи";
    }
}