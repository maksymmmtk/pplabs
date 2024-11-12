package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkOperator;

/**
 * Клас sortTariffsByFeeCommand реалізує команду для сортування тарифів
 * мобільного оператора за розміром плати.
 */
public class sortTariffsByFeeCommand implements Command {
    private MobileNetworkOperator operator;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param operator об'єкт мобільного оператора, тарифи якого будуть відсортовані за платою
     */
    public sortTariffsByFeeCommand(MobileNetworkOperator operator) {
        this.operator = operator;
    }

    /**
     * Виконує команду, сортування тарифів за розміром плати для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        operator.sortTariffsByFee();
    }
}