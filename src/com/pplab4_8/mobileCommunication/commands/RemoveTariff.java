package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;

import java.util.Scanner;

/**
 * Клас для команди видалення тарифу.
 * Реалізує інтерфейс Command.
 */
public class RemoveTariff implements Command {
    private MobileNetworkService service; // Сервіс для управління тарифами

    /**
     * Конструктор для ініціалізації команди з посиланням на сервіс.
     *
     * @param service сервіс для управління мобільними тарифами
     */
    public RemoveTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконання команди видалення тарифу.
     * Користувач вводить назву тарифу, який необхідно видалити.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        // Запит назви тарифу для видалення
        System.out.print("\nВведіть назву тарифу для видалення: ");
        String name = scanner.nextLine();

        // Пошук тарифу за назвою
        TariffPrepaid tariff = service.searchTariffByName(name);

        // Перевірка, чи знайдено тариф
        if (tariff != null) {
            // Видалення тарифу з сервісу
            service.removeTariff(tariff);
            System.out.println("Тариф успішно видалено.");
        } else {
            // Якщо тариф не знайдено
            System.out.println("Тариф не знайдено.");
        }
    }

    /**
     * Метод для отримання назви команди.
     *
     * @return Назва команди для відображення у меню
     */
    @Override
    public String getName() {
        return "Видалити тариф";
    }
}