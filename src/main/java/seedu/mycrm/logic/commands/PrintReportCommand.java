package seedu.mycrm.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;

/**
 * Prints out monthly job records and statistics.
 */
public class PrintReportCommand extends Command {
    public static final String SHOW_COMPLETED_FLAG = "-c";
    public static final String SHOW_IN_PROGRESS_FLAG = "-i";
    public static final String SHOW_PRODUCT_FLAG = "-p";

    public static final String COMMAND_WORD = "printReport";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Print monthly job report.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_REPORT_MESSAGE = "Opened report window.";

    public static final String MESSAGE_EMPTY_JOB_LIST = "There is no job this month.";

    private static final CommandType COMMAND_TYPE = CommandType.REPORT;

    private final String flag;

    /**
     * Creates an ListJobCommand to list jobs matching the {@code listPredicate}.
     */
    public PrintReportCommand(String flag) {
        requireNonNull(flag);
        this.flag = flag;
    }

    public PrintReportCommand() {
        this.flag = SHOW_COMPLETED_FLAG;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        requireNonNull(model);
        requireNonNull(flag);

        if (model.getFilteredAllJobList().isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_JOB_LIST);
        }

        return new CommandResult(SHOWING_REPORT_MESSAGE, COMMAND_TYPE, flag);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }
}
