package seedu.mycrm.model.job;

import java.util.Objects;

import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.products.Product;

/**
 * Represents a repair job in MyCRM.
 */
public class Job {

    private JobDescription jobDescription;
    private Contact client;
    private Product product;
    private JobDate receivedDate;
    private JobDate completedDate;
    private JobDate deliveryDate;
    private JobStatus jobStatus;
    private JobFee fee;

    /**
     * Creates a new repair job.
     *
     * @param jobDescription Description of the repair job.
     * @param client The contact object that corresponds to the client making the job request.
     * @param product The product object that is to be repaired.
     * @param deliveryDate Expected date of delivery of repaired product.
     * @param jobStatus Repair job's completion status.
     * @param receivedDate Date repair job request was received from client.
     * @param completedDate Date repair job was completed.
     * @param fee Fee charged to client for completion of repair.
     */
    public Job(JobDescription jobDescription, Contact client, Product product, JobDate deliveryDate,
               JobStatus jobStatus, JobDate receivedDate, JobDate completedDate, JobFee fee) {
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
     * Creates a new repair job.
     *
     * @param jobDescription Description of the repair job.
     * @param deliveryDate Expected date of delivery of repaired product.
     * @param receivedDate Date repair job request was received from client.
     * @param fee Fee charged to client for completion of repair.
     */
    public Job(JobDescription jobDescription, JobDate deliveryDate,
               JobDate receivedDate, JobFee fee) {
        this.jobDescription = jobDescription;
        this.client = null;
        this.product = null;
        this.deliveryDate = deliveryDate;
        this.jobStatus = new JobStatus(false);
        this.receivedDate = receivedDate;
        this.completedDate = completedDate;
        this.fee = fee;
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

    public JobDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(JobDate deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public void markCompleted() {
        jobStatus.markCompleted();
    }

    public JobDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(JobDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public JobDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(JobDate completedDate) {
        this.completedDate = completedDate;
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
                && Objects.equals(getDeliveryDate(), job.getDeliveryDate())
                && Objects.equals(getReceivedDate(), job.getReceivedDate())
                && Objects.equals(getCompletedDate(), job.getCompletedDate())
                && Objects.equals(getFee(), job.getFee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobDescription(), getClient(), getProduct(), getDeliveryDate(),
                getJobStatus(), getReceivedDate(), getCompletedDate(), getFee());
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
