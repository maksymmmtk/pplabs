package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;

import java.util.Scanner;

/**
 * Команда для видалення тарифу з мобільної мережі.
 * Дозволяє видалити тариф за його назвою.
 */
public class RemoveTariff implements Command {
    private MobileNetworkService service;

    /**
     * Конструктор класу RemoveTariff.
     *
     * @param service сервіс мобільної мережі, який використовується для роботи з тарифами.
     */
    public RemoveTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконує видалення тарифу з системи.
     * Користувач вводить назву тарифу, який необхідно видалити.
     * Якщо тариф знайдено, він буде видалений; якщо ні — буде виведено повідомлення.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nВведіть назву тарифу для видалення: ");
        String name = scanner.nextLine();
        TariffPrepaid tariff = service.searchTariffByName(name);

        if (tariff != null) {
            service.removeTariff(tariff);
            System.out.println("Тариф успішно видалено.");
        } else {
            System.out.println("Тариф не знайдено.");
        }
    }

    /**
     * Отримує назву команди.
     *
     * @return назва команди.
     */
    @Override
    public String getName() {
        return "Видалити тариф";
    }
}