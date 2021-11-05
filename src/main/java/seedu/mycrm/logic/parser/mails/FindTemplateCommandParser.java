package seedu.mycrm.logic.parser.mails;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.mycrm.logic.commands.mails.FindTemplateCommand;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.mail.SubjectContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindTemplateCommand object
 */
public class FindTemplateCommandParser implements Parser<FindTemplateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTemplateCommand
     * and returns a FindTemplateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTemplateCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTemplateCommand.MESSAGE_USAGE));
        }

        String[] subjectKeywords = trimmedArgs.split("\\s+");

        return new FindTemplateCommand(new SubjectContainsKeywordsPredicate(Arrays.asList(subjectKeywords)));
    }

}
