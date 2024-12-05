package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;

import java.util.Scanner;

/**
 * Команда для перегляду всіх доступних тарифів.
 * Додатково дозволяє підрахувати кількість клієнтів або відсортувати тарифи за абонплатою.
 */
public class ViewTariffs implements Command {
    private MobileNetworkService service;

    /**
     * Конструктор класу ViewTariffs.
     *
     * @param service сервіс мобільної мережі для роботи з тарифами.
     */
    public ViewTariffs(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконує команду перегляду тарифів.
     * Виводить список тарифів та надає додаткові функції:
     * - Підрахунок клієнтів на тарифах.
     * - Сортування тарифів за абонплатою.
     */
    @Override
    public void execute() {
        var tariffs = service.viewTariffs();
        if (tariffs.isEmpty()) {
            System.out.println("Список тарифів порожній.");
            return;
        }

        System.out.println("\nСписок тарифів:");
        tariffs.forEach(System.out::println);

        System.out.println("\n1. Підрахувати клієнтів");
        System.out.println("2. Відсортувати за абонплатою");
        System.out.println("3. Завершити");
        Scanner scanner = new Scanner(System.in);

        // Виконання вибору дії
        switch (scanner.nextInt()) {
            case 1 -> System.out.println("Клієнтів: " + service.calculateTotalClients());
            case 2 -> {
                service.sortTariffsByFee();
                System.out.println("Тарифи відсортовано.");
            }
            default -> System.out.println("Завершено.");
        }
    }

    /**
     * Отримує назву команди.
     *
     * @return назва команди.
     */
    @Override
    public String getName() {
        return "Переглянути наявні тарифи";
    }
}