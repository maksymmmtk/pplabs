package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;

import java.util.Scanner;

/**
 * Команда для редагування існуючого тарифу.
 * Дозволяє змінювати назву, абонплату, кількість хвилин, гігабайтів і додаткові послуги тарифу.
 */
public class EditTariff implements Command {
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
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("\nВведіть назву тарифу для редагування: ");
            if (!scanner.hasNextLine()) {
                System.out.println("Помилка: введення завершено.");
                return;
            }
            String name = scanner.nextLine();
            TariffPrepaid oldTariff = service.searchTariffByName(name);

            if (oldTariff == null) {
                System.out.println("Тариф не знайдено.");
                return;
            }

            System.out.print("Введіть нову назву (або залиште порожнім): ");
            String newName = scanner.hasNextLine() ? scanner.nextLine() : "";
            if (newName.isEmpty()) newName = oldTariff.getName();

            float newFee = getValidFloat(scanner, "Введіть нову абонплату (або залиште 0): ", oldTariff.getMonthlyFee());
            int newMinutes = getValidInt(scanner, "Введіть нову кількість хвилин (або залиште 0): ", oldTariff.getCallMinutesCount());
            float newGigabytes = getValidFloat(scanner, "Введіть нову кількість гігабайт (або залиште 0): ", oldTariff.getInternetGigabytesCount());

            TariffPrepaid updatedTariff;
            if (oldTariff instanceof TariffContract contract) {
                System.out.print("Введіть нові додаткові послуги (або залиште порожнім): ");
                String newServices = scanner.hasNextLine() ? scanner.nextLine() : "";
                if (newServices.isEmpty()) newServices = contract.getAdditionalServices();

                updatedTariff = new TariffContract(newName, newFee, newMinutes, newGigabytes, newServices);
            } else {
                updatedTariff = new TariffPrepaid(newName, newFee, newMinutes, newGigabytes);
            }

            service.editTariff(oldTariff, updatedTariff);
            System.out.println("Тариф успішно оновлено.");
        } finally {
            scanner.close();
        }
    }

    /**
     * Отримує дійсне число з введення користувача або повертає значення за замовчуванням.
     *
     * @param scanner      об'єкт Scanner для читання введення.
     * @param message      повідомлення для користувача.
     * @param defaultValue значення за замовчуванням.
     * @return введене дійсне число або значення за замовчуванням.
     */
    private float getValidFloat(Scanner scanner, String message, float defaultValue) {
        while (true) {
            System.out.print(message);
            if (!scanner.hasNextLine()) {
                System.out.println("Помилка: введення завершено.");
                return defaultValue;
            }
            String input = scanner.nextLine();
            if (input.isEmpty()) return defaultValue;
            try {
                float value = Float.parseFloat(input);

                return value == 0 ? defaultValue : value;
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть дійсне число.");
            }
        }
    }

    /**
     * Отримує ціле число з введення користувача або повертає значення за замовчуванням.
     *
     * @param scanner      об'єкт Scanner для читання введення.
     * @param message      повідомлення для користувача.
     * @param defaultValue значення за замовчуванням.
     * @return введене ціле число або значення за замовчуванням.
     */
    private int getValidInt(Scanner scanner, String message, int defaultValue) {
        while (true) {
            System.out.print(message);
            if (!scanner.hasNextLine()) {
                System.out.println("Помилка: введення завершено.");
                return defaultValue;
            }
            String input = scanner.nextLine();
            if (input.isEmpty()) return defaultValue;
            try {
                int value = Integer.parseInt(input);

                return value == 0 ? defaultValue : value;
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть ціле число.");
            }
        }
    }

    /**
     * Отримує назву команди.
     *
     * @return назва команди.
     */
    @Override
    public String getName() {
        return "Редагувати тариф";
    }
}