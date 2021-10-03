package seedu.address.logic.parser.contactparser;

import seedu.address.logic.commands.contactcommands.LinkContactCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class LinkContactCommandParser implements Parser<LinkContactCommand> {

    @Override
    public LinkContactCommand parse(String userInput) throws ParseException {
        return new LinkContactCommand();
    }
}
