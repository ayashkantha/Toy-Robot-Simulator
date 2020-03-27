package com.zonedigital.simulation.robot;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * @throws CommandException throws when command key is not supported or else number of arguments
     *                          expected by the particular command is invalid
     */
    static Command parseCommand(String input) throws CommandException {

        //Tokenize the input around white space
        List<String> inputs = Arrays.stream(input.split(SPACE))

                //creating inputs list ignoring empty tokens
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
            throw new CommandException(inputs.get(0) + " command is not supported");
        }

        generateArgs(inputs, command);

        return command;
    }


    /**
     * Generating arguments of the command
     * @param inputs tokenized inputs
     * @param command command object to be filled with arguments
     * @throws CommandException
     */
    private static void generateArgs(List<String> inputs, Command command) throws CommandException {
        //further Tokenize the arguments around comma
        for (int i = 1; i < inputs.size(); i++) {

            List<String> args = Arrays.stream(inputs.get(i).split(COMMA))

                    //creating args list ignoring empty tokens
                    .filter(arg -> !arg.isEmpty())
                    .collect(Collectors.toList());

            args.forEach(arg -> command.setArg(arg.trim().toUpperCase()));
        }

        if (!command.isValid()) {
            //Error command
            throwCommandException(command.getKey());
        }
    }

    private static void throwCommandException(Command.Key s) throws CommandException {
        throw new CommandException("Unsupported number of arguments for " + s + " command\n" +
                "Please type HELP for more details");
    }
}
