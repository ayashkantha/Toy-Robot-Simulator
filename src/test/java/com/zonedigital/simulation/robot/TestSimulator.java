package com.zonedigital.simulation.robot;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestSimulator {

    private final static ByteArrayOutputStream consoleOut = new ByteArrayOutputStream();

    @BeforeClass
    public static void init() {
        System.setOut(new PrintStream(consoleOut));
    }

    @Test
    public void testPlaceRobot() {
        Simulator simulator = new Simulator();

        //placing beyond y
        String input = "place 3,8, west";
        simulator.start(input);
        Assert.assertEquals("Cannot position the robot outside the table", consoleOut.toString().trim());
        consoleOut.reset();

        input = "report";
        simulator.start(input);
        Assert.assertEquals("Robot is not placed, yet", consoleOut.toString().trim());
        consoleOut.reset();

        //placing beyond x
        input = "place 7,4, north";
        simulator.start(input);
        Assert.assertEquals("Cannot position the robot outside the table", consoleOut.toString().trim());
        consoleOut.reset();

        //placing beyond x and y
        input = "place 7,8, south";
        simulator.start(input);
        Assert.assertEquals("Cannot position the robot outside the table", consoleOut.toString().trim());
        consoleOut.reset();

        //placing below x
        input = "place -3, 4, east";
        simulator.start(input);
        Assert.assertEquals("Cannot position the robot outside the table", consoleOut.toString().trim());
        consoleOut.reset();

        //placing in the range
        input = "place 3,4, east";
        simulator.start(input);
        Assert.assertEquals("", consoleOut.toString().trim());
        consoleOut.reset();

        ////placing below x and y
        input = "place -3, -6, west";
        simulator.start(input);
        Assert.assertEquals("Cannot position the robot outside the table", consoleOut.toString().trim());
        consoleOut.reset();

        //last position should be prompted
        input = "report";
        simulator.start(input);
        Assert.assertEquals("3, 4, EAST", consoleOut.toString().trim());
        consoleOut.reset();
    }

    @Test
    public void testMove() {
        
    }
}
