package seedu.mycrm.logic.commands.jobs;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.commons.core.index.Index;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.job.Job;

public class CompleteJobCommand extends Command {
    public static final String COMMAND_WORD = "completeJob";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + "Marks the job identified by the index number in the displayed job list as complete.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Repair job marked as complete: %1$s";

    private static final CommandType COMMAND_TYPE = CommandType.JOBS;

    private final Index targetIndex;

    public CompleteJobCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Job> lastShownList = model.getFilteredJobList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToMarkComplete = lastShownList.get(targetIndex.getZeroBased());

        if (jobToMarkComplete.isCompleted()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_COMPLETE_REQUEST);
        }

        jobToMarkComplete.markCompleted();
        model.setJob(jobToMarkComplete, jobToMarkComplete);
        model.updateFilteredJobList(Model.PREDICATE_SHOW_ALL_INCOMPLETE_JOBS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, jobToMarkComplete), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CompleteJobCommand // instanceof handles nulls
            && targetIndex.equals(((CompleteJobCommand) other).targetIndex)); // state check
    }
}
