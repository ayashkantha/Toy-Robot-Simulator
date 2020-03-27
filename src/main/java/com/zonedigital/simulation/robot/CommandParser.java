package com.zonedigital.simulation.robot;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import static com.zonedigital.simulation.robot.Command.Key.*;

/**
 * Input parsing utility.
 * Check the method <code>CommandParser.parseCommand(String input)</code> for more info.
 */
class CommandParser {

    private static final String SPACE = " ";
    private static final String COMMA = ",";

    /**
     * Convert a plain <code>String</code> to a <code>Command</code>
     *
     * @param input a plain command string e.g. `PLACE 2, 3, EAST`
     * @return the relevant Command object
     * @throws WrongCommandException throws when command key is not supported or else number of arguments
     *                               expected by the particular command is invalid
     */
    static Command parseCommand(String input) throws WrongCommandException {

        //Tokenize the input using the spaces
        List<String> inputs = Arrays.stream(input.split(SPACE))
                .filter(key -> !key.isEmpty())
                .collect(Collectors.toList());

        if (inputs.size() == 0) {
            return null;
        }

        Command command = new Command();
        Command.Key key;
        try {
            key = Command.Key.valueOf(inputs.get(0).toUpperCase());
            command.setKey(key);
        } catch (IllegalArgumentException e) {
            throw new WrongCommandException(inputs.get(0) + " command is not supported");
        }

        //further Tokenize the arguments using the comma
        for (int i = 1; i < inputs.size(); i++) {
            List<String> args = Arrays.stream(inputs.get(i).split(COMMA))
                    .filter(arg -> !arg.isEmpty())
                    .collect(Collectors.toList());
            args.forEach(arg -> command.setArg(arg.trim().toUpperCase()));
        }

        if(command.isValid()) {
            return command;
        }
        //if code reaches here, then it is an invalid command
        throwCommandException(command.getKey());
        return null;
    }

    private static void throwCommandException(Command.Key s) throws WrongCommandException {
        throw new WrongCommandException("Unsupported number of arguments for " + s + " command\n" +
                "Please type HELP for more details");
    }
}
