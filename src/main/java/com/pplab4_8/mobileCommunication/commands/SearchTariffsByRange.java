package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.users.MobileSubscriber;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Команда для пошуку тарифу в заданому діапазоні абонплати.
 * Після знаходження тарифу пропонує користувачеві підключити його.
 */
public class SearchTariffsByRange implements Command {
    private static final Logger logger = LogManager.getLogger(SearchTariffsByRange.class);
    private final MobileSubscriber subscriber;
    private final MobileNetworkService service;

    /**
     * Конструктор класу SearchTariffsByRange.
     *
     * @param subscriber абонент, який виконує пошук і може підключити знайдений тариф.
     * @param service    сервіс мобільної мережі для пошуку тарифів.
     */
    public SearchTariffsByRange(MobileSubscriber subscriber, MobileNetworkService service) {
        this.subscriber = subscriber;
        this.service = service;
    }

    /**
     * Виконує пошук тарифу в заданому діапазоні абонплати.
     * Якщо тариф знайдено, користувачеві пропонується підключити його.
     */
    @Override
    public void execute() {
        logger.info("Початок виконання команди 'Знайти тариф'.");

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("\nВведіть мінімальну абонплату: ");
            float minFee = scanner.nextFloat();
            logger.info("Користувач ввів мінімальну абонплату: {}", minFee);

            System.out.print("Введіть максимальну абонплату: ");
            float maxFee = scanner.nextFloat();
            logger.info("Користувач ввів максимальну абонплату: {}", maxFee);

            // Пошук тарифу у вказаному діапазоні
            TariffPrepaid foundTariff = service.searchTariffsByRange(minFee, maxFee);
            if (foundTariff == null) {
                logger.warn("Тариф у заданому діапазоні не знайдено. Мін. абонплата: {}, Макс. абонплата: {}", minFee, maxFee);
                System.out.println("Тариф у заданому діапазоні не знайдено.");
                return;
            }

            logger.info("Знайдено тариф: {}", foundTariff);
            System.out.println("Знайдено тариф:");
            System.out.println(foundTariff);

            // Пропозиція підключити знайдений тариф
            System.out.print("Підключити цей тариф? (1 - так, 2 - ні): ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                subscriber.setTariff(foundTariff);
                logger.info("Користувач підключив тариф: {}", foundTariff);
                System.out.println("Тариф успішно підключено.");
            } else {
                logger.info("Користувач відмовився від підключення тарифу.");
                System.out.println("Тариф не змінено.");
            }
        } catch (Exception e) {
            logger.error("Виникла помилка під час виконання команди 'Знайти тариф'.", e);
        } finally {
            logger.info("Завершення виконання команди 'Знайти тариф'.");
        }
    }

    /**
     * Отримує назву команди.
     *
     * @return назва команди.
     */
    @Override
    public String getName() {
        return "Знайти тариф";
    }
}