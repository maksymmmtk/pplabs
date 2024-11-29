package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;

import java.util.Scanner;

/**
 * Клас для команди додавання нового тарифу.
 * Реалізує інтерфейс Command.
 */
public class AddTariff implements Command {
    private MobileNetworkService service; // Сервіс для управління тарифами

    /**
     * Конструктор для ініціалізації об'єкта з посиланням на сервіс.
     *
     * @param service сервіс управління мобільними тарифами
     */
    public AddTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконання команди додавання нового тарифу.
     * Користувач вводить дані про тариф через консоль.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nДодавання нового тарифу:");
        System.out.println("1. Передплачений тариф (Prepaid)");
        System.out.println("2. Контрактний тариф (Contract)");
        System.out.print("Ваш вибір: ");
        int choice = scanner.nextInt(); // Зчитує вибір типу тарифу
        scanner.nextLine(); // Очищує вхідний буфер

        System.out.print("Введіть назву тарифу: ");
        String name = scanner.nextLine(); // Зчитує назву тарифу

        System.out.print("Введіть щомісячну плату: ");
        float monthlyFee = scanner.nextFloat(); // Зчитує щомісячну плату

        System.out.print("Введіть кількість хвилин: ");
        int callMinutes = scanner.nextInt(); // Зчитує кількість хвилин у тарифі

        System.out.print("Введіть кількість гігабайт: ");
        float gigabytes = scanner.nextFloat(); // Зчитує кількість гігабайт у тарифі

        // Обробка вибору типу тарифу
        if (choice == 1) {
            // Додається передплачений тариф
            service.addTariff(new TariffPrepaid(name, monthlyFee, callMinutes, gigabytes));
            System.out.println("Передплачений тариф успішно додано.");
        } else if (choice == 2) {
            scanner.nextLine(); // Очищує вхідний буфер
            System.out.print("Введіть додаткові послуги: ");
            String services = scanner.nextLine(); // Зчитує додаткові послуги для контрактного тарифу

            // Додається контрактний тариф
            service.addTariff(new TariffContract(name, monthlyFee, callMinutes, gigabytes, services));
            System.out.println("Контрактний тариф успішно додано.");
        } else {
            // Обробка помилкового вибору
            System.out.println("Невірний вибір. Тариф не додано.");
        }
    }

    /**
     * Метод для отримання назви команди.
     *
     * @return Назва команди для відображення у меню
     */
    @Override
    public String getName() {
        return "Додати тариф";
    }
}