package seedu.mycrm.logic.commands.jobs;

import static java.util.Objects.requireNonNull;

import seedu.mycrm.commons.core.Messages;
import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.Command;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.model.Model;
import seedu.mycrm.model.job.JobContainsKeywordsPredicate;

public class FindJobCommand extends Command {
    public static final String COMMAND_WORD = "findJob";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all jobs whose descriptions contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Graphics card replacement needed";

    private static final CommandType COMMAND_TYPE = CommandType.JOBS;

    private final JobContainsKeywordsPredicate predicate;

    public FindJobCommand(JobContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) {
        requireNonNull(model);
        model.updateFilteredJobList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_JOBS_LISTED_OVERVIEW, model.getFilteredJobList().size()), COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindJobCommand // instanceof handles nulls
                && predicate.equals(((FindJobCommand) other).predicate)); // state check
    }
}
