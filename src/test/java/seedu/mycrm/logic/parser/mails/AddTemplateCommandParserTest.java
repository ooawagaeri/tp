package seedu.mycrm.logic.parser.mails;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.commands.CommandTestUtil.BODY_DESC_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.BODY_DESC_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_BODY_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_SUBJECT_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.mycrm.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.mycrm.logic.commands.CommandTestUtil.SUBJECT_DESC_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.SUBJECT_DESC_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_BODY_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_COMPLETE;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.testutil.TypicalTemplates.COMPLETED;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.mails.AddTemplateCommand;
import seedu.mycrm.model.mail.Body;
import seedu.mycrm.model.mail.Subject;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.testutil.TemplateBuilder;

class AddTemplateCommandParserTest {
    private final AddTemplateCommandParser parser = new AddTemplateCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Template expectedTemplate = new TemplateBuilder(COMPLETED).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + SUBJECT_DESC_COMPLETE + BODY_DESC_COMPLETE,
                new AddTemplateCommand(expectedTemplate));

        // multiple subjects - last subject accepted
        assertParseSuccess(parser, SUBJECT_DESC_DONE + SUBJECT_DESC_COMPLETE + BODY_DESC_COMPLETE,
                new AddTemplateCommand(expectedTemplate));

        // multiple bodies - last body accepted
        assertParseSuccess(parser, SUBJECT_DESC_COMPLETE + BODY_DESC_DONE + BODY_DESC_COMPLETE ,
                new AddTemplateCommand(expectedTemplate));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTemplateCommand.MESSAGE_USAGE);

        // missing subject prefix
        assertParseFailure(parser, VALID_SUBJECT_COMPLETE + BODY_DESC_COMPLETE, expectedMessage);

        // missing body prefix
        assertParseFailure(parser, SUBJECT_DESC_COMPLETE + VALID_BODY_COMPLETE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_SUBJECT_COMPLETE + VALID_BODY_COMPLETE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid subject
        assertParseFailure(parser, INVALID_SUBJECT_DESC + BODY_DESC_COMPLETE, Subject.MESSAGE_CONSTRAINTS);

        // invalid body
        assertParseFailure(parser, SUBJECT_DESC_COMPLETE + INVALID_BODY_DESC, Body.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_SUBJECT_DESC + INVALID_BODY_DESC, Subject.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SUBJECT_DESC_COMPLETE + BODY_DESC_COMPLETE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTemplateCommand.MESSAGE_USAGE));
    }
}
