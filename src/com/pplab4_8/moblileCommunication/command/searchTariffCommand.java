package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkOperator;

/**
 * Клас searchTariffCommand реалізує команду для пошуку тарифу
 * в мобільного оператора.
 */
public class searchTariffCommand implements Command {
    private MobileNetworkOperator operator;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param operator об'єкт мобільного оператора, в якому буде здійснено пошук тарифу
     */
    public searchTariffCommand(MobileNetworkOperator operator) {
        this.operator = operator;
    }

    /**
     * Виконує команду, здійснюючи пошук тарифу для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        operator.searchTariff();
    }
}