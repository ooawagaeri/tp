package seedu.mycrm.logic.parser.jobs;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_JOB;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.jobs.CompleteJobCommand;
import seedu.mycrm.model.job.JobDate;

public class CompleteJobCommandParserTest {
    private final CompleteJobCommandParser parser = new CompleteJobCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1 30/10/2021",
                new CompleteJobCommand(INDEX_FIRST_JOB, new JobDate("30/10/2021")));

        JobDate currentDate = JobDate.getCurrentDate();
        assertParseSuccess(parser, "1",
                new CompleteJobCommand(INDEX_FIRST_JOB, currentDate));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            CompleteJobCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            CompleteJobCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "1 30/10/21", "Completion " + JobDate.MESSAGE_CONSTRAINTS);
    }
}
