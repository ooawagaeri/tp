package seedu.mycrm.logic.parser.contacts;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.contacts.UndoHideContactCommand;

public class UndoHideContactCommandParserTest {
    private UndoHideContactCommandParser parser = new UndoHideContactCommandParser();

    @Test
    public void parse_validArgs_returnsHideCommand() {
        assertParseSuccess(parser, "1", new UndoHideContactCommand(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_nullArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UndoHideContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UndoHideContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "-100", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UndoHideContactCommand.MESSAGE_USAGE));
    }
}
