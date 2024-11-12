package com.pplab4_8.moblileCommunication.menu;

import java.util.Scanner;
import com.pplab4_8.moblileCommunication.command.Command;

/**
 * Клас Menu реалізує меню для мобільного оператора та абонента,
 * де користувач може вибирати різні операції, пов'язані з тарифами.
 */
public class Menu {
    private Command addTariffCommand;
    private Command editTariffCommand;
    private Command removeTariffCommand;
    private Command viewTariffsCommand;
    private Command searchTariffCommand;
    private Command viewPersonalTariffCommand;

    /**
     * Конструктор для ініціалізації меню для мобільного оператора.
     *
     * @param addTariffCommand команда для додавання тарифу
     * @param editTariffCommand команда для редагування тарифу
     * @param removeTariffCommand команда для видалення тарифу
     * @param viewTariffsCommand команда для перегляду тарифів
     */
    public Menu(Command addTariffCommand, Command editTariffCommand, Command removeTariffCommand, Command viewTariffsCommand) {
        this.addTariffCommand = addTariffCommand;
        this.editTariffCommand = editTariffCommand;
        this.removeTariffCommand = removeTariffCommand;
        this.viewTariffsCommand = viewTariffsCommand;
    }

    /**
     * Конструктор для ініціалізації меню для абонента.
     *
     * @param viewPersonalTariffCommand команда для перегляду особистого тарифу
     * @param viewTariffsCommand команда для перегляду всіх тарифів
     * @param searchTariffsCommand команда для пошуку тарифу
     */
    public Menu(Command viewPersonalTariffCommand, Command viewTariffsCommand, Command searchTariffsCommand) {
        this.viewPersonalTariffCommand = viewPersonalTariffCommand;
        this.viewTariffsCommand = viewTariffsCommand;
        this.searchTariffCommand = searchTariffsCommand;
    }

    /**
     * Виводить меню для мобільного оператора та обробляє введення користувача.
     * Оператор може додавати, редагувати, видаляти або переглядати тарифи.
     */
    public void showOperatorMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n---------- Мобільний оператор ----------");
            System.out.println("1. Додати тариф");
            System.out.println("2. Редагувати тариф");
            System.out.println("3. Видалити тариф");
            System.out.println("4. Переглянути тарифи");
            System.out.println("5. Вийти");
            System.out.print("Виберіть опцію: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addTariffCommand.execute();  // Виконується команда для додавання тарифу
                    break;
                case 2:
                    editTariffCommand.execute();  // Виконується команда для редагування тарифу
                    break;
                case 3:
                    removeTariffCommand.execute();  // Виконується команда для видалення тарифу
                    break;
                case 4:
                    viewTariffsCommand.execute();  // Виконується команда для перегляду тарифів
                    break;
                case 5:
                    System.out.println("Вихід...");
                    break;
                default:
                    System.out.println("Невірна опція. Спробуйте ще раз.");
            }
        } while (choice != 5);
    }

    /**
     * Виводить меню для абонента та обробляє введення користувача.
     * Абонент може шукати тариф, переглядати свій особистий тариф або всі доступні тарифи.
     */
    public void showSubscriberMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n---------- Абонент ----------");
            System.out.println("1. Знайти тариф");
            System.out.println("2. Переглянути особистий тариф");
            System.out.println("3. Переглянути наявні тарифи");
            System.out.println("4. Вийти");
            System.out.print("Виберіть опцію: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    searchTariffCommand.execute();  // Виконується команда для пошуку тарифу
                    break;
                case 2:
                    viewPersonalTariffCommand.execute();  // Виконується команда для перегляду особистого тарифу
                    break;
                case 3:
                    viewTariffsCommand.execute();  // Виконується команда для перегляду всіх тарифів
                    break;
                case 4:
                    System.out.println("Вихід...");
                    break;
                default:
                    System.out.println("Невірна опція. Спробуйте ще раз.");
            }
        } while (choice != 4);
    }
}