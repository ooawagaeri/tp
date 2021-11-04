package seedu.mycrm.model.mail;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.util.AppUtil.checkArgument;

/**
 * Represents a Template's body in the body book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBody(String)}
 */
public class Body {

    public static final String MESSAGE_CONSTRAINTS = "Body can take any values, and it should not be blank";

    /**
     * The first character of the template must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     * This regex ensures that any character is accepted.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String body;

    /**
     * Constructs an {@code Body}.
     *
     * @param body valid body.
     */
    public Body(String body) {
        requireNonNull(body);
        checkArgument(isValidBody(body), MESSAGE_CONSTRAINTS);
        this.body = body;
    }

    /**
     * Returns true if a given string is a valid body.
     *
     * @param test target body to test
     */
    public static boolean isValidBody(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return body;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Body // instanceof handles nulls
                && body.equals(((Body) other).body)); // state check
    }

    @Override
    public int hashCode() {
        return body.hashCode();
    }

}
