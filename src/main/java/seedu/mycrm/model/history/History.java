package seedu.mycrm.model.history;

import static java.util.Objects.requireNonNull;

public class History {

    private final String value;

    /**
     * Creates a history with the entered command info
     *
     * @param history
     */
    public History(String history) {
        requireNonNull(history);
        value = history;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof History // instanceof handles nulls
                && value.equals(((History) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
