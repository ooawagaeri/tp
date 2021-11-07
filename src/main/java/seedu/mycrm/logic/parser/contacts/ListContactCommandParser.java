package seedu.mycrm.logic.parser.contacts;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.commands.contacts.ListContactCommand.SHOW_ALL_CONTACTS;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_CONTACTS;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_NOT_HIDDEN_CONTACTS;

import java.util.function.Predicate;

import seedu.mycrm.logic.commands.contacts.ListContactCommand;
import seedu.mycrm.logic.parser.Parser;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.contact.Contact;

/**
 * Parses input arguments and creates a new ListContactCommand object
 */
public class ListContactCommandParser implements Parser<ListContactCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListContactCommand
     * and returns an ListContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListContactCommand parse(String args) throws ParseException {
        Predicate<Contact> listPredicate;
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            listPredicate = PREDICATE_SHOW_NOT_HIDDEN_CONTACTS;
        } else {
            String[] nameKeywords = trimmedArgs.split("\\s+");
            if (nameKeywords.length > 1 || !nameKeywords[0].equals(SHOW_ALL_CONTACTS)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ListContactCommand.MESSAGE_USAGE));
            }
            listPredicate = PREDICATE_SHOW_ALL_CONTACTS;
        }

        return new ListContactCommand(listPredicate);
    }
}
