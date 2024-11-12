package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkOperator;

/**
 * Клас removeTariffCommand реалізує команду для видалення тарифу
 * в мобільного оператора.
 */
public class removeTariffCommand implements Command {
    private MobileNetworkOperator operator;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param operator об'єкт мобільного оператора, над яким буде виконана команда
     */
    public removeTariffCommand(MobileNetworkOperator operator) {
        this.operator = operator;
    }

    /**
     * Виконує команду, видаляючи тариф для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        operator.removeTariff();
    }
}