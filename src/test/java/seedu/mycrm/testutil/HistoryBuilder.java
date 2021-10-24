package seedu.mycrm.testutil;

import seedu.mycrm.model.history.History;

/**
 * A utility class to help with building History objects.
 */
public class HistoryBuilder {

    private static final String DEFAULT_HISTORY_COMMAND_ONE = "history";
    private static final String DEFAULT_HISTORY_COMMAND_TWO = "listJob";
    private static final String DEFAULT_HISTORY_COMMAND_THREE = "listContact";
    private static final String DEFAULT_HISTORY_COMMAND_FOUR = "anything";

    private String value;

    /**
     * Creates a {@code HistoryBuilder} with the default details.
     */
    public HistoryBuilder(DefaultHistoryIndex index) {
        switch (index) {
        case ONE:
            this.value = DEFAULT_HISTORY_COMMAND_ONE;
            break;
        case TWO:
            this.value = DEFAULT_HISTORY_COMMAND_TWO;
            break;
        case THREE:
            this.value = DEFAULT_HISTORY_COMMAND_THREE;
            break;
        case FOUR:
            this.value = DEFAULT_HISTORY_COMMAND_FOUR;
            break;
        default:
            assert false : "Enum not implemented";
        }
    }

    /**
     * Creates a {@code HistoryBuilder} with the default details.
     */
    public HistoryBuilder(History historyToCopy) {
        value = historyToCopy.toString();
    }

    /**
     * Sets the value of the {@code History} that we are building.
     */
    public HistoryBuilder withCommandText(String commandText) {
        value = commandText;
        return this;
    }

    public History build() {
        return new History(value);
    }

    public enum DefaultHistoryIndex { ONE, TWO, THREE, FOUR };
}
