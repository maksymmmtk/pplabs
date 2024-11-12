package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkOperator;

/**
 * Клас addTariffCommand реалізує команду для додавання тарифу
 * в мобільного оператора.
 */
public class addTariffCommand implements Command {
    private MobileNetworkOperator operator;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param operator об'єкт мобільного оператора, над яким буде виконана команда
     */
    public addTariffCommand(MobileNetworkOperator operator) {
        this.operator = operator;
    }

    /**
     * Виконує команду, додаючи новий тариф для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        operator.addTariff();
    }
}