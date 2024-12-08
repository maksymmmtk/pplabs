package com.pplab4_8.mobileCommunication.menu;

import com.pplab4_8.mobileCommunication.commands.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu implements Command {

    private static final Logger logger = LogManager.getLogger(MainMenu.class);

    private final Map<Integer, Command> commands = new HashMap<>();
    private String role;
    private Scanner scanner;

    public MainMenu(Map<Integer, Command> commands, Integer role, Scanner scanner) {
        this.commands.putAll(commands);
        if (role == 1) this.role = "Мобільний оператор";
        else this.role = "Абонент";
        this.scanner = scanner;

        logger.info("MainMenu ініціалізовано для ролі: '{}'. Доступно {} команд.", this.role, commands.size());
    }

    public Map<Integer, Command> getCommands() {
        return commands;
    }

    public String getRole() {
        return role;
    }

    public void showMenu(String role) {
        System.out.println("\n---------- " + role + " ----------");
        for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }
        logger.info("Відображено меню для ролі '{}'.", role);
    }

    @Override
    public void execute() {
        logger.info("Виконання MainMenu для ролі '{}' розпочато.", role);
        while (true) {
            showMenu(role);
            System.out.print("Введіть номер команди для виконання (0 для повернення назад): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                Command command = commands.get(choice);

                if (command != null) {
                    logger.info("Користувач обрав команду №{}: '{}'.", choice, command.getName());
                    command.execute();
                } else if (choice == 0) {
                    logger.info("Користувач вибрав вихід з меню.");
                    System.out.println("Вихід з меню.");
                    break;
                } else {
                    logger.warn("Користувач обрав невірну команду №{}.", choice);
                    System.out.println("Невірна команда. Спробуйте ще раз.");
                }
            } else {
                logger.warn("Користувач ввів нечислове значення.");
                System.out.println("Будь ласка, введіть числове значення.");
                scanner.next(); // Пропустити некоректне введення
            }
        }
        logger.info("Виконання MainMenu для ролі '{}' завершено.", role);
    }
}