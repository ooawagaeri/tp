package seedu.mycrm.logic.parser.mails;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.commands.CommandTestUtil.BODY_DESC_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.BODY_DESC_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_BODY_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_SUBJECT_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.SUBJECT_DESC_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.SUBJECT_DESC_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_BODY_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_BODY_DONE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_COMPLETE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_SUBJECT_DONE;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_TEMPLATE;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.mails.EditTemplateCommand;
import seedu.mycrm.logic.commands.mails.EditTemplateCommand.EditTemplateDescriptor;
import seedu.mycrm.model.mail.Body;
import seedu.mycrm.model.mail.Subject;
import seedu.mycrm.testutil.EditTemplateDescriptorBuilder;

public class EditTemplateCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTemplateCommand.MESSAGE_USAGE);

    private final EditTemplateCommandParser parser = new EditTemplateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_SUBJECT_COMPLETE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTemplateCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + SUBJECT_DESC_COMPLETE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + SUBJECT_DESC_COMPLETE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_SUBJECT_DESC, Subject.MESSAGE_CONSTRAINTS); // invalid subject
        assertParseFailure(parser, "1" + INVALID_BODY_DESC, Body.MESSAGE_CONSTRAINTS); // invalid body

        // invalid body followed by valid subject
        assertParseFailure(parser, "1" + INVALID_BODY_DESC + SUBJECT_DESC_DONE, Body.MESSAGE_CONSTRAINTS);

        // valid body followed by invalid body. The test case for invalid body followed by valid body
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + BODY_DESC_COMPLETE + INVALID_BODY_DESC, Body.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_SUBJECT_DESC + INVALID_BODY_DESC, Subject.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TEMPLATE;
        String userInput = targetIndex.getOneBased() + SUBJECT_DESC_DONE + BODY_DESC_DONE;

        EditTemplateDescriptor descriptor = new EditTemplateDescriptorBuilder().withSubject(VALID_SUBJECT_DONE)
                .withBody(VALID_BODY_DONE).build();
        EditTemplateCommand expectedCommand = new EditTemplateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // subject
        Index targetIndex = INDEX_FIRST_TEMPLATE;
        String userInput = targetIndex.getOneBased() + SUBJECT_DESC_DONE;
        EditTemplateDescriptor descriptor = new EditTemplateDescriptorBuilder().withSubject(VALID_SUBJECT_DONE).build();
        EditTemplateCommand expectedCommand = new EditTemplateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // body
        userInput = targetIndex.getOneBased() + BODY_DESC_DONE;
        descriptor = new EditTemplateDescriptorBuilder().withBody(VALID_BODY_DONE).build();
        expectedCommand = new EditTemplateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TEMPLATE;
        String userInput = targetIndex.getOneBased() + BODY_DESC_DONE + BODY_DESC_DONE + BODY_DESC_COMPLETE;

        EditTemplateDescriptor descriptor = new EditTemplateDescriptorBuilder().withBody(VALID_BODY_COMPLETE).build();
        EditTemplateCommand expectedCommand = new EditTemplateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TEMPLATE;
        String userInput = targetIndex.getOneBased() + INVALID_BODY_DESC + BODY_DESC_COMPLETE;
        EditTemplateDescriptor descriptor = new EditTemplateDescriptorBuilder().withBody(VALID_BODY_COMPLETE).build();
        EditTemplateCommand expectedCommand = new EditTemplateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + SUBJECT_DESC_DONE + INVALID_BODY_DESC + BODY_DESC_COMPLETE;
        descriptor = new EditTemplateDescriptorBuilder().withSubject(VALID_SUBJECT_DONE).withBody(VALID_BODY_COMPLETE)
                .build();
        expectedCommand = new EditTemplateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
