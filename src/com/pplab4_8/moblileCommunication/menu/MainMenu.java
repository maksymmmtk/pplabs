package com.pplab4_8.moblileCommunication.menu;

import com.pplab4_8.moblileCommunication.command.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Клас MainMenu реалізує інтерфейс Command і надає меню для виконання команд.
 * Меню адаптується залежно від ролі користувача (мобільний оператор або абонент).
 * Клас дозволяє користувачеві вибирати команди для виконання.
 */
public class MainMenu implements Command {
    private final Map<Integer, Command> commands = new HashMap<>();
    private String role;

    /**
     * Конструктор для ініціалізації меню з наданими командами та роллю користувача.
     *
     * @param commands Карта команд для меню.
     * @param role Роль користувача (1 - мобільний оператор, 2 - абонент).
     */
    public MainMenu(Map<Integer, Command> commands, Integer role) {
        this.commands.putAll(commands);
        if (role == 1) this.role = "Мобільний оператор";
        else this.role = "Абонент";
    }

    /**
     * Виводить меню команд для користувача в залежності від його ролі.
     *
     * @param role Роль користувача (мобільний оператор або абонент).
     */
    public void showMenu(String role) {
        System.out.println("\n---------- " + role + "----------");
        for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }
    }

    /**
     * Виконує головне меню, в якому користувач може вибрати команду для виконання.
     * Якщо вибрана команда є, вона виконується. Якщо користувач вводить невірний номер,
     * програма повідомляє про помилку і пропонує спробувати ще раз.
     * Користувач може вийти з меню, вибравши команду 0.
     */
    @Override
    public void execute() {
        while (true) {
            showMenu(role);  // Виведення меню для поточної ролі користувача
            System.out.print("Введіть номер команди для виконання (0 для повернення назад): ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();  // Вибір користувача

            Command command = commands.get(choice);
            if (command != null) {
                command.execute();  // Виконання вибраної команди
            } else if (choice == 0) {
                System.out.println("Вихід з меню.");  // Вихід з меню
                break;
            } else {
                System.out.println("Невірна команда. Спробуйте ще раз.");  // Повідомлення про помилку
            }
        }
    }
}