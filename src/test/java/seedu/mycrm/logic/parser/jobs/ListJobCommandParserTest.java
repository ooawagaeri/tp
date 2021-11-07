package seedu.mycrm.logic.parser.jobs;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_COMPLETED_JOBS;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_INCOMPLETE_JOBS;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_JOBS;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.jobs.ListJobCommand;

public class ListJobCommandParserTest {
    private ListJobCommandParser parser = new ListJobCommandParser();

    @Test
    public void parse_validArgs_returnsListCommand() {
        assertParseSuccess(parser, "", new ListJobCommand(PREDICATE_SHOW_ALL_INCOMPLETE_JOBS));
        assertParseSuccess(parser, "-a", new ListJobCommand(PREDICATE_SHOW_ALL_JOBS));
        assertParseSuccess(parser, "-c", new ListJobCommand(PREDICATE_SHOW_ALL_COMPLETED_JOBS));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-rflag", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListJobCommand.MESSAGE_USAGE));
    }
}
