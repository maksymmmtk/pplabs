package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас removeTariffCommand реалізує команду для видалення тарифу
 * в мобільного оператора.
 */
public class RemoveTariff implements Command {
    private MobileNetworkService service;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param service об'єкт мобільного оператора, над яким буде виконана команда
     */
    public RemoveTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконує команду, видаляючи тариф для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        service.removeTariff();
    }

    @Override
    public String getName() {
        return "Видалити тариф";
    }
}