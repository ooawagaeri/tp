package seedu.mycrm.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;

public class AbortCommand extends Command {
    public static final String COMMAND_WORD = "abort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Only used in conjunction with addJob or editJob when assigning product or contact to job\n"
            + "Will abort the current operation. For addJob the new job will not be added\n"
            + "For editJob the job will remain unedited\n";

    private static final CommandType COMMAND_TYPE = CommandType.JOBS;

    public AbortCommand() {

    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        return stateManager.handleAbort();
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AbortCommand); // instanceof handles nulls
    }
}
