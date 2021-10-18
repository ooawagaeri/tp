package seedu.mycrm.model.job;

import java.util.Objects;

import seedu.mycrm.model.contact.Contact;
import seedu.mycrm.model.products.Product;

public class Job {

    private JobDescription jobDescription;
    private Contact client;
    private Product product;
    private JobDeliveryDate deliveryDate;
    private JobStatus jobStatus;


    /**
     * Creates a new repair job.
     *
     * @param jobDescription Description of the repair job.
     * @param client The contact object that corresponds to the client making the job request.
     * @param product The product object that is to be repaired.
     * @param deliveryDate Expected date of delivery of repaired product.
     */
    public Job(JobDescription jobDescription, Contact client, Product product,
               JobDeliveryDate deliveryDate, JobStatus jobStatus) {
        this.jobDescription = jobDescription;
        this.client = client;
        this.product = product;
        this.deliveryDate = deliveryDate;
        this.jobStatus = jobStatus;
    }

    /**
     * Creates a new job with the jobDescription and deliveryDate.
     *
     * @param jobDescription Description of the repair job.
     * @param deliveryDate Expected date of delivery of repaired product.
     */
    public Job(JobDescription jobDescription, JobDeliveryDate deliveryDate) {
        this.jobDescription = jobDescription;
        this.client = null;
        this.product = null;
        this.deliveryDate = deliveryDate;
        this.jobStatus = new JobStatus(false);
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public JobDeliveryDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(JobDeliveryDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
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
     * Returns true if both jobs templates have the same fields.
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
                && Objects.equals(getDeliveryDate(), job.getDeliveryDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobDescription(), getClient(),
                getProduct(), getDeliveryDate(), getJobStatus());
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
