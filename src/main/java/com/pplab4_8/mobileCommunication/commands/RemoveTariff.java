package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Команда для видалення тарифу з мобільної мережі.
 * Дозволяє видалити тариф за його назвою.
 */
public class RemoveTariff implements Command {
    private static final Logger logger = LogManager.getLogger(RemoveTariff.class);
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
        logger.info("Початок виконання команди 'Видалити тариф'.");
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("\nВведіть назву тарифу для видалення: ");
            if (!scanner.hasNextLine()) {
                logger.warn("Користувач не ввів назву тарифу.");
                System.out.println("Помилка: введення завершено.");
                return;
            }

            String name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                logger.warn("Введено порожню назву тарифу.");
                System.out.println("Помилка: введена назва тарифу порожня.");
                return;
            }

            logger.info("Користувач ввів назву тарифу для видалення: '{}'", name);

            TariffPrepaid tariff = service.searchTariffByName(name);
            if (tariff != null) {
                logger.info("Тариф знайдено: {}", tariff);
                service.removeTariff(tariff);
                logger.info("Тариф '{}' успішно видалено.", name);
                System.out.println("Тариф успішно видалено.");
            } else {
                logger.warn("Тариф з назвою '{}' не знайдено.", name);
                System.out.println("Тариф не знайдено.");
            }
        } catch (Exception e) {
            logger.error("Виникла помилка під час виконання команди 'Видалити тариф'.", e);
        } finally {
            scanner.close();
            logger.info("Завершення виконання команди 'Видалити тариф'.");
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