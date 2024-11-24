package com.pplab4_8.moblileCommunication.command;

import com.pplab4_8.moblileCommunication.users.MobileNetworkService;

/**
 * Клас editTariffCommand реалізує команду для редагування тарифу
 * в мобільного оператора.
 */
public class EditTariff implements Command {
    private MobileNetworkService service;

    /**
     * Конструктор, який приймає мобільного оператора для виконання команди.
     *
     * @param service об'єкт мобільного оператора, над яким буде виконана команда
     */
    public EditTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконує команду, редагуючи існуючий тариф для заданого мобільного оператора.
     */
    @Override
    public void execute() {
        service.editTariff();
    }

    @Override
    public String getName() {
        return "Редагувати тариф";
    }
}