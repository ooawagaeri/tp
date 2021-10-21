package seedu.mycrm.logic.parser.contacts;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_NOT_HIDDEN_CONTACTS;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.contacts.ListContactCommand;

public class ListContactCommandParserTest {

    private ListContactCommandParser parser = new ListContactCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {
        assertParseSuccess(parser, "", new ListContactCommand(PREDICATE_SHOW_NOT_HIDDEN_CONTACTS));
        assertParseSuccess(parser, "-a", new ListContactCommand());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "ukpkmkk", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListContactCommand.MESSAGE_USAGE));
    }
}
