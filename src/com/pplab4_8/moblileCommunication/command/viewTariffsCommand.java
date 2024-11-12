package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkOperator;

/**
 * Клас viewTariffsCommand реалізує команду для перегляду всіх тарифів
 * у мобільного оператора.
 */
public class viewTariffsCommand implements Command {
    private MobileNetworkOperator operator;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param operator об'єкт мобільного оператора, тарифи якого будуть переглянуті
     */
    public viewTariffsCommand(MobileNetworkOperator operator) {
        this.operator = operator;
    }

    /**
     * Виконує команду, показуючи всі доступні тарифи для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        operator.viewTariffs();
    }
}