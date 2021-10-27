package seedu.mycrm.logic.parser.mails;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.mails.FindTemplateCommand;
import seedu.mycrm.model.mail.SubjectContainsKeywordsPredicate;

public class FindTemplateCommandParserTest {

    private final FindTemplateCommandParser parser = new FindTemplateCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTemplateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindTemplateCommand expectedFindCommand =
                new FindTemplateCommand(new SubjectContainsKeywordsPredicate(Arrays.asList("Complete", "Done")));
        assertParseSuccess(parser, "Complete Done", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Complete \n \t Done  \t", expectedFindCommand);
    }

}
