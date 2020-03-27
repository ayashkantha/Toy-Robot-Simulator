package com.zonedigital.simulation.robot;

import java.util.ArrayList;
import java.util.List;

import static com.zonedigital.simulation.robot.Command.Key.*;
import static com.zonedigital.simulation.robot.Command.Key.HELP;

/**
 * POJO for supported commands for the robot motion.
 * Supported commands are PLACE, MOVE, LEFT, RIGHT, REPORT and HELP
 */
class Command {

    /**
     * Supported keys PLACE, MOVE, LEFT, RIGHT, REPORT and HELP
     */
    private Key key;

    /**
     * Args is necessary arguments for particular command
     */
    private List<String> args = new ArrayList<>();

    Key getKey() {
        return key;
    }

    void setKey(Key key) {
        this.key = key;
    }

    List<String> getArgs() {
        return args;
    }

    void setArg(String arg) {
        this.args.add(arg);
    }

    /**
     * Supported commands by the robot
     */
    enum Key {
        PLACE,
        MOVE,
        LEFT,
        RIGHT,
        REPORT,
        HELP
    }

    /**
     *  Check if the number of arguments not supported by the particular command.
     * @return false if not valid
     */
    boolean isValid() {

        switch (key) {

            case PLACE:
                if (args.size() != 3) {
                    return false;
                }
                break;

            case LEFT:
            case REPORT:
            case HELP:
            case MOVE:
            case RIGHT:
                if (args.size() != 0) {
                    return false;
                }
                break;
        }

        return true;
    }
}
