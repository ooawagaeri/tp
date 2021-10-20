package seedu.mycrm.logic.parser.contacts;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.contacts.HideContactCommand;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.ParserUtil;
import seedu.mycrm.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HideContactCommand object
 */
public class HideContactCommandParser implements Parser<HideContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the HideContactCommand
     * and returns an HideContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public HideContactCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new HideContactCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HideContactCommand.MESSAGE_USAGE), pe);
        }
    }
}
