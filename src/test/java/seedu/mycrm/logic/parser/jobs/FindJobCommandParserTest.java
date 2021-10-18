package seedu.mycrm.logic.parser.jobs;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.jobs.FindJobCommand;
import seedu.mycrm.model.job.JobContainsKeywordsPredicate;

public class FindJobCommandParserTest {

    private FindJobCommandParser parser = new FindJobCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindJobCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommandWithContact() {
        // no leading and trailing whitespaces
        FindJobCommand expectedFindCommand =
                new FindJobCommand(new JobContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }
}
