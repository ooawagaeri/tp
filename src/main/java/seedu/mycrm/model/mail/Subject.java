package seedu.mycrm.model.mail;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.util.AppUtil.checkArgument;

/**
 * Represents a Template's subject in the myCrm.
 * Guarantees: immutable; is valid as declared in {@link #isValidSubject(String)}
 */
public class Subject {

    public static final String MESSAGE_CONSTRAINTS =
            "Subject should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * The first character of the subject must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * This regex ensures that only alphanumerics and spaces in between are accepted.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String subject;

    /**
     * Constructs a {@code Name}.
     *
     * @param subject valid subject header.
     */
    public Subject(String subject) {
        requireNonNull(subject);
        checkArgument(isValidSubject(subject), MESSAGE_CONSTRAINTS);
        this.subject = subject;
    }

    /**
     * Returns true if a given string is a valid subject.
     *
     * @param test target subject to test
     */
    public static boolean isValidSubject(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return subject;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Subject
                && subject.equals(((Subject) other).subject));
    }

    @Override
    public int hashCode() {
        return subject.hashCode();
    }

}
