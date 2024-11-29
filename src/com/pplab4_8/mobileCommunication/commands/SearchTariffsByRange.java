package com.pplab4_8.mobileCommunication.commands;

import com.pplab4_8.mobileCommunication.service.MobileNetworkService;
import com.pplab4_8.mobileCommunication.users.*;
import com.pplab4_8.mobileCommunication.tariffs.TariffPrepaid;

import java.util.Scanner;

/**
 * Клас для команди пошуку тарифів за діапазоном абонплати.
 * Реалізує інтерфейс Command.
 */
public class SearchTariffsByRange implements Command {
    private final MobileSubscriber subscriber; // Абонент, який виконує пошук
    private final MobileNetworkService service; // Сервіс для управління тарифами

    /**
     * Конструктор для ініціалізації команди з абонентом та сервісом.
     *
     * @param subscriber абонент, який шукає тариф
     * @param service сервіс для управління тарифами
     */
    public SearchTariffsByRange(MobileSubscriber subscriber, MobileNetworkService service) {
        this.subscriber = subscriber;
        this.service = service;
    }

    /**
     * Метод виконання команди пошуку тарифів у заданому діапазоні абонплати.
     * Користувач вводить мінімальну та максимальну абонплату, і, якщо тариф знайдено,
     * є можливість його підключити.
     */
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        // Запит введення діапазону абонплати
        System.out.print("\nВведіть мінімальну абонплату: ");
        float minFee = scanner.nextFloat();
        System.out.print("Введіть максимальну абонплату: ");
        float maxFee = scanner.nextFloat();

        // Пошук тарифу за вказаним діапазоном
        TariffPrepaid foundTariff = service.searchTariffsByRange(minFee, maxFee);

        // Перевірка, чи знайдено тариф
        if (foundTariff == null) {
            System.out.println("Тариф у заданому діапазоні не знайдено.");
            return;
        }

        // Виведення інформації про знайдений тариф
        System.out.println("Знайдено тариф:");
        System.out.println(foundTariff);

        // Пропозиція підключити знайдений тариф
        System.out.print("Підключити цей тариф? (1 - так, 2 - ні): ");
        int choice = scanner.nextInt();
        if (choice == 1) {
            // Підключення тарифу до абонента
            subscriber.setTariff(foundTariff);
        } else {
            // Відмова від підключення
            System.out.println("Тариф не змінено.");
        }
    }

    /**
     * Метод для отримання назви команди.
     *
     * @return Назва команди для відображення у меню
     */
    @Override
    public String getName() {
        return "Знайти тариф";
    }
}