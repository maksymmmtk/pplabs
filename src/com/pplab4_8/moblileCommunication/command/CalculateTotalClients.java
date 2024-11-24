package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас calculateTotalClientsCommand реалізує команду для обчислення
 * загальної кількості клієнтів у мобільного оператора.
 */
public class CalculateTotalClients implements Command {
    private MobileNetworkService service;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param service об'єкт мобільного оператора, для якого буде обчислена загальна кількість клієнтів
     */
    public CalculateTotalClients(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконує команду, обчислюючи загальну кількість клієнтів для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        service.calculateTotalClients();
    }

    @Override
    public String getName() {
        return "Підрахувати загальну кількість клієнтів";
    }
}