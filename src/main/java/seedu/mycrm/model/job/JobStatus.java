package seedu.mycrm.model.job;

/**
 * Represents a Job's completion in the myCrm.
 * Guarantees: immutable;
 */
public class JobStatus {

    private boolean isCompleted;
    private String value;

    /**
     * Constructs a {@code Job Status}.
     *
     * @param isCompleted Job is completed or not
     */
    public JobStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
        this.value = isCompleted ? "Completed"
                : "In Progress";
    }

    /**
     * Returns whether the Specific Job has been completed or not
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Mark a Specific Job to be completed.
     */
    public void markCompleted() {
        this.isCompleted = true;
        this.value = "Completed";
    }

    /**
     * Mark a Specific Job as incomplete.
     */
    public void markIncomplete() {
        this.isCompleted = false;
        this.value = "In Progress";
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JobStatus // instanceof handles nulls
                && value.equals(((JobStatus) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
