package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;

import java.util.Scanner;

/**
 * Клас для команди перегляду наявних тарифів.
 * Реалізує інтерфейс Command.
 */
public class ViewTariffs implements Command {
    private MobileNetworkService service; // Сервіс для управління тарифами

    /**
     * Конструктор для ініціалізації команди з сервісом.
     *
     * @param service сервіс для управління тарифами
     */
    public ViewTariffs(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконання команди перегляду тарифів.
     * Виводить список тарифів, пропонує додаткові операції з тарифами.
     */
    @Override
    public void execute() {
        // Отримуємо список тарифів із сервісу
        var tariffs = service.viewTariffs();

        // Якщо список тарифів порожній, виводимо відповідне повідомлення
        if (tariffs.isEmpty()) {
            System.out.println("Список тарифів порожній.");
            return;
        }

        // Виводимо список тарифів
        System.out.println("\nСписок тарифів:");
        tariffs.forEach(System.out::println);

        // Пропонуємо користувачу додаткові опції
        System.out.println("\n1. Підрахувати клієнтів");
        System.out.println("2. Відсортувати за абонплатою");
        System.out.println("3. Завершити");

        // Читання вибору користувача
        Scanner scanner = new Scanner(System.in);

        // Обробка вибору користувача
        switch (scanner.nextInt()) {
            case 1 -> System.out.println("Клієнтів: " + service.calculateTotalClients()); // Підрахунок клієнтів
            case 2 -> {
                service.sortTariffsByFee(); // Сортування тарифів за абонплатою
                System.out.println("Тарифи відсортовано.");
            }
            default -> System.out.println("Завершено."); // Завершення операції
        }
    }

    /**
     * Метод для отримання назви команди.
     *
     * @return Назва команди для відображення у меню
     */
    @Override
    public String getName() {
        return "Переглянути наявні тарифи"; // Назва команди, яка відображатиметься в меню
    }
}