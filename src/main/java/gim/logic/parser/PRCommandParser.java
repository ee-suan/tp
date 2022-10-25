package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Set;
import java.util.stream.Stream;

import gim.logic.commands.PRCommand;
import gim.logic.parser.exceptions.ParseException;
import gim.model.exercise.Name;

/**
 * Parses input arguments and creates a new PRCommand object
 */
public class PRCommandParser implements Parser<PRCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PRCommand
     * and returns an PRCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PRCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PRCommand.MESSAGE_USAGE));
        }
        Set<Name> nameSet = ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME));
        return new PRCommand(nameSet);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
