package com.pplab4_8.mobileCommunication.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    static class TestCommand implements Command {
        private boolean executed = false;

        @Override
        public void execute() {
            executed = true;
        }

        public boolean isExecuted() {
            return executed;
        }
    }

    @Test
    void testGetName() {
        Command command = new TestCommand();
        assertEquals("TestCommand", command.getName(), "Method getName() should return the class name");
    }

    @Test
    void testExecute() {
        TestCommand command = new TestCommand();
        command.execute();
        assertTrue(command.isExecuted(), "Method execute() should set executed to true");
    }
}