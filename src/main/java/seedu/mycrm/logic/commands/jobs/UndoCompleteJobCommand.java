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

public class UndoCompleteJobCommand extends Command {
    public static final String COMMAND_WORD = "undoCompleteJob";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Reverts the status of the completed job identified by the INDEX "
            + "in the displayed job list to incomplete.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Repair job's status reverted to in-progress: %1$s";

    private static final CommandType COMMAND_TYPE = CommandType.JOBS;

    private final Index targetIndex;

    public UndoCompleteJobCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToRevertStatus = lastShownList.get(targetIndex.getZeroBased());

        if (!jobToRevertStatus.isCompleted()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_UNDO_COMPLETE_REQUEST);
        }

        Job copiedJob = new Job(jobToRevertStatus);
        copiedJob.markIncomplete();

        model.setJob(jobToRevertStatus, copiedJob);
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_INCOMPLETE_JOBS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, jobToRevertStatus), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UndoCompleteJobCommand // instanceof handles nulls
            && targetIndex.equals(((UndoCompleteJobCommand) other).targetIndex)); // state check
    }
}
