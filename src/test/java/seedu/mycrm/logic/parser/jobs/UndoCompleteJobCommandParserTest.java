package seedu.mycrm.logic.parser.jobs;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_JOB;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.jobs.UndoCompleteJobCommand;

public class UndoCompleteJobCommandParserTest {
    private final UndoCompleteJobCommandParser parser = new UndoCompleteJobCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new UndoCompleteJobCommand(INDEX_FIRST_JOB));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            UndoCompleteJobCommand.MESSAGE_USAGE));
    }
}
