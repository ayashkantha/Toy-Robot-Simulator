package com.zonedigital.simulation.robot;

/**
 * Represents the positions of the table associated with the direction faced by the robot.
 * Coordination and the Direction
 */
class Position {
    private int x;
    private int y;
    private Direction direction;

    Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    Direction getDirection() {
        return direction;
    }

    void setDirection(Direction direction) {
        this.direction = direction;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
}
