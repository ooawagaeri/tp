package seedu.mycrm.logic.commands;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting myCrm as requested ...";

    private static final CommandType COMMAND_TYPE = CommandType.EXIT;

    @Override
    public CommandResult execute(Model model, StateManager stateManager) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }
}
