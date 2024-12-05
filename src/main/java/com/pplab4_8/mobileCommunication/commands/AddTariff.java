package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;

import java.util.Scanner;

/**
 * Команда для додавання нового тарифу до мобільної мережі.
 * Дозволяє додавати передплачені та контрактні тарифи через консольний ввід.
 */
public class AddTariff implements Command {
    private MobileNetworkService service;

    /**
     * Конструктор класу AddTariff.
     *
     * @param service сервіс мобільної мережі, в який будуть додаватися тарифи.
     */
    public AddTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконання команди для додавання нового тарифу.
     * Користувач вводить дані через консоль.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("\nДодавання нового тарифу:");
            System.out.println("1. Передплачений тариф (Prepaid)");
            System.out.println("2. Контрактний тариф (Contract)");
            System.out.print("Ваш вибір: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Некоректний ввід. Тариф не додано.");
                return;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > 2) {
                System.out.println("Невірний вибір. Тариф не додано.");
                return;
            }

            System.out.print("Введіть назву тарифу: ");
            String name = scanner.nextLine();

            System.out.print("Введіть щомісячну плату: ");
            if (!scanner.hasNextFloat()) {
                System.out.println("Некоректний ввід. Тариф не додано.");
                return;
            }
            float monthlyFee = scanner.nextFloat();

            System.out.print("Введіть кількість хвилин: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Некоректний ввід. Тариф не додано.");
                return;
            }
            int callMinutes = scanner.nextInt();

            System.out.print("Введіть кількість гігабайт: ");
            if (!scanner.hasNextFloat()) {
                System.out.println("Некоректний ввід. Тариф не додано.");
                return;
            }
            float gigabytes = scanner.nextFloat();

            scanner.nextLine();

            if (choice == 1) {
                service.addTariff(new TariffPrepaid(name, monthlyFee, callMinutes, gigabytes));
                System.out.println("Передплачений тариф успішно додано.");
            } else if (choice == 2) {
                System.out.print("Введіть додаткові послуги: ");
                String services = scanner.nextLine();

                service.addTariff(new TariffContract(name, monthlyFee, callMinutes, gigabytes, services));
                System.out.println("Контрактний тариф успішно додано.");
            }
        } finally {
            scanner.close();
        }
    }

    /**
     * Отримує назву команди.
     *
     * @return назва команди.
     */
    @Override
    public String getName() {
        return "Додати тариф";
    }
}