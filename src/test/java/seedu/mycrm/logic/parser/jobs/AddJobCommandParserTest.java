package seedu.mycrm.logic.parser.jobs;

import static seedu.mycrm.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_JOB_CONTACT_INDEX_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_JOB_DESCRIPTION_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_JOB_EXPECTED_COMPLETION_DATE_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_JOB_FEE_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_JOB_PRODUCT_INDEX_DESC;
import static seedu.mycrm.logic.commands.CommandTestUtil.INVALID_JOB_RECEIVED_DATE_DESC;
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
import static seedu.mycrm.logic.parser.CliSyntax.PREFIX_EXPECTED_COMPLETION_DATE;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.jobs.AddJobCommand;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;
import seedu.mycrm.testutil.JobBuilder;

public class AddJobCommandParserTest {
    private AddJobCommandParser parser = new AddJobCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index clientIndex = Index.fromOneBased(1);
        Index productIndex = Index.fromOneBased(1);

        Job expectedJob = new JobBuilder().withJobDescription(VALID_JOB_DESCRIPTION).withFee(VALID_JOB_FEE)
                .withReceivedDate(VALID_JOB_RECEIVED_DATE)
                .withExpectedCompletionDate(VALID_JOB_EXPECTED_COMPLETION_DATE)
                .withClient(null)
                .withProduct(null).build();

        // whitespace only preamble
        assertParseSuccess(parser, VALID_JOB_DESCRIPTION_DESC + VALID_JOB_FEE_DESC
                + VALID_JOB_RECEIVED_DATE_DESC + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC
                + VALID_JOB_CONTACT_INDEX_DESC + VALID_JOB_PRODUCT_INDEX_DESC,
                new AddJobCommand(expectedJob, clientIndex, productIndex));
    }

    @Test
    public void parse_allCompulsoryFieldsPresent_success() {
        String receivedDate = JobDate.getCurrentDate().raw();
        String expectedCompletionDate = receivedDate;

        Job expectedJob = new JobBuilder().withJobDescription(VALID_JOB_DESCRIPTION).withFee(VALID_JOB_FEE)
            .withExpectedCompletionDate(expectedCompletionDate)
            .withReceivedDate(receivedDate)
            .withClient(null)
            .withProduct(null).build();

        // whitespace only preamble
        assertParseSuccess(parser, VALID_JOB_DESCRIPTION_DESC + VALID_JOB_FEE_DESC
                + " " + PREFIX_EXPECTED_COMPLETION_DATE + expectedCompletionDate ,
            new AddJobCommand(expectedJob, null, null));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddJobCommand.MESSAGE_USAGE);

        // missing job fee
        assertParseFailure(parser, VALID_JOB_DESCRIPTION_DESC + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC,
                expectedMessage);

        // missing job description
        assertParseFailure(parser, VALID_JOB_FEE_DESC + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC,
                expectedMessage);

        // missing job expected completion date
        assertParseFailure(parser, VALID_JOB_DESCRIPTION_DESC + VALID_JOB_FEE_DESC, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid job description
        assertParseFailure(parser, INVALID_JOB_DESCRIPTION_DESC + VALID_JOB_FEE_DESC
                + VALID_JOB_RECEIVED_DATE_DESC + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC,
                JobDescription.MESSAGE_CONSTRAINTS);

        // invalid received date format
        assertParseFailure(parser, VALID_JOB_DESCRIPTION_DESC + VALID_JOB_FEE_DESC
                + INVALID_JOB_RECEIVED_DATE_DESC + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC,
                "Received " + JobDate.MESSAGE_CONSTRAINTS);

        // invalid expected completion date format
        assertParseFailure(parser, VALID_JOB_DESCRIPTION_DESC + VALID_JOB_FEE_DESC
                + VALID_JOB_RECEIVED_DATE_DESC + INVALID_JOB_EXPECTED_COMPLETION_DATE_DESC,
                "Expected Completion " + JobDate.MESSAGE_CONSTRAINTS);

        // invalid job fee
        assertParseFailure(parser, VALID_JOB_DESCRIPTION_DESC + INVALID_JOB_FEE_DESC
                + VALID_JOB_RECEIVED_DATE_DESC + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC,
                JobFee.MESSAGE_CONSTRAINTS);

        // invalid product index
        assertParseFailure(parser, VALID_JOB_DESCRIPTION_DESC + VALID_JOB_FEE_DESC
                + VALID_JOB_RECEIVED_DATE_DESC + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC
                + VALID_JOB_CONTACT_INDEX_DESC + INVALID_JOB_PRODUCT_INDEX_DESC,
                MESSAGE_INVALID_INDEX);

        // invalid contact index
        assertParseFailure(parser, VALID_JOB_DESCRIPTION_DESC + VALID_JOB_FEE_DESC
                + VALID_JOB_RECEIVED_DATE_DESC + VALID_JOB_EXPECTED_COMPLETION_DATE_DESC
                + INVALID_JOB_CONTACT_INDEX_DESC + VALID_JOB_PRODUCT_INDEX_DESC,
                MESSAGE_INVALID_INDEX);
    }
}
