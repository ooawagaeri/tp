package seedu.mycrm.logic.commands.history;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.model.Model.PREDICATE_SHOW_ALL_HISTORIES;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.model.Model;

public class HistoryCommand extends Command {
    public static final String COMMAND_WORD = "history";

    public static final String SHOWING_HISTORY_MESSAGE = "Commands history are shown";

    private static final CommandType COMMAND_TYPE = CommandType.HISTORY;

    @Override
    public CommandResult execute(Model model, StateManager stateManager) {
        requireNonNull(model);
        model.updateFilteredHistoryList(PREDICATE_SHOW_ALL_HISTORIES);
        return new CommandResult(SHOWING_HISTORY_MESSAGE, COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }
}
