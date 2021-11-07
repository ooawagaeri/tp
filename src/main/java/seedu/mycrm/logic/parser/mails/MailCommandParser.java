package seedu.mycrm.logic.parser.mails;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_JOB_INDEX;
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_TEMPLATE_INDEX;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.mails.MailCommand;
import seedu.mycrm.logic.parser.ArgumentMultimap;
import seedu.mycrm.logic.parser.ArgumentTokenizer;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.ParserUtil;
import seedu.mycrm.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MailCommand object
 */
public class MailCommandParser implements Parser<MailCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MailCommand
     * and returns a MailCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MailCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_JOB_INDEX, PREFIX_TEMPLATE_INDEX);

        if (ArgumentTokenizer.arePrefixesNotPresent(argMultimap, PREFIX_JOB_INDEX, PREFIX_TEMPLATE_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MailCommand.MESSAGE_USAGE));
        }

        Index jobIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_JOB_INDEX).get());
        Index templateIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TEMPLATE_INDEX).get());

        return new MailCommand(jobIndex, templateIndex);
    }
}
