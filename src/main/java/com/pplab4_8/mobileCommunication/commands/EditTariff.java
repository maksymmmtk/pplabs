package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Команда для редагування існуючого тарифу.
 * Дозволяє змінювати назву, абонплату, кількість хвилин, гігабайтів і додаткові послуги тарифу.
 */
public class EditTariff implements Command {
    private static final Logger logger = LogManager.getLogger(EditTariff.class);
    private MobileNetworkService service;

    /**
     * Конструктор класу EditTariff.
     *
     * @param service сервіс мобільної мережі, в якому редагуються тарифи.
     */
    public EditTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Виконує редагування існуючого тарифу.
     * Користувач вводить дані через консоль.
     */
    @Override
    public void execute() {
        logger.info("Початок виконання команди 'Редагувати тариф'.");
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("\nВведіть назву тарифу для редагування: ");
            if (!scanner.hasNextLine()) {
                logger.warn("Користувач не ввів назву тарифу.");
                System.out.println("Помилка: введення завершено.");
                return;
            }
            String name = scanner.nextLine();
            logger.info("Користувач ввів назву тарифу для редагування: '{}'", name);

            TariffPrepaid oldTariff = service.searchTariffByName(name);
            if (oldTariff == null) {
                logger.warn("Тариф з назвою '{}' не знайдено.", name);
                System.out.println("Тариф не знайдено.");
                return;
            }

            logger.info("Редагування тарифу: {}", oldTariff);

            System.out.print("Введіть нову назву (або залиште порожнім): ");
            String newName = scanner.hasNextLine() ? scanner.nextLine() : "";
            if (newName.isEmpty()) newName = oldTariff.getName();
            logger.info("Встановлено нову назву тарифу: '{}'", newName);

            float newFee = getValidFloat(scanner, "Введіть нову абонплату (або залиште 0): ", oldTariff.getMonthlyFee());
            logger.info("Встановлено нову абонплату: {}", newFee);

            int newMinutes = getValidInt(scanner, "Введіть нову кількість хвилин (або залиште 0): ", oldTariff.getCallMinutesCount());
            logger.info("Встановлено нову кількість хвилин: {}", newMinutes);

            float newGigabytes = getValidFloat(scanner, "Введіть нову кількість гігабайт (або залиште 0): ", oldTariff.getInternetGigabytesCount());
            logger.info("Встановлено нову кількість гігабайт: {}", newGigabytes);

            TariffPrepaid updatedTariff;
            if (oldTariff instanceof TariffContract contract) {
                System.out.print("Введіть нові додаткові послуги (або залиште порожнім): ");
                String newServices = scanner.hasNextLine() ? scanner.nextLine() : "";
                if (newServices.isEmpty()) newServices = contract.getAdditionalServices();
                updatedTariff = new TariffContract(newName, newFee, newMinutes, newGigabytes, newServices);
                logger.info("Встановлено нові додаткові послуги: {}", newServices);
            } else {
                updatedTariff = new TariffPrepaid(newName, newFee, newMinutes, newGigabytes);
            }

            service.editTariff(oldTariff, updatedTariff);
            logger.info("Тариф успішно оновлено: {}", updatedTariff);
            System.out.println("Тариф успішно оновлено.");
        } catch (Exception e) {
            logger.error("Виникла помилка під час виконання команди 'Редагувати тариф'.", e);
        } finally {
            scanner.close();
            logger.info("Завершення виконання команди 'Редагувати тариф'.");
        }
    }

    private float getValidFloat(Scanner scanner, String message, float defaultValue) {
        while (true) {
            System.out.print(message);
            if (!scanner.hasNextLine()) {
                logger.warn("Користувач не ввів дійсне число.");
                System.out.println("Помилка: введення завершено.");
                return defaultValue;
            }
            String input = scanner.nextLine();
            if (input.isEmpty()) return defaultValue;
            try {
                float value = Float.parseFloat(input);
                return value == 0 ? defaultValue : value;
            } catch (NumberFormatException e) {
                logger.warn("Помилка перетворення '{}', очікувалося дійсне число.", input);
                System.out.println("Помилка: введіть дійсне число.");
            }
        }
    }

    private int getValidInt(Scanner scanner, String message, int defaultValue) {
        while (true) {
            System.out.print(message);
            if (!scanner.hasNextLine()) {
                logger.warn("Користувач не ввів ціле число.");
                System.out.println("Помилка: введення завершено.");
                return defaultValue;
            }
            String input = scanner.nextLine();
            if (input.isEmpty()) return defaultValue;
            try {
                int value = Integer.parseInt(input);
                return value == 0 ? defaultValue : value;
            } catch (NumberFormatException e) {
                logger.warn("Помилка перетворення '{}', очікувалося ціле число.", input);
                System.out.println("Помилка: введіть ціле число.");
            }
        }
    }

    @Override
    public String getName() {
        return "Редагувати тариф";
    }
}