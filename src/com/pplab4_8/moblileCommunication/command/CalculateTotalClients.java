package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас CalculateTotalClients реалізує інтерфейс Command і відповідає за команду
 * підрахунку загальної кількості клієнтів у мобільній мережі.
 */
public class CalculateTotalClients implements Command {
    // Сервіс мобільної мережі, який виконує операцію підрахунку клієнтів
    private MobileNetworkService service;

    /**
     * Конструктор, який приймає об'єкт MobileNetworkService.
     * @param service сервіс мобільної мережі, який буде використовуватись для виконання команди
     */
    public CalculateTotalClients(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконує команду підрахунку загальної кількості клієнтів,
     * викликаючи відповідний метод у MobileNetworkService.
     */
    @Override
    public void execute() {
        service.calculateTotalClients();
    }

    /**
     * Метод повертає назву команди.
     * @return назва команди "Підрахувати загальну кількість клієнтів".
     */
    @Override
    public String getName() {
        return "Підрахувати загальну кількість клієнтів";
    }
}