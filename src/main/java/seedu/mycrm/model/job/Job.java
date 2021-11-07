package seedu.mycrm.model.job;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Objects;

import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.product.Product;

/**
 * Represents a repair job in MyCRM.
 */
public class Job {

    private JobDescription jobDescription;
    private Contact client;
    private Product product;
    private JobDate receivedDate;
    private JobDate completionDate;
    private JobDate expectedCompletionDate;
    private JobStatus jobStatus;
    private JobFee fee;

    /**
     * Creates a new repair job.
     *
     * @param jobDescription Description of the repair job.
     * @param client The contact object that corresponds to the client making the job request.
     * @param product The product object that is to be repaired.
     * @param expectedCompletionDate Expected date of completion of repaired product.
     * @param jobStatus Repair job's completion status.
     * @param receivedDate Date repair job request was received from client.
     * @param completionDate Actual Date repair job was completed.
     * @param fee Fee charged to client for completion of repair.
     */
    public Job(JobDescription jobDescription, Contact client, Product product, JobDate expectedCompletionDate,
            JobStatus jobStatus, JobDate receivedDate, JobDate completionDate, JobFee fee) {

        this.jobDescription = jobDescription;
        this.client = client;
        this.product = product;
        this.expectedCompletionDate = expectedCompletionDate;
        this.jobStatus = jobStatus;
        this.receivedDate = receivedDate;
        this.completionDate = completionDate;
        this.fee = fee;
    }

    /**
     * Creates a new repair job.
     *
     * @param jobDescription Description of the repair job.
     * @param expectedCompletionDate Expected date of completion of repaired product.
     * @param receivedDate Date repair job request was received from client.
     * @param fee Fee charged to client for completion of repair.
     */
    public Job(JobDescription jobDescription, JobDate expectedCompletionDate,
            JobDate receivedDate, JobFee fee) {

        this.jobDescription = jobDescription;
        this.client = null;
        this.product = null;
        this.expectedCompletionDate = expectedCompletionDate;
        this.jobStatus = new JobStatus(false);
        this.receivedDate = receivedDate;
        this.fee = fee;
    }

    /**
     * Creates a new repair job by copying the attributes of the given {@code toCopyJob}.
     */
    public Job(Job toCopyJob) {
        this.jobDescription = new JobDescription(toCopyJob.getJobDescription().toString());
        this.client = toCopyJob.getClient();
        this.product = toCopyJob.getProduct();
        this.expectedCompletionDate = new JobDate(toCopyJob.getExpectedCompletionDate().raw());
        this.jobStatus = new JobStatus(toCopyJob.isCompleted());
        this.receivedDate = new JobDate(toCopyJob.getReceivedDate().raw());
        this.fee = new JobFee(toCopyJob.getFee().toString());
        this.completionDate = (toCopyJob.isCompleted())
                              ? new JobDate(toCopyJob.getCompletionDate().raw())
                              : null;
    }

    public JobDescription getJobDescription() {
        return jobDescription;
    }

    public Contact getClient() {
        return client;
    }

    public void setClient(Contact client) {
        this.client = client;
    }

    public boolean hasClient() {
        return client != null;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean hasProduct() {
        return product != null;
    }

    public JobDate getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public void setExpectedCompletionDate(JobDate expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public boolean isCompleted() {
        return jobStatus.isCompleted();
    }

    /**
     * Marks the job as completed. Sets the job status as complete and the completion date.
     * @param completionDate Date of completion of job.
     */
    public void markCompleted(JobDate completionDate) {
        requireNonNull(completionDate);

        jobStatus.markCompleted();
        this.completionDate = completionDate;
    }

    /**
     * Marks the job as incomplete.
     * Sets the job status as incomplete and completion date as null.
     */
    public void markIncomplete() {
        jobStatus.markIncomplete();
        this.completionDate = null;
    }

    public JobDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(JobDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public JobDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(JobDate completionDate) {
        this.completionDate = completionDate;
    }

    public JobFee getFee() {
        return fee;
    }

    public void setFee(JobFee fee) {
        this.fee = fee;
    }

    public String getClientEmail() {
        return client.getEmail().toString();
    }

    public boolean isReceivedThisMonth(LocalDate date) {
        return this.getReceivedDate().isThisMonth(date);
    }

    /**
     * Returns true this job is completed at the same month with the given {@code date}
     */
    public boolean isCompletedThisMonth(LocalDate date) {
        requireNonNull(date);

        if (this.getCompletionDate() == null) {
            return false;
        } else {
            return this.getCompletionDate().isThisMonth(date);
        }
    }

    /**
     * Returns true if both jobs have the same client, product and description
     * This defines a weaker notion of equality between two jobs.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
                && Objects.equals(getJobDescription(), otherJob.getJobDescription())
                && Objects.equals(getClient(), otherJob.getClient())
                && Objects.equals(getProduct(), otherJob.getProduct());
    }

    /**
     * Returns true if both jobs have the same fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Job)) {
            return false;
        }

        Job job = (Job) o;
        return Objects.equals(getJobStatus(), job.getJobStatus())
                && Objects.equals(getJobDescription(), job.getJobDescription())
                && Objects.equals(getClient(), job.getClient())
                && Objects.equals(getProduct(), job.getProduct())
                && Objects.equals(getExpectedCompletionDate(), job.getExpectedCompletionDate())
                && Objects.equals(getReceivedDate(), job.getReceivedDate())
                && Objects.equals(getCompletionDate(), job.getCompletionDate())
                && Objects.equals(getFee(), job.getFee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobDescription(), getClient(), getProduct(), getExpectedCompletionDate(),
                getJobStatus(), getReceivedDate(), getCompletionDate(), getFee());
    }

    @Override
    public String toString() {
        String jobString = jobDescription.toString();

        if (client != null) {
            jobString += String.format(" for %s", client.getName().fullName);
        }
        return jobString;
    }
}
