package seedu.mycrm.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;

public class SelectCommand extends Command {
    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Only used in conjunction with addJob or editJob.\n"
            + "Will select an item from a displayed list.\n"
            + "depending on whether a contact or product is currently being assigned to the job\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private static final CommandType COMMAND_TYPE = CommandType.JOBS;

    private final Index targetIndex;

    public SelectCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        return stateManager.handleIndex(targetIndex);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof SelectCommand // instanceof handles nulls
            && targetIndex.equals(((SelectCommand) other).targetIndex)); // state check
    }
}
