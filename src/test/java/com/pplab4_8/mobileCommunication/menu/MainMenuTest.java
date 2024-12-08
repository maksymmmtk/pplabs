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

public class MainMenuTest {
    private Scanner scanner;
    private MainMenu mainMenu;
    private Map<Integer, Command> commands;
    
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

    @BeforeEach
    public void setUp() {
        commands = new HashMap<>();
        commands.put(1, new DummyCommand("Command 1"));
        commands.put(2, new DummyCommand("Command 2"));
        commands.put(3, new DummyCommand("Command 3"));
    }

    @Test
    public void testShowMenu() {
        mainMenu = new MainMenu(commands, 1, scanner);  
        mainMenu.showMenu("Мобільний оператор");
    }

    @Test
    public void testExecuteValidChoice() {
        String input = "1\n0\n";
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

    @Test
    public void testExecuteInvalidChoice() {
        String input = "99\n0\n";
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

    @Test
    public void testExecuteExit() {
        String input = "0\n";
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

    @Test
    public void testExecuteNonNumericInput() {
        String input = "abc\n0\n";
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