package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.tariffs.*;

import java.util.Scanner;

/**
 * Клас для команди редагування існуючого тарифу.
 * Реалізує інтерфейс Command.
 */
public class EditTariff implements Command {
    private MobileNetworkService service; // Сервіс для управління тарифами

    /**
     * Конструктор для ініціалізації команди з посиланням на сервіс.
     *
     * @param service сервіс для управління мобільними тарифами
     */
    public EditTariff(MobileNetworkService service) {
        this.service = service;
    }

    /**
     * Метод виконання команди редагування тарифу.
     * Користувач вводить назву існуючого тарифу та оновлює його параметри.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        // Запит назви тарифу для редагування
        System.out.print("\nВведіть назву тарифу для редагування: ");
        String name = scanner.nextLine();

        // Пошук тарифу за назвою
        TariffPrepaid oldTariff = service.searchTariffByName(name);

        // Перевірка, чи знайдено тариф
        if (oldTariff == null) {
            System.out.println("Тариф не знайдено.");
            return;
        }

        // Зчитування нових параметрів тарифу (або залишення старих значень)
        System.out.print("Введіть нову назву (або залиште порожнім): ");
        String newName = scanner.nextLine();
        if (newName.isEmpty()) newName = oldTariff.getName();

        System.out.print("Введіть нову абонплату (або залиште 0): ");
        float newFee = scanner.nextFloat();
        if (newFee == 0) newFee = oldTariff.getMonthlyFee();

        System.out.print("Введіть нову кількість хвилин (або залиште 0): ");
        int newMinutes = scanner.nextInt();
        if (newMinutes == 0) newMinutes = oldTariff.getCallMinutesCount();

        System.out.print("Введіть нову кількість гігабайт (або залиште 0): ");
        float newGigabytes = scanner.nextFloat();
        if (newGigabytes == 0) newGigabytes = oldTariff.getInternetGigabytesCount();

        scanner.nextLine(); // Очищення буфера

        TariffPrepaid updatedTariff;

        // Якщо тариф є контрактним, оновлюємо додаткові послуги
        if (oldTariff instanceof TariffContract contract) {
            System.out.print("Введіть нові додаткові послуги (або залиште порожнім): ");
            String newServices = scanner.nextLine();
            if (newServices.isEmpty()) newServices = contract.getAdditionalServices();

            // Створення нового об'єкта контрактного тарифу
            updatedTariff = new TariffContract(newName, newFee, newMinutes, newGigabytes, newServices);
        } else {
            // Створення нового об'єкта передплаченого тарифу
            updatedTariff = new TariffPrepaid(newName, newFee, newMinutes, newGigabytes);
        }

        // Оновлення тарифу в сервісі
        service.editTariff(oldTariff, updatedTariff);
        System.out.println("Тариф успішно оновлено.");
    }

    /**
     * Метод для отримання назви команди.
     *
     * @return Назва команди для відображення у меню
     */
    @Override
    public String getName() {
        return "Редагувати тариф";
    }
}