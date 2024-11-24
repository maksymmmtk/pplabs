package com.pplab4_8.moblileCommunication.menu;

import com.pplab4_8.moblileCommunication.command.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu implements Command {
    private final Map<Integer, Command> commands = new HashMap<>();
    private String role;

    public MainMenu(Map<Integer, Command> commands, Integer role) {
        this.commands.putAll(commands);
        if (role == 1) this.role = "Мобільний оператор";
        else this.role = "Абонент";
    }

    public void showMenu(String role) {
        System.out.println("\n---------- " + role + "----------");
        for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }
    }

    @Override
    public void execute() {
        while (true) {
            showMenu(role);
            System.out.print("Введіть номер команди для виконання (0 для повернення назад): ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            Command command = commands.get(choice);
            if (command != null) {
                command.execute();
            } else if (choice == 0) {
                System.out.println("Вихід з меню.");
                break;
            } else {
                System.out.println("Невірна команда. Спробуйте ще раз.");
            }
        }
    }
}