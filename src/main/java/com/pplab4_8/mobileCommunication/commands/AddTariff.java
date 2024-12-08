package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.TariffContract;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Команда для додавання нового тарифу до мобільної мережі.
 * Дозволяє додавати передплачені та контрактні тарифи через консольний ввід.
 */
public class AddTariff implements Command {
    private static final Logger logger = LogManager.getLogger(AddTariff.class);

    private MobileNetworkService service;

    /**
     * Конструктор класу AddTariff.
     *
     * @param service сервіс мобільної мережі, в який будуть додаватися тарифи.
     */
    public AddTariff(MobileNetworkService service) {
        this.service = service;
        logger.info("Ініціалізовано команду 'Додати тариф' для мережі '{}'", service.getNetwork().getName());
    }

    /**
     * Виконання команди для додавання нового тарифу.
     * Користувач вводить дані через консоль.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        try {
            logger.info("Розпочато виконання команди 'Додати тариф'");
            System.out.println("\nДодавання нового тарифу:");
            System.out.println("1. Передплачений тариф (Prepaid)");
            System.out.println("2. Контрактний тариф (Contract)");
            System.out.print("Ваш вибір: ");

            if (!scanner.hasNextInt()) {
                logger.warn("Користувач ввів некоректний тип даних для вибору типу тарифу.");
                System.out.println("Некоректний ввід. Тариф не додано.");
                return;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // очищення буфера

            if (choice < 1 || choice > 2) {
                logger.warn("Користувач зробив некоректний вибір: {}", choice);
                System.out.println("Невірний вибір. Тариф не додано.");
                return;
            }

            logger.info("Користувач обрав тип тарифу: {}", choice == 1 ? "Передплачений" : "Контрактний");

            System.out.print("Введіть назву тарифу: ");
            String name = scanner.nextLine();
            logger.info("Користувач ввів назву тарифу: '{}'", name);

            System.out.print("Введіть щомісячну плату: ");
            if (!scanner.hasNextFloat()) {
                logger.warn("Користувач ввів некоректний тип даних для місячної плати.");
                System.out.println("Некоректний ввід. Тариф не додано.");
                return;
            }
            float monthlyFee = scanner.nextFloat();
            logger.info("Користувач ввів щомісячну плату: {}", monthlyFee);

            System.out.print("Введіть кількість хвилин: ");
            if (!scanner.hasNextInt()) {
                logger.warn("Користувач ввів некоректний тип даних для кількості хвилин.");
                System.out.println("Некоректний ввід. Тариф не додано.");
                return;
            }
            int callMinutes = scanner.nextInt();
            logger.info("Користувач ввів кількість хвилин: {}", callMinutes);

            System.out.print("Введіть кількість гігабайт: ");
            if (!scanner.hasNextFloat()) {
                logger.warn("Користувач ввів некоректний тип даних для кількості гігабайт.");
                System.out.println("Некоректний ввід. Тариф не додано.");
                return;
            }
            float gigabytes = scanner.nextFloat();
            logger.info("Користувач ввів кількість гігабайт: {}", gigabytes);

            scanner.nextLine(); // очищення буфера

            if (choice == 1) {
                service.addTariff(new TariffPrepaid(name, monthlyFee, callMinutes, gigabytes));
                logger.info("Передплачений тариф '{}' успішно додано.", name);
                System.out.println("Передплачений тариф успішно додано.");
            } else if (choice == 2) {
                System.out.print("Введіть додаткові послуги: ");
                String services = scanner.nextLine();
                logger.info("Користувач ввів додаткові послуги: '{}'", services);

                service.addTariff(new TariffContract(name, monthlyFee, callMinutes, gigabytes, services));
                logger.info("Контрактний тариф '{}' успішно додано.", name);
                System.out.println("Контрактний тариф успішно додано.");
            }
        } catch (Exception e) {
            logger.error("Сталася помилка під час додавання тарифу: ", e);
        } finally {
            logger.info("Завершено виконання команди 'Додати тариф'.");
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