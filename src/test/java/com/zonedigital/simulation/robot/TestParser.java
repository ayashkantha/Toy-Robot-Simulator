package com.zonedigital.simulation.robot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestParser {
    private final static ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();

    @BeforeAll
    public static void init() {
        System.setOut(new PrintStream(consoleOut));
    }

    @Test
    @DisplayName("Place Command")
    public void testPlaceCommand() {
        try {
            Command command = CommandParser.parseCommand("place 0, 0, east");

            if (command == null) {
                Assertions.fail();
            }

            Assertions.assertEquals(Command.Key.PLACE, command.getKey(), "Wrong command has been detected");
            Assertions.assertEquals(3, command.getArgs().size(), "Wrong number of args has been detected");
            Assertions.assertEquals("0", command.getArgs().get(0), "Wrong first arg has been detected");
            Assertions.assertEquals("0", command.getArgs().get(1), "Wrong second arg has been detected");
            Assertions.assertEquals("EAST", command.getArgs().get(2), "Wrong third arg has been detected");
        } catch (CommandException e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Move Command")
    public void testMoveCommand() {
        try {
            Command command = CommandParser.parseCommand("move");

            if (command == null) {
                Assertions.fail();
            }

            Assertions.assertEquals(Command.Key.MOVE, command.getKey(), "Wrong command has been detected");
            Assertions.assertEquals(0, command.getArgs().size(), "Wrong number of args has been detected");
        } catch (CommandException e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Right Command")
    public void testRightCommand() {
        try {
            Command command = CommandParser.parseCommand("right");

            if (command == null) {
                Assertions.fail();
            }

            Assertions.assertEquals(Command.Key.RIGHT, command.getKey(), "Wrong command has been detected");
            Assertions.assertEquals(0, command.getArgs().size(), "Wrong number of args has been detected");
        } catch (CommandException e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Left Command")
    public void testLeftCommand() {
        try {
            Command command = CommandParser.parseCommand("left");

            if (command == null) {
                Assertions.fail();
            }

            Assertions.assertEquals(Command.Key.LEFT, command.getKey(), "Wrong command has been detected");
            Assertions.assertEquals(0, command.getArgs().size(), "Wrong number of args has been detected");
        } catch (CommandException e) {
            Assertions.fail();
        }
    }

    @Test
    @DisplayName("Incorrect Place Command")
    public void testWrongPlaceCommand() {
        try {
            CommandParser.parseCommand("place 0, 0, 0, east");
        } catch (CommandException e) {
            Assertions.assertEquals("Unsupported number of arguments for PLACE command\n" +
                            "Please type HELP for more details", e.getMessage(),
                    "Incorrect exception is thrown");
        }
    }

    @Test
    @DisplayName("Incorrect Move Command")
    public void testWrongMoveCommand() {
        try {
            CommandParser.parseCommand("move 0");
        } catch (CommandException e) {
            Assertions.assertEquals("Unsupported number of arguments for MOVE command\n" +
                            "Please type HELP for more details", e.getMessage(),
                    "Incorrect exception is thrown");
        }
    }

    @Test
    @DisplayName("Incorrect Right/Left Command")
    public void testWrongTurnCommands() {
        try {
            CommandParser.parseCommand("left 0");
        } catch (CommandException e) {
            Assertions.assertEquals("Unsupported number of arguments for LEFT command\n" +
                            "Please type HELP for more details", e.getMessage(),
                    "Incorrect exception is thrown");
        }

        try {
            CommandParser.parseCommand("right 2");
        } catch (CommandException e) {
            Assertions.assertEquals("Unsupported number of arguments for RIGHT command\n" +
                            "Please type HELP for more details", e.getMessage(),
                    "Incorrect exception is thrown");
        }
    }

    @Test
    @DisplayName("Incorrect command")
    public void testWrongCommand() {
        try {
            CommandParser.parseCommand("placed");
        } catch (CommandException e) {
            Assertions.assertEquals("placed command is not supported", e.getMessage(),
                    "Incorrect exception is thrown");
        }
    }
}
