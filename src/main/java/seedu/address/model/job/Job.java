package seedu.address.model.job;

import java.util.Objects;

import seedu.address.model.contact.Contact;

public class Job {

    private JobDescription jobDescription;
    private Contact client;
    private String deliveryDate;
    private boolean isCompleted;

    /**
     * Creates a job with the jobDescription and deliveryDate
     *
     * @param jobDescription
     */
    public Job(JobDescription jobDescription, String deliveryDate) {
        this.jobDescription = jobDescription;
        this.client = null;
        this.deliveryDate = deliveryDate;
        this.isCompleted = false;
    }

    /**
     * Creates a job with all fields.
     */
    public Job(JobDescription jobDescription, Contact client, String deliveryDate, boolean completionStatus) {
        this.jobDescription = jobDescription;
        this.client = client;
        this.deliveryDate = deliveryDate;
        this.isCompleted = completionStatus;
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

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Returns true if both jobs have the same client and description
     * This defines a weaker notion of equality between two jobs.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
            && otherJob.getJobDescription().equals(getJobDescription())
            && otherJob.getClient().equals(getClient());

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
        return isCompleted() == job.isCompleted() && getJobDescription().equals(job.getJobDescription())
                && getClient().equals(job.getClient()) && getDeliveryDate().equals(job.getDeliveryDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobDescription(), getClient(), getDeliveryDate(), isCompleted());
    }

    @Override
    public String toString() {
        return String.format("Job Description: %s; Client: %s", jobDescription, client);
    }
}
