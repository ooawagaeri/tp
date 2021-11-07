package seedu.mycrm.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class JobDate {
    public static final String MESSAGE_CONSTRAINTS = "Date should follow the format dd/MM/YYYY";

    private static final DateTimeFormatter VALID_INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    public final LocalDate value;

    /**
     * Constructs a {@code JobDate}.
     *
     * @param date A string representation of the date.
     */
    public JobDate(String date) {
        requireNonNull(date);
        checkArgument(isValidJobDate(date), MESSAGE_CONSTRAINTS);
        this.value = LocalDate.parse(date, VALID_INPUT_FORMAT);;
    }

    /**
     * Returns current date as a JobDate object.
     */
    public static JobDate getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        return new JobDate(currentDate.format(VALID_INPUT_FORMAT));
    }

    /**
     * Returns true if the given string for the date conforms to the correct format.
     */
    public static boolean isValidJobDate(String test) {
        try {
            LocalDate.parse(test, VALID_INPUT_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if this JobDate is in this month.
     */
    public boolean isThisMonth(LocalDate date) {
        return this.value.getMonth().equals(date.getMonth())
                && this.value.getYear() == date.getYear();
    }

    /**
     * Returns original date string representation format.
     *
     * @return original string format
     */
    public String raw() {
        if (value == null) {
            return null;
        }
        return value.format(VALID_INPUT_FORMAT);
    }

    @Override
    public String toString() {
        if (value == null) {
            return null;
        }
        return value.format(DISPLAY_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof JobDate // instanceof handles nulls
            && value.equals(((JobDate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
