package seedu.mycrm.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.JobDeliveryDate;
import seedu.mycrm.model.job.JobDescription;
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

    /**
     * Constructs a {@code JsonAdaptedJob} with the given job details.
     */
    @JsonCreator
    public JsonAdaptedJob(@JsonProperty("jobDescription") String jobDescription, @JsonProperty("client") String client,
                          @JsonProperty("product") String product, @JsonProperty("deliveryDate") String deliveryDate,
                          @JsonProperty("jobStatus") String jobStatus) {
        this.jobDescription = jobDescription;
        this.client = client;
        this.product = product;
        this.deliveryDate = deliveryDate;
        this.jobStatus = jobStatus;
    }

    /**
     * Converts a given {@code Job} into this class for Jackson use.
     */
    public JsonAdaptedJob(Job source) {
        jobDescription = source.getJobDescription().toString();
        client = source.getClient().getName().toString();
        product = source.getProduct().getName().toString();
        deliveryDate = source.getDeliveryDate().raw();
        jobStatus = source.getJobStatus().toString();
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

        if (deliveryDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    JobDeliveryDate.class.getSimpleName()));
        }
        if (!JobDeliveryDate.isValidJobDeliveryDate(deliveryDate)) {
            throw new IllegalValueException(JobDeliveryDate.MESSAGE_CONSTRAINTS);
        }
        final JobDeliveryDate modelJobDeliveryDate = new JobDeliveryDate(deliveryDate);

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

        final Job modeJob = new Job(modelJobDescription, modelJobDeliveryDate);
        modeJob.setJobStatus(modelJobStatus);

        return modeJob;
    }
}
