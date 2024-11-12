package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkOperator;

/**
 * Клас calculateTotalClientsCommand реалізує команду для обчислення
 * загальної кількості клієнтів у мобільного оператора.
 */
public class calculateTotalClientsCommand implements Command {
    private MobileNetworkOperator operator;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param operator об'єкт мобільного оператора, для якого буде обчислена загальна кількість клієнтів
     */
    public calculateTotalClientsCommand(MobileNetworkOperator operator) {
        this.operator = operator;
    }

    /**
     * Виконує команду, обчислюючи загальну кількість клієнтів для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        operator.calculateTotalClients();
    }
}