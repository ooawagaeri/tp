package seedu.mycrm.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;
import seedu.mycrm.model.job.JobStatus;

/**
 * Jackson-friendly version of {@link Job}.
 */
class JsonAdaptedJob {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Job's %s field is missing!";

    private String jobDescription;
    private String client;
    private String product;
    private String deliveryDate;
    private String jobStatus;
    private String receivedDate;
    private String completedDate;
    private String fee;

    /**
     * Constructs a {@code JsonAdaptedJob} with the given job details.
     */
    @JsonCreator
    public JsonAdaptedJob(@JsonProperty("jobDescription") String jobDescription, @JsonProperty("client") String client,
                          @JsonProperty("product") String product, @JsonProperty("deliveryDate") String deliveryDate,
                          @JsonProperty("jobStatus") String jobStatus,
                          @JsonProperty("receivedDate") String receivedDate,
                          @JsonProperty("completedDate") String completedDate,
                          @JsonProperty("fee") String fee) {
        this.jobDescription = jobDescription;
        this.client = client;
        this.product = product;
        this.deliveryDate = deliveryDate;
        this.jobStatus = jobStatus;
        this.receivedDate = receivedDate;
        this.completedDate = completedDate;
        this.fee = fee;
    }

    /**
     * Converts a given {@code Job} into this class for Jackson use.
     */
    public JsonAdaptedJob(Job source) {
        jobDescription = source.getJobDescription().toString();

        if (source.getClient() != null) {
            client = source.getClient().getName().toString();
        }

        if (source.getProduct() != null) {
            product = source.getProduct().getName().toString();
        }

        jobStatus = source.getJobStatus().toString();
        receivedDate = source.getReceivedDate().raw();

        if (source.isCompleted()) {
            completedDate = source.getCompletedDate().raw();
        }

        if (source.getFee() != null) {
            fee = source.getFee().toString();
        }

        if (source.getDeliveryDate() != null) {
            deliveryDate = source.getDeliveryDate().raw();
        }
    }

    public String getClient() {
        return client;
    }

    public String getProduct() {
        return product;
    }

    /**
     * Converts this Jackson-friendly adapted job object into the model's {@code Job} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted job.
     */
    public Job toModelType() throws IllegalValueException {

        if (jobDescription == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobDescription.class.getSimpleName()));
        }
        if (!JobDescription.isValidJobDescription(jobDescription)) {
            throw new IllegalValueException(JobDescription.MESSAGE_CONSTRAINTS);
        }
        final JobDescription modelJobDescription = new JobDescription(jobDescription);

        JobDate modelJobDeliveryDate = null;
        if (deliveryDate != null) {
            if (JobDate.isValidJobDate(deliveryDate)) {
                modelJobDeliveryDate = new JobDate(deliveryDate);
            } else {
                throw new IllegalValueException(JobDate.MESSAGE_CONSTRAINTS);
            }
        }

        JobDate modelJobReceivedDate = null;
        if (receivedDate != null) {
            if (JobDate.isValidJobDate(receivedDate)) {
                modelJobReceivedDate = new JobDate(receivedDate);
            } else {
                throw new IllegalValueException(JobDate.MESSAGE_CONSTRAINTS);
            }
        }

        JobDate modelJobCompletedDate = null;
        if (completedDate != null) {
            if (JobDate.isValidJobDate(completedDate)) {
                modelJobCompletedDate = new JobDate(completedDate);
            } else {
                throw new IllegalValueException(JobDate.MESSAGE_CONSTRAINTS);
            }
        }

        JobFee modelJobFee = null;
        if (fee != null) {
            if (JobFee.isValidJobFee(fee)) {
                modelJobFee = new JobFee(fee);
            } else {
                throw new IllegalValueException(JobFee.MESSAGE_CONSTRAINTS);
            }
        }

        if (jobStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobStatus.class.getSimpleName()));
        }
        final JobStatus modelJobStatus;
        if (jobStatus.equals("Completed")) {
            modelJobStatus = new JobStatus(true);
        } else {
            modelJobStatus = new JobStatus(false);
        }

        final Job modeJob = new Job(modelJobDescription, modelJobDeliveryDate,
                modelJobReceivedDate, modelJobFee);
        modeJob.setJobStatus(modelJobStatus);
        modeJob.setCompletedDate(modelJobCompletedDate);

        return modeJob;
    }
}
