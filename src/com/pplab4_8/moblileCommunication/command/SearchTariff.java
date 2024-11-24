package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас searchTariffCommand реалізує команду для пошуку тарифу
 * в мобільного оператора.
 */
public class SearchTariff implements Command {
    private MobileNetworkService service;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param service об'єкт мобільного оператора, в якому буде здійснено пошук тарифу
     */
    public SearchTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконує команду, здійснюючи пошук тарифу для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        service.searchTariff();
    }

    @Override
    public String getName() {
        return "Знайти тариф";
    }
}