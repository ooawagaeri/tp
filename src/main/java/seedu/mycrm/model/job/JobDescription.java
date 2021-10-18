package seedu.mycrm.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.util.AppUtil.checkArgument;

public class JobDescription {

    public static final String MESSAGE_CONSTRAINTS = "Job Description can take any values, and it should not be blank";

    /*
     * The first character of the job description must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    private static final String VALIDATION_REGEX = "[^\\s].*";

    private final String value;

    /**
     * Constructs a {@code Job Description}.
     *
     * @param jobDescription A valid job description.
     */
    public JobDescription(String jobDescription) {
        requireNonNull(jobDescription);
        checkArgument(isValidJobDescription(jobDescription), MESSAGE_CONSTRAINTS);
        value = jobDescription;
    }

    /**
     * Returns true if a given string is a valid job description.
     */
    public static boolean isValidJobDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobDescription // instanceof handles nulls
            && value.equals(((JobDescription) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
