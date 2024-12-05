package com.pplab4_8.mobileCommunication.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовий клас для перевірки функціональності команд, що реалізують інтерфейс {@link Command}.
 * Цей клас включає тести для методів {@link Command#getName()} та {@link Command#execute()}.
 */
class CommandTest {

    /**
     * Тестова команда для перевірки основних методів інтерфейсу {@link Command}.
     * Ця команда просто змінює стан змінної executed на true під час виконання.
     */
    static class TestCommand implements Command {
        private boolean executed = false;

        /**
         * Виконує команду, змінюючи значення змінної executed на true.
         */
        @Override
        public void execute() {
            executed = true;
        }

        /**
         * Повертає значення змінної executed.
         *
         * @return true, якщо команда була виконана, інакше false.
         */
        public boolean isExecuted() {
            return executed;
        }
    }

    /**
     * Тест для методу {@link Command#getName()}.
     * Перевіряє, чи метод повертає правильне ім'я класу.
     */
    @Test
    void testGetName() {
        Command command = new TestCommand();
        assertEquals("TestCommand", command.getName(), "Method getName() should return the class name");
    }

    /**
     * Тест для методу {@link Command#execute()}.
     * Перевіряє, чи правильно працює метод execute, змінюючи стан змінної executed.
     */
    @Test
    void testExecute() {
        TestCommand command = new TestCommand();
        command.execute();
        assertTrue(command.isExecuted(), "Method execute() should set executed to true");
    }
}