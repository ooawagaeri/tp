package seedu.mycrm.storage;

import static seedu.mycrm.storage.JsonAdaptedJob.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalJobs.COMPLETED;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;
import seedu.mycrm.model.job.JobStatus;


public class JsonAdaptedJobTest {
    private static final String INVALID_JOB_DESCRIPTION = " ";
    private static final String INVALID_DATE = "2021/01/01";
    private static final String INVALID_FEE = "a10.00";

    private static final String VALID_JOB_DESCRIPTION = COMPLETED.getJobDescription().toString();
    private static final String VALID_CLIENT = COMPLETED.getClient().toString();
    private static final String VALID_PRODUCT = COMPLETED.getProduct().toString();
    private static final String VALID_DATE = COMPLETED.getDeliveryDate().raw();
    private static final String VALID_JOB_STATUS = COMPLETED.getProduct().toString();
    private static final String VALID_FEE = COMPLETED.getFee().toString();

    @Test
    public void toModelType_invalidJobDescription_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(INVALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT, VALID_DATE,
                        VALID_JOB_STATUS, VALID_DATE, VALID_DATE, VALID_FEE);
        String expectedMessage = JobDescription.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidDeliveryDate_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT, INVALID_DATE,
                        VALID_JOB_STATUS, VALID_DATE, VALID_DATE, VALID_FEE);
        String expectedMessage = "Delivery Date: " + JobDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidReceivedDate_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT, VALID_DATE,
                        VALID_JOB_STATUS, INVALID_DATE, VALID_DATE, VALID_FEE);
        String expectedMessage = "Received Date: " + JobDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidCompletedDate_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT, VALID_DATE,
                        VALID_JOB_STATUS, VALID_DATE, INVALID_DATE, VALID_FEE);
        String expectedMessage = "Pending job should not have a completed date";
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_invalidJobFee_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT, VALID_DATE,
                        VALID_JOB_STATUS, VALID_DATE, VALID_DATE, INVALID_FEE);
        String expectedMessage = JobFee.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }

    @Test
    public void toModelType_nullJobStatus_throwsIllegalValueException() {
        JsonAdaptedJob job =
                new JsonAdaptedJob(VALID_JOB_DESCRIPTION, VALID_CLIENT, VALID_PRODUCT, VALID_DATE,
                        null, VALID_DATE, VALID_DATE, VALID_FEE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JobStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, job::toModelType);
    }
}
