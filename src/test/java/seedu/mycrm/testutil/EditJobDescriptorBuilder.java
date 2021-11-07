package seedu.mycrm.testutil;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.jobs.EditJobCommand;
import seedu.mycrm.model.job.Job;
import seedu.mycrm.model.job.JobDate;
import seedu.mycrm.model.job.JobDescription;
import seedu.mycrm.model.job.JobFee;

public class EditJobDescriptorBuilder {
    private EditJobCommand.EditJobDescriptor jobDescriptor;

    public EditJobDescriptorBuilder() {
        jobDescriptor = new EditJobCommand.EditJobDescriptor();
    }

    public EditJobDescriptorBuilder(EditJobCommand.EditJobDescriptor jobDescriptor) {
        this.jobDescriptor = new EditJobCommand.EditJobDescriptor(jobDescriptor);
    }

    /**
     * Creates an {@code EditContactDescriptor} with fields containing {@code job}'s details
     */
    public EditJobDescriptorBuilder(Job job, Index clientIndex, Index productIndex) {
        jobDescriptor = new EditJobCommand.EditJobDescriptor();
        jobDescriptor.setJobDescription(job.getJobDescription());
        jobDescriptor.setReceivedDate(job.getReceivedDate());
        jobDescriptor.setExpectedCompletionDate(job.getExpectedCompletionDate());
        jobDescriptor.setFee(job.getFee());
        jobDescriptor.setClientIndex(clientIndex);
        jobDescriptor.setProductIndex(productIndex);
    }

    /**
     * Sets the {@code jobDescription} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withJobDescription(String jobDescription) {
        jobDescriptor.setJobDescription(new JobDescription(jobDescription));
        return this;
    }

    /**
     * Sets the {@code fee} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withFee(String fee) {
        jobDescriptor.setFee(new JobFee(fee));
        return this;
    }

    /**
     * Sets the {@code expectedCompletionDate} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withExpectedCompletionDate(String expectedCompletionDate) {
        jobDescriptor.setExpectedCompletionDate(new JobDate(expectedCompletionDate));
        return this;
    }

    /**
     * Sets the {@code receivedDate} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withReceivedDate(String receivedDate) {
        jobDescriptor.setReceivedDate(new JobDate(receivedDate));
        return this;
    }

    /**
     * Sets the {@code clientIndex} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withClientIndex(int clientIndex) {
        jobDescriptor.setClientIndex(Index.fromOneBased(clientIndex));
        return this;
    }

    /**
     * Sets the {@code productIndex} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withProductIndex(int productIndex) {
        jobDescriptor.setProductIndex(Index.fromOneBased(productIndex));
        return this;
    }

    public EditJobCommand.EditJobDescriptor build() {
        return jobDescriptor;
    }
}
