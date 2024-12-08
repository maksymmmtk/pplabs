package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Команда для перегляду всіх доступних тарифів.
 * Додатково дозволяє підрахувати кількість клієнтів або відсортувати тарифи за абонплатою.
 */
public class ViewTariffs implements Command {
    private static final Logger logger = LogManager.getLogger(ViewTariffs.class);
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
        logger.info("Виконання команди 'Переглянути наявні тарифи'");

        var tariffs = service.viewTariffs();
        if (tariffs.isEmpty()) {
            logger.warn("Список тарифів порожній.");
            System.out.println("Список тарифів порожній.");
            return;
        }

        logger.info("Виведення списку тарифів.");
        System.out.println("\nСписок тарифів:");
        tariffs.forEach(System.out::println);

        System.out.println("\n1. Підрахувати клієнтів");
        System.out.println("2. Відсортувати за абонплатою");
        System.out.println("3. Завершити");
        Scanner scanner = new Scanner(System.in);

        // Виконання вибору дії
        int choice = scanner.nextInt();
        logger.info("Користувач вибрав опцію: {}", choice);

        switch (choice) {
            case 1:
                logger.info("Підрахунок клієнтів.");
                System.out.println("Клієнтів: " + service.calculateTotalClients());
                break;
            case 2:
                logger.info("Сортування тарифів за абонплатою.");
                service.sortTariffsByFee();
                System.out.println("Тарифи відсортовано.");
                break;
            default:
                logger.info("Завершення роботи команди.");
                System.out.println("Завершено.");
                break;
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