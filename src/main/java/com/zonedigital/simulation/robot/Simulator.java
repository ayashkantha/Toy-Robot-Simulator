package com.zonedigital.simulation.robot;

import java.util.List;
import java.util.Scanner;

/**
 * Helps simulating placing, turning and moving the robot
 */
class Simulator {

    private final Position lowerBound = new Position(0, 0, null);
    private final Position upperBound = new Position(5, 5, null);
    private final Position currentPosition = new Position(-1, -1, null);

    void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("$~");
            String input = scanner.nextLine();

            try {
                Command command = CommandParser.parseCommand(input);

                if(command == null) {
                    continue;
                }

                executeCommand(command);
            } catch (WrongCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void executeCommand(Command command) {
        switch (command.getKey()) {
            case PLACE:
                placeRobot(command.getArgs());
                break;
            case LEFT:
                turnLeft();
                break;
            case RIGHT:
                turnRight();
                break;
            case MOVE:
                move();
                break;
            case REPORT:
                report();
                break;
            case HELP:
                help();
        }
    }

    /**
     * Report the current position to the user by printing e.g. (4, 3, NORTH)
     */
    private void report() {

        if(currentPosition.getX() == -1 && currentPosition.getY() == -1) {
            System.out.println("Robot is not placed.");
            return;
        }

        System.out.println(currentPosition.getX() + ", "
                + currentPosition.getY() + ", "
                + currentPosition.getDirection());
    }

    /**
     * Prints the help menu
     */
    private void help() {
        System.out.println("PLACE X,Y,F #Placing the robot in (X,Y) facing F. \n" +
                "\tX and Y are numbers between 0 AND 5, F is NORTH, EAST, WEST, SOUTH directions\n" +
                "MOVE #Move one position towards the current direction\n" +
                "LEFT #Turn left\n" +
                "RIGHT #Turn right\n" +
                "REPORT #Prints current position and the direction\n" +
                "HELP #Help menu");
    }

    /**
     * Moving one position to the faced direction
     */
    private void move() {

        if(currentPosition.getX() == -1 && currentPosition.getY() == -1) {
            System.out.println("Robot is not placed.");
            return;
        }

        Position.Direction direction = currentPosition.getDirection();
        int newX = -1;
        int newY = -1;
        switch (direction) {
            case EAST:
                newX = currentPosition.getX() + 1;
                newY = currentPosition.getY();
                break;
            case WEST:
                newX = currentPosition.getX() - 1;
                newY = currentPosition.getY();
                break;
            case NORTH:
                newX = currentPosition.getX();
                newY = currentPosition.getY() + 1;
                break;
            case SOUTH:
                newX = currentPosition.getX();
                newY = currentPosition.getY() - 1;
                break;
        }

        if(newX < lowerBound.getX() || newY < lowerBound.getY()
                || newX > upperBound.getX() || newY > upperBound.getY()) {
            System.out.println("Cannot move to " + direction);
            return;
        }

        currentPosition.setX(newX);
        currentPosition.setY(newY);
    }

    /**
     * Turn 90 degrees clockwise
     */
    private void turnRight() {

        if(currentPosition.getX() == -1 && currentPosition.getY() == -1) {
            System.out.println("Robot is not placed.");
            return;
        }

        Position.Direction direction = currentPosition.getDirection();
        switch (direction) {
            case EAST:
                direction = Position.Direction.SOUTH;
                break;
            case WEST:
                direction = Position.Direction.NORTH;
                break;
            case NORTH:
                direction = Position.Direction.EAST;
                break;
            case SOUTH:
                direction = Position.Direction.WEST;
                break;
        }

        currentPosition.setDirection(direction);
    }

    /**
     * Turn 90 degrees anticlockwise
     */
    private void turnLeft() {

        if(currentPosition.getX() == -1 && currentPosition.getY() == -1) {
            System.out.println("Robot is not placed.");
            return;
        }

        Position.Direction direction = currentPosition.getDirection();
        switch (direction) {
            case EAST:
                direction = Position.Direction.NORTH;
                break;
            case WEST:
                direction = Position.Direction.SOUTH;
                break;
            case NORTH:
                direction = Position.Direction.WEST;
                break;
            case SOUTH:
                direction = Position.Direction.EAST;
                break;
        }

        currentPosition.setDirection(direction);
    }

    /**
     * Place the robot at the (x, y) facing the direction
     * @param args 0 - value of x
     *             1 - value of y
     *             2 - direction
     */
    private void placeRobot(List<String> args) {
        int x;
        int y;
        Position.Direction direction;

        try {
            x = Integer.parseInt(args.get(0));
            y = Integer.parseInt(args.get(1));
            direction = Position.Direction.valueOf(args.get(2));
        } catch (NumberFormatException e) {
            System.out.println("x and y accept only numbers");
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(args.get(2) + " looks an unsupported direction");
            return;
        }

        if(x >= lowerBound.getX() && y >= lowerBound.getY() &&
                x <= upperBound.getX() && y <= upperBound.getY() ) {
            currentPosition.setX(x);
            currentPosition.setY(y);
            currentPosition.setDirection(direction);
        } else {
            System.out.println("Cannot position the robot outside the table");
        }
    }
}
