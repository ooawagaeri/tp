package seedu.mycrm.testutil;

import static seedu.mycrm.testutil.TypicalContacts.ALICE;
import static seedu.mycrm.testutil.TypicalProducts.ASUS_GPU;

import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;
import seedu.mycrm.model.job.JobStatus;
import seedu.mycrm.model.product.Product;

/**
 * A utility class to help with building Job objects.
 */
public class JobBuilder {

    private static final String DEFAULT_JOB_DESCRIPTION = "Some description";
    private static final boolean DEFAULT_COMPLETION_STATUS = false;
    private static final String DEFAULT_DELIVERY_DATE = "12/12/2021";
    private static final String DEFAULT_RECEIVED_DATE = "11/12/2021";
    private static final String DEFAULT_COMPLETE_DATE = "13/12/2021";
    private static final String DEFAULT_FEE = "$30.00";

    private JobDescription jobDescription;
    private Contact client;
    private Product product;
    private JobDate deliveryDate;
    private JobStatus completionStatus;
    private JobDate receivedDate;
    private JobDate completedDate;
    private JobFee fee;

    /**
     * Creates a {@code JobBuilder} with the default details.
     */
    public JobBuilder() {
        jobDescription = new JobDescription(DEFAULT_JOB_DESCRIPTION);
        deliveryDate = new JobDate(DEFAULT_DELIVERY_DATE);
        completionStatus = new JobStatus(DEFAULT_COMPLETION_STATUS);
        client = ALICE;
        product = ASUS_GPU;
        receivedDate = new JobDate(DEFAULT_RECEIVED_DATE);
        completedDate = null;
        fee = new JobFee(DEFAULT_FEE);
    }

    /**
     * Initializes the JobBuilder with the data of {@code jobToCopy}.
     */
    public JobBuilder(Job jobToCopy) {
        jobDescription = jobToCopy.getJobDescription();
        deliveryDate = jobToCopy.getDeliveryDate();
        client = jobToCopy.getClient();
        product = jobToCopy.getProduct();
        completionStatus = jobToCopy.getJobStatus();
        receivedDate = jobToCopy.getReceivedDate();
        completedDate = jobToCopy.getCompletedDate();
        fee = jobToCopy.getFee();
    }

    /**
     * Sets the {@code JobDescription} of the {@code Job} that we are building.
     */
    public JobBuilder withJobDescription(String jobDescription) {
        this.jobDescription = new JobDescription(jobDescription);
        return this;
    }

    /**
     * Sets the {@code deliveryDate} of the {@code Job} that we are building.
     */
    public JobBuilder withDeliveryDate(String deliveryDate) {
        this.deliveryDate = new JobDate(deliveryDate);
        return this;
    }

    /**
     * Sets the {@code client} of the {@code Job} that we are building.
     */
    public JobBuilder withClient(Contact client) {
        this.client = client;
        return this;
    }

    /**
     * Sets the {@code product} of the {@code Job} that we are building.
     */
    public JobBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    /**
     * Sets the {@code completionStatus} of the {@code Job} that we are building.
     */
    public JobBuilder withCompletionStatus(boolean completionStatus) {
        this.completionStatus = new JobStatus(completionStatus);
        return this;
    }

    /**
     * Sets the {@code receivedDate} of the {@code Job} that we are building.
     */
    public JobBuilder withReceivedDate(String receivedDate) {
        this.receivedDate = new JobDate(receivedDate);
        return this;
    }

    /**
     * Sets the {@code completedDate} of the {@code Job} that we are building.
     */
    public JobBuilder withCompletedDate(String completedDate) {
        this.completedDate = new JobDate(completedDate);
        return this;
    }

    /**
     * Sets the {@code fee} of the {@code Job} that we are building.
     */
    public JobBuilder withCompletedfee(String fee) {
        this.fee = new JobFee(fee);
        return this;
    }

    /**
     * Constructs the job object.
     */
    public Job build() {
        return new Job(jobDescription, client, product, deliveryDate, completionStatus,
                receivedDate, completedDate, fee);
    }
}
