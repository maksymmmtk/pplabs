package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас sortTariffsByFeeCommand реалізує команду для сортування тарифів
 * мобільного оператора за розміром плати.
 */
public class SortTariffsByFee implements Command {
    private MobileNetworkService service;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param service об'єкт мобільного оператора, тарифи якого будуть відсортовані за платою
     */
    public SortTariffsByFee(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконує команду, сортування тарифів за розміром плати для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        service.sortTariffsByFee();
    }

    @Override
    public String getName() {
        return "Відсортувати тарифи за оплатою";
    }
}