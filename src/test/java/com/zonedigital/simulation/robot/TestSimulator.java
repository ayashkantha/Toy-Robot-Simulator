package com.zonedigital.simulation.robot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestSimulator {

    private final static ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();

    @BeforeAll
    public static void init() {
        System.setOut(new PrintStream(consoleOut));
    }

    @Test
    @DisplayName("Robot's Every Possible Placement Test")
    public void testPlaceRobot() {
        Simulator simulator = new Simulator();

        //placing beyond y
        String input = "place 3,8, west";
        simulator.processInput(input);
        Assertions.assertEquals("Cannot position the robot outside the table", consoleOut.toString().trim());
        consoleOut.reset();

        input = "report";
        simulator.processInput(input);
        Assertions.assertEquals("Robot is not placed, yet.", consoleOut.toString().trim());
        consoleOut.reset();

        //placing beyond x
        input = "place 7,4, north";
        simulator.processInput(input);
        Assertions.assertEquals("Cannot position the robot outside the table", consoleOut.toString().trim());
        consoleOut.reset();

        //placing beyond x and y
        input = "place 7,8, south";
        simulator.processInput(input);
        Assertions.assertEquals("Cannot position the robot outside the table", consoleOut.toString().trim());
        consoleOut.reset();

        //placing below x
        input = "place -3, 4, east";
        simulator.processInput(input);
        Assertions.assertEquals("Cannot position the robot outside the table", consoleOut.toString().trim());
        consoleOut.reset();

        //placing in the range
        input = "place 3,4, east";
        simulator.processInput(input); //should not output anything
        Assertions.assertEquals("", consoleOut.toString().trim());
        consoleOut.reset();

        ////placing below x and y
        input = "place -3, -6, west";
        simulator.processInput(input);
        Assertions.assertEquals("Cannot position the robot outside the table", consoleOut.toString().trim());
        consoleOut.reset();

        //last position should be prompted
        input = "report";
        simulator.processInput(input);
        Assertions.assertEquals("3, 4, EAST", consoleOut.toString().trim());
        consoleOut.reset();
    }

    @Test
    @DisplayName("Robot's Every Possible Movement Test")
    public void testMoveRobot() {
        Simulator simulator = new Simulator();
        String input = "place 3,4, east";
        simulator.processInput(input);

        input = "move";
        simulator.processInput(input);//(4,4)
        simulator.processInput(input);//(5,4)
        simulator.processInput(input);//should refuse the move outside the table
        Assertions.assertEquals("Cannot move to EAST", consoleOut.toString().trim());
        consoleOut.reset();

        input = "report";
        simulator.processInput(input);//last position should be prompted i.e (5,4)
        Assertions.assertEquals("5, 4, EAST", consoleOut.toString().trim());
        consoleOut.reset();
    }

    @Test
    @DisplayName("Robot's Every Possible Turning Test")
    public void testTurnRobot() {
        Simulator simulator = new Simulator();

        String input = "place 4,2, west";
        simulator.processInput(input);//(4,2)

        input = "right";
        simulator.processInput(input);

        input = "report";
        simulator.processInput(input);
        Assertions.assertEquals("4, 2, NORTH", consoleOut.toString().trim());
        consoleOut.reset();

        input = "right";
        simulator.processInput(input);//east
        input = "right";
        simulator.processInput(input);//south
        input = "right";
        simulator.processInput(input);//west
        input = "left";
        simulator.processInput(input);//south

        input = "report";
        simulator.processInput(input);
        Assertions.assertEquals("4, 2, SOUTH", consoleOut.toString().trim());
        consoleOut.reset();
    }

    @Test
    @DisplayName("Robot's Long Path Test")
    public void fullTestPath() {
        Simulator simulator = new Simulator();

        String inputPlace = "place 4,2, west";
        String inputMove = "move";
        String inputLeft = "left";
        String inputRight = "right";
        String inputReport = "report";

        simulator.processInput(inputPlace);//(4,2) WEST
        simulator.processInput(inputMove);//(3,2) WEST
        simulator.processInput(inputMove);//(2,2) WEST
        simulator.processInput(inputLeft);//(2,2) SOUTH
        simulator.processInput(inputMove);//(2,1) SOUTH
        simulator.processInput(inputMove);//(2,0) SOUTH
        simulator.processInput(inputRight);//(2,0) WEST
        simulator.processInput(inputMove);//(1,0) WEST
        simulator.processInput(inputMove);//(0,0) WEST
        simulator.processInput(inputMove);//refused to move

        Assertions.assertEquals("Cannot move to WEST", consoleOut.toString().trim());
        consoleOut.reset();

        simulator.processInput(inputRight);
        simulator.processInput(inputMove);//(0,1) NORTH
        simulator.processInput(inputMove);//(0,2) NORTH
        simulator.processInput(inputReport);

        Assertions.assertEquals("0, 2, NORTH", consoleOut.toString().trim());
        consoleOut.reset();
    }
}
