package seedu.mycrm.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.mycrm.storage.JsonAdaptedJob.MESSAGE_INVALID_COMPLETION_DATE;
import static seedu.mycrm.storage.JsonAdaptedJob.MESSAGE_INVALID_STATUS;
import static seedu.mycrm.storage.JsonAdaptedJob.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalContacts.GEORGE;
import static seedu.mycrm.testutil.TypicalJobs.COMPLETED;
import static seedu.mycrm.testutil.TypicalJobs.INCOMPLETED;
import static seedu.mycrm.testutil.TypicalProducts.SAMSUNG_SSD;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.MyCrm;
import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;
import seedu.mycrm.model.job.JobStatus;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.testutil.MyCrmBuilder;

public class JsonAdaptedJobTest {
    private static final String INVALID_JOB_DESCRIPTION = " ";
    private static final String INVALID_DATE = "2021/01/01";
    private static final String INVALID_FEE = "a10.00";
    private static final String INVALID_STATUS = "invalid";
    private static final String INVALID_PRODUCT = SAMSUNG_SSD.getName().toString();
    private static final String INVALID_CLIENT = GEORGE.getName().toString();

    private static final String VALID_JOB_DESCRIPTION = COMPLETED.getJobDescription().toString();
    private static final String VALID_CLIENT = COMPLETED.getClient().getName().toString();
    private static final String VALID_PRODUCT = COMPLETED.getProduct().getName().toString();
    private static final String VALID_EXPECTED_COMPLETION_DATE = COMPLETED.getExpectedCompletionDate().raw();
    private static final String VALID_COMPLETION_DATE = COMPLETED.getCompletionDate().raw();
    private static final String VALID_RECEIVED_DATE = COMPLETED.getReceivedDate().raw();
    private static final String VALID_JOB_STATUS = COMPLETED.getJobStatus().toString();
    private static final String VALID_FEE = COMPLETED.getFee().toString();

    private static final String VALID_INCOMPLETE_JOB_STATUS = INCOMPLETED.getJobStatus().toString();
    private static final String VALID_INCOMPLETE_DATE = null;

    private final MyCrm myCrm = new MyCrmBuilder().withProduct(COMPLETED.getProduct())
            .withContact(COMPLETED.getClient()).build();

    @Test
    public void toModelType_validJob_returnsJob() throws IllegalValueException {
        JsonAdaptedJob jsonJob = new JsonAdaptedJob(COMPLETED);
        Job job = jsonJob.toModelType(myCrm);
        assertEquals(COMPLETED, job);
    }

    @Test
    public void toModelType_validJobDetails_returnsJob() throws IllegalValueException {
        JsonAdaptedJob jsonJob = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        Job job = jsonJob.toModelType(myCrm);
        assertEquals(COMPLETED, job);
    }

    @Test
    public void toModelType_validJobDetailsIncomplete_returnsJob() throws IllegalValueException {
        JsonAdaptedJob jsonJob = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_INCOMPLETE_JOB_STATUS, VALID_RECEIVED_DATE, VALID_INCOMPLETE_DATE,
                VALID_FEE);
        Job job = jsonJob.toModelType(myCrm);
        assertEquals(INCOMPLETED, job);
    }

    @Test
    public void toModelType_invalidJobDescription_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(INVALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        String expectedMessage = JobDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_invalidClient_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, INVALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        assertThrows(IllegalValueException.class, JsonAdaptedJob.MESSAGE_INVALID_CLIENT, () -> job.toModelType(myCrm));

    }

    @Test
    public void toModelType_invalidProduct_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, INVALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        assertThrows(IllegalValueException.class, JsonAdaptedJob.MESSAGE_INVALID_PRODUCT, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_invalidExpectedCompletionDate_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
                INVALID_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        String expectedMessage = "Expected Completion Date: " + JobDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_invalidJobStatus_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, INVALID_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        assertThrows(IllegalValueException.class, MESSAGE_INVALID_STATUS, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_inProgressJobStatus_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_INCOMPLETE_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE,
                VALID_FEE);
        assertThrows(IllegalValueException.class, MESSAGE_INVALID_COMPLETION_DATE, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_invalidReceivedDate_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, INVALID_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        String expectedMessage = "Received Date: " + JobDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_invalidCompletionDate_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, INVALID_DATE, VALID_FEE);
        String expectedMessage = "Completion Date: " + JobDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_invalidJobFee_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, INVALID_FEE);
        String expectedMessage = JobFee.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_nullJobDescription_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(null, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobDescription.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_nullClient_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, null, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Contact.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));

    }

    @Test
    public void toModelType_nullProduct_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, null,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Product.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));

    }

    @Test
    public void toModelType_nullExpectedCompletionDate_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
                null, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Expected Completion Date");
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_nullJobStatus_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, null, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, VALID_FEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));
    }


    @Test
    public void toModelType_nullReceivedDate_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, null, VALID_COMPLETION_DATE, VALID_FEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Received Date");
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));
    }

    @Test
    public void toModelType_nullJobFee_throwsIllegalValueException() {
        JsonAdaptedJob job = new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT,
            VALID_EXPECTED_COMPLETION_DATE, VALID_JOB_STATUS, VALID_RECEIVED_DATE, VALID_COMPLETION_DATE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobFee.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> job.toModelType(myCrm));
    }
}
