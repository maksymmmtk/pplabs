package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас addTariffCommand реалізує команду для додавання тарифу
 * в мобільного оператора.
 */
public class AddTariff implements Command {
    private MobileNetworkService service;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param service об'єкт мобільного оператора, над яким буде виконана команда
     */
    public AddTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконує команду, додаючи новий тариф для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        service.addTariff();
    }

    @Override
    public String getName() {
        return "Додати тариф";
    }
}