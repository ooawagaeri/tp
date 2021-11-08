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
import seedu.mycrm.model.job.JobDate;

public class CompleteJobCommand extends Command {
    public static final String COMMAND_WORD = "completeJob";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "Marks the job identified by the index number in the displayed job list as complete.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[COMPLETION DATE (in dd/MM/YYYY)]\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Repair job marked as complete: %1$s";

    private static final CommandType COMMAND_TYPE = CommandType.JOBS;

    private final Index targetIndex;
    private final JobDate completionDate;

    /**
     * Constructs a CompleteJobCommand object
     * @param targetIndex Index of job from displayed list that should be marked complete
     * @param completionDate Date job was completed
     */
    public CompleteJobCommand(Index targetIndex, JobDate completionDate) {
        this.targetIndex = targetIndex;
        this.completionDate = completionDate;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);

        List<Job> lastShownList = model.getFilteredJobList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_DISPLAYED_INDEX);
        }

        Job jobToMarkComplete = lastShownList.get(targetIndex.getZeroBased());

        if (jobToMarkComplete.isCompleted()) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_COMPLETE_REQUEST);
        }

        if (completionDate.value.isBefore(jobToMarkComplete.getReceivedDate().value)) {
            throw new CommandException(Messages.MESSAGE_INVALID_JOB_COMPLETION_DATE);
        }

        Job copiedJob = new Job(jobToMarkComplete);
        copiedJob.markCompleted(completionDate);

        model.setJob(jobToMarkComplete, copiedJob);
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
