package seedu.mycrm.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;

public class ExportReportCommand extends Command {
    public static final String COMMAND_WORD = "exportReport";

    public static final String SHOWING_EXPORT_MESSAGE = "Printing report.";

    public static final String MESSAGE_EMPTY_JOB_LIST = "There is no job this month.";

    private static final CommandType COMMAND_TYPE = CommandType.EXPORT;

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);

        if (model.getFilteredAllJobList().isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_JOB_LIST);
        }

        return new CommandResult(SHOWING_EXPORT_MESSAGE, COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }
}
