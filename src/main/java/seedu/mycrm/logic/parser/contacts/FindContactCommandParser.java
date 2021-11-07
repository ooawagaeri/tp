package seedu.mycrm.logic.parser.contacts;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.mycrm.logic.commands.contacts.FindContactCommand;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.contact.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindContactCommand object
 */
public class FindContactCommandParser implements Parser<FindContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindContactCommand
     * and returns a FindContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindContactCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        NameContainsKeywordsPredicate keywordsPredicate = new NameContainsKeywordsPredicate(Arrays
                .asList(nameKeywords));

        return new FindContactCommand(keywordsPredicate);
    }

}
