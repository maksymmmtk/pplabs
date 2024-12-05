package com.pplab4_8.mobileCommunication.menu;

import com.pplab4_8.mobileCommunication.commands.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовий клас для перевірки функціональності головного меню.
 * Перевіряє, чи правильно працюють різні сценарії виконання команд.
 */
public class MainMenuTest {
    private Scanner scanner;
    private MainMenu mainMenu;
    private Map<Integer, Command> commands;

    /**
     * Імітація класу команди для тестування.
     */
    static class DummyCommand implements Command {
        private final String name;

        public DummyCommand(String name) {
            this.name = name;
        }

        @Override
        public void execute() {
            System.out.println(name + " executed");
        }

        @Override
        public String getName() {
            return name;
        }
    }

    /**
     * Ініціалізація тестових даних перед виконанням кожного тесту.
     * Створюються команди, які будуть використовуватись в меню.
     */
    @BeforeEach
    public void setUp() {
        commands = new HashMap<>();
        commands.put(1, new DummyCommand("Command 1"));
        commands.put(2, new DummyCommand("Command 2"));
        commands.put(3, new DummyCommand("Command 3"));
    }

    /**
     * Тест для перевірки правильного відображення меню.
     * Перевіряється, чи коректно відображаються команди.
     */
    @Test
    public void testShowMenu() {
        mainMenu = new MainMenu(commands, 1, scanner);
        mainMenu.showMenu("Мобільний оператор");
    }

    /**
     * Тест для перевірки виконання правильної команди.
     * Перевіряється, чи правильно виконується команда за вибором користувача.
     */
    @Test
    public void testExecuteValidChoice() {
        String input = "1\n0\n"; // Вхід: вибір команди 1, вихід
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        scanner = new Scanner(System.in);
        mainMenu = new MainMenu(commands, 1, scanner);
        mainMenu.execute();

        String[] outputs = outputStream.toString().split("\n");

        assertTrue(outputs[5].contains("Command 1 executed"), "Перевірка виконання команди 1");
    }

    /**
     * Тест для перевірки обробки вибору невірної команди.
     * Перевіряється, чи система реагує на введення невірної команди (наприклад, числа, якого немає в списку).
     */
    @Test
    public void testExecuteInvalidChoice() {
        String input = "99\n0\n"; // Вхід: неправильний номер команди, вихід
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        scanner = new Scanner(System.in);
        mainMenu = new MainMenu(commands, 1, scanner);
        mainMenu.execute();

        String[] outputs = outputStream.toString().split("\n");

        assertTrue(outputs[5].contains("Невірна команда. Спробуйте ще раз."), "Перевірка на невірну команду");
    }

    /**
     * Тест для перевірки обробки виходу з меню.
     * Перевіряється, чи правильно виконується вихід при виборі команди 0.
     */
    @Test
    public void testExecuteExit() {
        String input = "0\n"; // Вхід: вибір виходу з меню
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        scanner = new Scanner(System.in);
        mainMenu = new MainMenu(commands, 1, scanner);
        mainMenu.execute();

        String[] outputs = outputStream.toString().split("\n");

        assertTrue(outputs[5].contains("Вихід з меню."), "Перевірка на вихід");
    }

    /**
     * Тест для перевірки обробки некоректного введення.
     * Перевіряється, чи система правильно реагує на введення нечислового значення.
     */
    @Test
    public void testExecuteNonNumericInput() {
        String input = "abc\n0\n"; // Вхід: некоректне значення, потім вихід
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        System.setIn(inputStream);
        System.setOut(new PrintStream(outputStream));

        scanner = new Scanner(System.in);
        mainMenu = new MainMenu(commands, 1, scanner);
        mainMenu.execute();

        String[] outputs = outputStream.toString().split("\n");

        assertTrue(outputs[5].contains("Будь ласка, введіть числове значення."), "Перевірка на некоректно введене значення");
    }
}