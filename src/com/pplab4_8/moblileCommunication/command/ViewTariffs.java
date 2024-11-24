package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас viewTariffsCommand реалізує команду для перегляду всіх тарифів
 * у мобільного оператора.
 */
public class ViewTariffs implements Command {
    private MobileNetworkService service;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param service об'єкт мобільного оператора, тарифи якого будуть переглянуті
     */
    public ViewTariffs(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконує команду, показуючи всі доступні тарифи для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        service.viewTariffs();
    }

    @Override
    public String getName() {
        return "Переглянути наявні тарифи";
    }
}