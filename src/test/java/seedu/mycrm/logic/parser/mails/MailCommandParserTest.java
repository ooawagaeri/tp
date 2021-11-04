package seedu.mycrm.logic.parser.mails;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_JOB_INDEX_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_TEMPLATE_INDEX_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.JOB_INDEX_DESC_1;
import static seedu.mycrm.logic.commands.CommandTestUtil.JOB_INDEX_DESC_2;
import static seedu.mycrm.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.mycrm.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.mycrm.logic.commands.CommandTestUtil.TEMPLATE_INDEX_DESC_1;
import static seedu.mycrm.logic.commands.CommandTestUtil.TEMPLATE_INDEX_DESC_2;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_INDEX_1;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_TEMPLATE_INDEX_1;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_JOB;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_TEMPLATE;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_JOB;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_SECOND_TEMPLATE;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.mails.MailCommand;
import seedu.mycrm.logic.parser.ParserUtil;

public class MailCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MailCommand.MESSAGE_USAGE);

    private final MailCommandParser parser = new MailCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + JOB_INDEX_DESC_1 + TEMPLATE_INDEX_DESC_1,
                new MailCommand(INDEX_FIRST_JOB, INDEX_FIRST_TEMPLATE));

        // multiple jobs - last job accepted
        assertParseSuccess(parser, JOB_INDEX_DESC_1 + JOB_INDEX_DESC_2 + TEMPLATE_INDEX_DESC_1,
                new MailCommand(INDEX_SECOND_JOB, INDEX_FIRST_TEMPLATE));

        // multiple templates - last template accepted
        assertParseSuccess(parser, JOB_INDEX_DESC_1 + TEMPLATE_INDEX_DESC_1 + TEMPLATE_INDEX_DESC_2 ,
                new MailCommand(INDEX_FIRST_JOB, INDEX_SECOND_TEMPLATE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing job prefix
        assertParseFailure(parser, VALID_JOB_INDEX_1 + TEMPLATE_INDEX_DESC_1, MESSAGE_INVALID_FORMAT);

        // missing template prefix
        assertParseFailure(parser, JOB_INDEX_DESC_1 + VALID_TEMPLATE_INDEX_1, MESSAGE_INVALID_FORMAT);

        // all prefixes missing
        assertParseFailure(parser, VALID_JOB_INDEX_1 + VALID_TEMPLATE_INDEX_1, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid job
        assertParseFailure(parser, INVALID_JOB_INDEX_DESC + TEMPLATE_INDEX_DESC_1,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // invalid template
        assertParseFailure(parser, JOB_INDEX_DESC_1 + INVALID_TEMPLATE_INDEX_DESC,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_JOB_INDEX_DESC + INVALID_TEMPLATE_INDEX_DESC,
                ParserUtil.MESSAGE_INVALID_INDEX);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + JOB_INDEX_DESC_1 + TEMPLATE_INDEX_DESC_1,
                MESSAGE_INVALID_FORMAT);
    }
}
