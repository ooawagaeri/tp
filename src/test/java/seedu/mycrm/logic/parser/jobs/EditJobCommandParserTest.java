package seedu.mycrm.logic.parser.jobs;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.commands.CommandTestUtil.PRODUCT_NAME_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_CONTACT_INDEX_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_DESCRIPTION;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_DESCRIPTION_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_EXPECTED_COMPLETION_DATE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_EXPECTED_COMPLETION_DATE_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_FEE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_FEE_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_PRODUCT_INDEX_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_RECEIVED_DATE;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_JOB_RECEIVED_DATE_DESC;
import static seedu.mycrm.logic.commands.products.EditProductCommand.MESSAGE_NOT_EDITED;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.testutil.TypicalIndexes.INDEX_FIRST_JOB;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.jobs.EditJobCommand;
import seedu.mycrm.testutil.EditJobDescriptorBuilder;

public class EditJobCommandParserTest {
    private static final String INVALID_COMMAND_FORMAT_MESSAGE =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditJobCommand.MESSAGE_USAGE);

    private static final String indexOne = String.valueOf(INDEX_FIRST_JOB.getOneBased());

    private EditJobCommandParser parser = new EditJobCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no job index specified
        assertParseFailure(parser, VALID_JOB_DESCRIPTION_DESC, INVALID_COMMAND_FORMAT_MESSAGE);

        // no job field specified to be edited
        assertParseFailure(parser, "1", MESSAGE_NOT_EDITED);
    }

    @Test
    public void parser_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", INVALID_COMMAND_FORMAT_MESSAGE);

        // negative index
        assertParseFailure(parser, "-5" + PRODUCT_NAME_DESC, INVALID_COMMAND_FORMAT_MESSAGE);

        // 0 index
        assertParseFailure(parser, "0" + VALID_JOB_DESCRIPTION_DESC, INVALID_COMMAND_FORMAT_MESSAGE);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/some random string", INVALID_COMMAND_FORMAT_MESSAGE);
    }

    @Test
    public void parser_allFieldsSpecified_success() {
        EditJobCommand.EditJobDescriptor jobDescriptor = new EditJobDescriptorBuilder()
                .withJobDescription(VALID_JOB_DESCRIPTION)
                .withFee(VALID_JOB_FEE)
                .withReceivedDate(VALID_JOB_RECEIVED_DATE)
                .withExpectedCompletionDate(VALID_JOB_EXPECTED_COMPLETION_DATE)
                .withClientIndex(1)
                .withProductIndex(1)
                .build();

        assertParseSuccess(parser, indexOne + " " + VALID_JOB_DESCRIPTION_DESC + VALID_JOB_FEE_DESC
                + VALID_JOB_RECEIVED_DATE_DESC + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC
                + VALID_JOB_CONTACT_INDEX_DESC + VALID_JOB_PRODUCT_INDEX_DESC,
                new EditJobCommand(INDEX_FIRST_JOB, jobDescriptor));
    }

    @Test
    public void parser_someFieldsSpecified_success() {
        // only job description and fee provided
        EditJobCommand.EditJobDescriptor jobDescriptor = new EditJobDescriptorBuilder()
                .withJobDescription(VALID_JOB_DESCRIPTION)
                .withFee(VALID_JOB_FEE)
                .build();

        assertParseSuccess(parser, indexOne + " " + VALID_JOB_DESCRIPTION_DESC + VALID_JOB_FEE_DESC,
                new EditJobCommand(INDEX_FIRST_JOB, jobDescriptor));

        // only received date and expected completion date provided
        jobDescriptor = new EditJobDescriptorBuilder()
                .withReceivedDate(VALID_JOB_RECEIVED_DATE)
                .withExpectedCompletionDate(VALID_JOB_EXPECTED_COMPLETION_DATE)
                .build();

        assertParseSuccess(parser, indexOne + " " + VALID_JOB_RECEIVED_DATE_DESC
                + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC,
                new EditJobCommand(INDEX_FIRST_JOB, jobDescriptor));

        // only client and product index provided
        jobDescriptor = new EditJobDescriptorBuilder()
                .withClientIndex(1)
                .withProductIndex(1)
                .build();

        assertParseSuccess(parser, indexOne + " " + VALID_JOB_CONTACT_INDEX_DESC
                + VALID_JOB_PRODUCT_INDEX_DESC,
                new EditJobCommand(INDEX_FIRST_JOB, jobDescriptor));
    }

    @Test
    public void parser_oneFieldSpecified_success() {
        // job description
        EditJobCommand.EditJobDescriptor jobDescriptor = new EditJobDescriptorBuilder()
                .withJobDescription(VALID_JOB_DESCRIPTION)
                .build();

        assertParseSuccess(parser, indexOne + " " + VALID_JOB_DESCRIPTION_DESC,
                new EditJobCommand(INDEX_FIRST_JOB, jobDescriptor));

        // fee
        jobDescriptor = new EditJobDescriptorBuilder()
                .withFee(VALID_JOB_FEE)
                .build();

        assertParseSuccess(parser, indexOne + " " + VALID_JOB_FEE_DESC,
                new EditJobCommand(INDEX_FIRST_JOB, jobDescriptor));

        // client index
        jobDescriptor = new EditJobDescriptorBuilder()
                .withClientIndex(1)
                .build();

        assertParseSuccess(parser, indexOne + " " + VALID_JOB_CONTACT_INDEX_DESC,
                new EditJobCommand(INDEX_FIRST_JOB, jobDescriptor));

        // product index
        jobDescriptor = new EditJobDescriptorBuilder()
                .withProductIndex(1)
                .build();

        assertParseSuccess(parser, indexOne + " " + VALID_JOB_PRODUCT_INDEX_DESC,
                new EditJobCommand(INDEX_FIRST_JOB, jobDescriptor));

        // received date
        jobDescriptor = new EditJobDescriptorBuilder()
                .withReceivedDate(VALID_JOB_RECEIVED_DATE)
                .build();

        assertParseSuccess(parser, indexOne + " " + VALID_JOB_RECEIVED_DATE_DESC,
                new EditJobCommand(INDEX_FIRST_JOB, jobDescriptor));

        // expected completion date
        jobDescriptor = new EditJobDescriptorBuilder()
                .withExpectedCompletionDate(VALID_JOB_EXPECTED_COMPLETION_DATE)
                .build();

        assertParseSuccess(parser, indexOne + " " + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC,
                new EditJobCommand(INDEX_FIRST_JOB, jobDescriptor));
    }
}
