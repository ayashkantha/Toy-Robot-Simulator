package com.zonedigital.simulation.robot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Input parsing utility.
 * Check the method <code>CommandParser.parseCommand(String input)</code> for more info.
 */
class CommandParser {

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

        //Basic tokenizing around white spaces
        StringTokenizer stringTokenizer = new StringTokenizer(input);
        List<String> inputs = new ArrayList<>(stringTokenizer.countTokens());

        while(stringTokenizer.hasMoreElements()) {
            inputs.add(stringTokenizer.nextToken());
        }

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

        //remove the key and add the rest of the tokens as arguments
        inputs.remove(0);
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
        //Further Tokenizing around comma
        for (String input: inputs) {
            StringTokenizer stringTokenizer = new StringTokenizer(input, COMMA);
            List<String> args = new ArrayList<>(stringTokenizer.countTokens());

            while(stringTokenizer.hasMoreElements()) {
                args.add(stringTokenizer.nextToken());
            }

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
