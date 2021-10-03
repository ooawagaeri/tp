package seedu.address.logic.parser.contacts;

import seedu.address.logic.commands.contacts.EditContactCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditContactCommandParser implements Parser<EditContactCommand> {

    @Override
    public EditContactCommand parse(String userInput) throws ParseException {
        return new EditContactCommand();
    }
}
