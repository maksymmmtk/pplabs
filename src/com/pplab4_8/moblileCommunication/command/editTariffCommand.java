package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkOperator;

/**
 * Клас editTariffCommand реалізує команду для редагування тарифу
 * в мобільного оператора.
 */
public class editTariffCommand implements Command {
    private MobileNetworkOperator operator;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param operator об'єкт мобільного оператора, над яким буде виконана команда
     */
    public editTariffCommand(MobileNetworkOperator operator) {
        this.operator = operator;
    }

    /**
     * Виконує команду, редагуючи існуючий тариф для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        operator.editTariff();
    }
}