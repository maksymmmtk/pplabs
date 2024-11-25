package com.pplab4_8.moblileCommunication.menu;

import com.pplab4_8.moblileCommunication.command.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Клас MainMenu реалізує інтерфейс Command і відповідає за відображення головного меню
 * та обробку вибору користувача для мобільного оператора чи абонента.
 */
public class MainMenu implements Command {

    // Мапа для зберігання команд та їхніх індексів
    private final Map<Integer, Command> commands = new HashMap<>();

    // Роль користувача (мобільний оператор чи абонент)
    private String role;

    /**
     * Конструктор класу, який ініціалізує мапу команд та роль користувача.
     * @param commands мапа з команд, доступних у меню
     * @param role роль користувача (1 - мобільний оператор, 2 - абонент)
     */
    public MainMenu(Map<Integer, Command> commands, Integer role) {
        this.commands.putAll(commands);  // Додавання команд в меню
        if (role == 1) this.role = "Мобільний оператор";  // Якщо роль 1, то це мобільний оператор
        else this.role = "Абонент";  // Якщо роль 2, то це абонент
    }

    /**
     * Метод для відображення меню для вказаної ролі (мобільний оператор або абонент).
     * @param role роль користувача
     */
    public void showMenu(String role) {
        System.out.println("\n---------- " + role + "----------");
        // Виведення всіх доступних команд з мапи
        for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }
    }

    /**
     * Метод, який виконує команду згідно вибору користувача.
     * Залишає меню відкритим до того, як користувач вибере опцію для виходу (0).
     */
    @Override
    public void execute() {
        while (true) {
            // Виведення меню на екран
            showMenu(role);
            System.out.print("Введіть номер команди для виконання (0 для повернення назад): ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();  // Зчитування вибору користувача

            // Виконання вибраної команди
            Command command = commands.get(choice);
            if (command != null) {
                command.execute();  // Виконання команди
            } else if (choice == 0) {
                System.out.println("Вихід з меню.");  // Вихід з меню
                break;
            } else {
                System.out.println("Невірна команда. Спробуйте ще раз.");  // Якщо введено неправильний номер команди
            }
        }
    }
}