package com.pplab4_8.mobileCommunication.menu;

import com.pplab4_8.mobileCommunication.commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Клас MainMenu представляє головне меню, яке дає змогу вибирати та виконувати команди залежно від ролі користувача.
 */
public class MainMenu implements Command {
    private final Map<Integer, Command> commands = new HashMap<>();
    private String role;
    private Scanner scanner;

    /**
     * Конструктор MainMenu.
     *
     * @param commands мапа, яка відображає номер команди до об'єкта {@code Command}.
     * @param role     роль користувача (1 для оператора, 2 для абонента).
     * @param scanner  сканер для читання вводу користувача.
     */
    public MainMenu(Map<Integer, Command> commands, Integer role, Scanner scanner) {
        this.commands.putAll(commands);
        if (role == 1) this.role = "Мобільний оператор";
        else this.role = "Абонент";
        this.scanner = scanner;
    }

    /**
     * Повертає мапу команд, доступних у меню.
     *
     * @return мапа команд.
     */
    public Map<Integer, Command> getCommands() {
        return commands;
    }

    /**
     * Повертає роль користувача.
     *
     * @return рядок, що представляє роль ("Мобільний оператор" або "Абонент").
     */
    public String getRole() {
        return role;
    }

    /**
     * Відображає доступні команди в меню для відповідної ролі.
     *
     * @param role роль користувача.
     */
    public void showMenu(String role) {
        System.out.println("\n---------- " + role + " ----------");
        for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }
    }

    /**
     * Основний цикл виконання меню.
     * Дозволяє користувачу вибирати команди для виконання або вийти з меню.
     */
    @Override
    public void execute() {
        while (true) {
            showMenu(role); // Відображення меню.
            System.out.print("Введіть номер команди для виконання (0 для повернення назад): ");

            if (scanner.hasNextInt()) { // Перевірка, чи введено число.
                int choice = scanner.nextInt();
                Command command = commands.get(choice); // Отримання команди за номером.
                if (command != null) {
                    command.execute(); // Виконання вибраної команди.
                } else if (choice == 0) {
                    System.out.println("Вихід з меню.");
                    break; // Вихід з циклу.
                } else {
                    System.out.println("Невірна команда. Спробуйте ще раз.");
                }
            } else {
                System.out.println("Будь ласка, введіть числове значення.");
                scanner.next(); // Очищення некоректного вводу.
            }
        }
    }
}