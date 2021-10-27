package seedu.mycrm.logic.commands.history;

import static java.util.Objects.requireNonNull;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.model.Model;


public class ClearHistoryCommand extends Command {
    public static final String COMMAND_WORD = "clearHistory";

    public static final String MESSAGE_SUCCESS = "History command data has been cleared";

    private static final CommandType COMMAND_TYPE = CommandType.HISTORY;

    @Override
    public CommandResult execute(Model model, StateManager stateManager) {
        requireNonNull(model);
        model.clearHistory();
        return new CommandResult(MESSAGE_SUCCESS, COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }
}
