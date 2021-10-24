package seedu.mycrm.logic.commands.history;

import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_HISTORIES;

public class ClearHistoryCommand extends Command {
    public static final String COMMAND_WORD = "clearHistory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clear all the history commands.\n";

    public static final String SHOWING_CLEARHISTORY_MESSAGE = "History command data has been cleared";

    private static final CommandType COMMAND_TYPE = CommandType.HISTORY;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearHistory();
        return new CommandResult(SHOWING_CLEARHISTORY_MESSAGE, COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }
}
