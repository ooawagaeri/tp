package seedu.mycrm.logic.commands.jobs;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.job.Job;

public class DeleteJobCommand extends Command {
    public static final String COMMAND_WORD = "deleteJob";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the job identified by the index number used in the displayed job list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_JOB_SUCCESS = "Deleted Job: %1$s";

    private static final CommandType COMMAND_TYPE = CommandType.JOBS;

    private final Index targetIndex;

    public DeleteJobCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteJob(jobToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_JOB_SUCCESS, jobToDelete), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteJobCommand // instanceof handles nulls
            && targetIndex.equals(((DeleteJobCommand) other).targetIndex)); // state check
    }
}
