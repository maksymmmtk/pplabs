package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас EditTariff реалізує інтерфейс Command і представляє команду для редагування тарифу
 * в мобільній мережі.
 */
public class EditTariff implements Command {

    // Об'єкт сервісу мобільної мережі, який використовується для редагування тарифу
    private MobileNetworkService service;

    /**
     * Конструктор класу, який приймає об'єкт MobileNetworkService.
     * @param service сервіс мобільної мережі, який буде виконувати редагування тарифу.
     */
    public EditTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконує команду редагування тарифу, викликаючи відповідний метод у MobileNetworkService.
     */
    @Override
    public void execute() {
        service.editTariff();
    }

    /**
     * Метод повертає назву команди.
     * @return назва команди "Редагувати тариф".
     */
    @Override
    public String getName() {
        return "Редагувати тариф";
    }
}