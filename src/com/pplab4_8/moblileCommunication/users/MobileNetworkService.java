package com.pplab4_8.moblileCommunication.users;

import com.pplab4_8.moblileCommunication.tariffs.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Клас MobileNetworkService управляє мобільними тарифами.
 * Він дозволяє додавати, редагувати, видаляти тарифи, а також переглядати їх.
 */
public class MobileNetworkService {
    private String name;
    private ArrayList<TariffPrepaid> tariffs;

    /**
     * Конструктор для ініціалізації сервісу мобільної мережі.
     *
     * @param name Назва мобільного сервісу.
     * @param tariffs Список тарифів.
     */
    public MobileNetworkService(String name, ArrayList<TariffPrepaid> tariffs) {
        this.name = name;
        this.tariffs = tariffs != null ? tariffs : new ArrayList<>();
    }

    /**
     * Отримує назву мобільного сервісу.
     *
     * @return Назва сервісу.
     */
    public String getName() {
        return name;
    }

    /**
     * Отримує тариф за його назвою.
     *
     * @param name Назва тарифу.
     * @return Тариф або null, якщо тариф не знайдений.
     */
    public TariffPrepaid getTariff(String name) {
        for (TariffPrepaid tariff : tariffs) {
            if (tariff.getName().equals(name)) {
                return tariff;
            }
        }
        return null;
    }

    /**
     * Отримує список всіх тарифів.
     *
     * @return Список тарифів.
     */
    public List<TariffPrepaid> getTariffs() {
        return tariffs;
    }

    /**
     * Встановлює нову назву мобільного сервісу.
     *
     * @param name Нова назва сервісу.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Встановлює новий список тарифів.
     *
     * @param tariffs Новий список тарифів.
     */
    public void setTariffs(ArrayList<TariffPrepaid> tariffs) {
        this.tariffs = tariffs;
    }

    /**
     * Додає новий тариф до списку тарифів.
     * Користувач може вибрати між передплаченим і контрактним тарифом.
     */
    public void addTariff() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nДодавання нового тарифу:");

        System.out.println("Виберіть тип тарифу для додавання:");
        System.out.println("1. Передплачений тариф (Prepaid)");
        System.out.println("2. Контрактний тариф (Contract)");
        System.out.print("Ваш вибір: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // очищення буфера

        System.out.print("Введіть назву тарифу: ");
        String name = scanner.nextLine();

        System.out.print("Введіть щомісячну плату: ");
        float monthlyFee = scanner.nextFloat();

        System.out.print("Введіть кількість хвилин: ");
        int callMinutesCount = scanner.nextInt();

        System.out.print("Введіть кількість гігабайт: ");
        float internetGigabytesCount = scanner.nextFloat();

        if (choice == 1) {
            TariffPrepaid newTariff = new TariffPrepaid(name, monthlyFee, callMinutesCount, internetGigabytesCount);
            tariffs.add(newTariff);
            System.out.println("Передплачений тариф успішно додано.");
        } else if (choice == 2) {
            scanner.nextLine(); // очищення буфера
            System.out.print("Введіть додаткові послуги: ");
            String additionalServices = scanner.nextLine();

            TariffContract newTariff = new TariffContract(name, monthlyFee, callMinutesCount, internetGigabytesCount, additionalServices);
            tariffs.add(newTariff);
            System.out.println("Контрактний тариф успішно додано.");
        } else {
            System.out.println("Невірний вибір. Тариф не додано.");
        }
    }

    /**
     * Редагує існуючий тариф.
     * Дає можливість змінювати дані тарифу, такі як назва, абонплата, хвилини, гігабайти та додаткові послуги.
     */
    public void editTariff() {
        TariffPrepaid tariff = searchTariff();

        if (tariff != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nРедагування тарифу: " + tariff.getName());
            System.out.print("Введіть нову назву тарифу (або залиште порожнім для збереження старої): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) tariff.setName(name);

            System.out.print("Введіть нову щомісячну плату (або залиште 0 для збереження старої): ");
            float monthlyFee = scanner.nextFloat();
            if (monthlyFee != 0) tariff.setMonthlyFee(monthlyFee);

            System.out.print("Введіть нову кількість хвилин (або залиште 0 для збереження старої): ");
            int callMinutes = scanner.nextInt();
            if (callMinutes != 0) tariff.setCallMinutesCount(callMinutes);

            System.out.print("Введіть нову кількість гігабайт (або залиште 0 для збереження старої): ");
            float internetGigabytes = scanner.nextFloat();
            if (internetGigabytes != 0) tariff.setInternetGigabytesCount(internetGigabytes);

            if (tariff instanceof TariffContract) {
                scanner.nextLine(); // очищення буфера
                System.out.print("Введіть нові додаткові послуги: ");
                String newServices = scanner.nextLine();
                ((TariffContract) tariff).setAdditionalServices(newServices);
            }

            System.out.println("Тариф успішно оновлено.");
        } else {
            System.out.println("Тариф не знайдено.");
        }
    }

    /**
     * Видаляє тариф з списку.
     * Перш ніж видалити, здійснюється пошук тарифу за назвою.
     */
    public void removeTariff() {
        TariffPrepaid tariff = searchTariff();

        if (tariff != null) {
            tariffs.remove(tariff);
            System.out.println("Тариф \"" + tariff.getName() + "\" успішно видалено.");
        } else {
            System.out.println("Тариф не знайдено.");
        }
    }

    /**
     * Переглядає список тарифів та надає користувачу можливість виконати додаткові операції.
     */
    public void viewTariffs() {
        if (tariffs.isEmpty()) {
            System.out.println("Список тарифів порожній.");
            return;
        }

        System.out.println("\nДоступні тарифи:");
        for (TariffPrepaid tariff : tariffs) {
            System.out.println(tariff);
        }

        System.out.println("\nВиберіть дію:");
        System.out.println("1. Підрахувати загальну кількість клієнтів");
        System.out.println("2. Відсортувати тарифи за абонплатою");
        System.out.println("3. Завершити перегляд");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> calculateTotalClients();
            case 2 -> sortTariffsByFee();
            case 3 -> System.out.println("Перегляд тарифів завершено.");
            default -> System.out.println("Неправильний вибір. Спробуйте ще раз.");
        }
    }

    /**
     * Підраховує та виводить загальну кількість клієнтів для всіх тарифів.
     */
    public void calculateTotalClients() {
        int totalClients = tariffs.stream().mapToInt(TariffPrepaid::getSubscribersCount).sum();
        System.out.println("Загальна кількість клієнтів: " + totalClients);
    }

    /**
     * Сортує тарифи за абонплатою в порядку зростання.
     */
    public void sortTariffsByFee() {
        tariffs.sort(Comparator.comparingDouble(TariffPrepaid::getMonthlyFee));
        System.out.println("Тарифи успішно відсортовані за абонплатою.");
    }

    /**
     * Шукає тариф за його назвою.
     *
     * @return Тариф або null, якщо тариф не знайдений.
     */
    public TariffPrepaid searchTariff() {
        if (tariffs.isEmpty()) {
            System.out.println("Список тарифів порожній.");
            return null;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nВведіть назву тарифу для пошуку: ");
        String name = scanner.nextLine();

        for (TariffPrepaid tariff : tariffs) {
            if (tariff.getName().equalsIgnoreCase(name)) {
                return tariff;
            }
        }
        System.out.println("Тариф із назвою \"" + name + "\" не знайдено.");
        return null;
    }
}