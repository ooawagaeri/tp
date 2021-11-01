package seedu.mycrm.model.contact;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.util.AppUtil.checkArgument;

import seedu.mycrm.logic.parser.ParserUtil;
import seedu.mycrm.logic.parser.exceptions.ParseException;
/**
 * Represents a Contact's phone number in the myCrm.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone implements ContactComponent<Phone> {

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    private static final Phone EMPTY_PHONE = new Phone();
    public final String value;

    /**
     * Constructs a {@code Phone}.
     */
    public Phone() {
        this.value = null;
    }

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    public static Phone getPhone(String phone) throws ParseException {
        requireNonNull(phone);

        if (phone.length() == 0) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        } else {
            return ParserUtil.parsePhone(phone);
        }
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public static Phone getEmptyPhone() {
        return EMPTY_PHONE;
    }

    @Override
    public boolean isEmpty() {
        return value == null;
    }

    @Override
    public String orElse(String alternativeString) {
        return this.isEmpty() ? alternativeString : this.value;
    }

    @Override
    public String toString() {
        return isEmpty() ? "" : value;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        if (value == null) {
            return ((Phone) other).value == null;
        }

        if (other instanceof Phone // instanceof handles nulls
                && value.equals(((Phone) other).value)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
