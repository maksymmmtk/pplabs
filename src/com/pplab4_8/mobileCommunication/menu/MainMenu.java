package com.pplab4_8.mobileCommunication.menu;

import com.pplab4_8.mobileCommunication.commands.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Клас для реалізації головного меню, яке дозволяє вибирати доступні команди для мобільного оператора або абонента.
 * Реалізує інтерфейс Command.
 */
public class MainMenu implements Command {
    private final Map<Integer, Command> commands = new HashMap<>(); // Карта команд, де ключ - номер команди, значення - команда
    private String role; // Роль користувача (мобільний оператор або абонент)

    /**
     * Конструктор для ініціалізації головного меню з командами та роллю.
     *
     * @param commands карти з командами
     * @param role роль користувача (1 - мобільний оператор, 2 - абонент)
     */
    public MainMenu(Map<Integer, Command> commands, Integer role) {
        this.commands.putAll(commands); // Ініціалізація картки команд
        // Встановлення ролі користувача
        if (role == 1) this.role = "Мобільний оператор";
        else this.role = "Абонент";
    }

    /**
     * Метод для виведення меню на екран, що містить список доступних команд.
     *
     * @param role роль користувача
     */
    public void showMenu(String role) {
        System.out.println("\n---------- " + role + " ----------");
        // Виведення списку команд
        for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }
    }

    /**
     * Метод для виконання команд з меню.
     * Програма чекає на ввід команди від користувача і виконує відповідну команду.
     */
    @Override
    public void execute() {
        while (true) {
            showMenu(role);  // Виводимо меню
            System.out.print("Введіть номер команди для виконання (0 для повернення назад): ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();  // Вибір користувача

            // Отримання вибраної команди за номером
            Command command = commands.get(choice);
            if (command != null) {
                command.execute();  // Виконання обраної команди
            } else if (choice == 0) {
                System.out.println("Вихід з меню.");  // Якщо вибір 0, виходимо з меню
                break;
            } else {
                System.out.println("Невірна команда. Спробуйте ще раз.");  // Якщо команда неправильна, запитуємо знову
            }
        }
    }
}