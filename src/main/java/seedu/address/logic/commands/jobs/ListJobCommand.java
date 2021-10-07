package seedu.address.logic.commands.jobs;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_JOBS;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ListJobCommand extends Command {
    public static final String COMMAND_WORD = "listJob";

    public static final String MESSAGE_SUCCESS = "Listed all jobs";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredJobList(PREDICATE_SHOW_ALL_JOBS);
        return new CommandResult(MESSAGE_SUCCESS, false, false, false, true);
    }
}
