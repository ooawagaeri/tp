package seedu.mycrm.logic.commands;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private static final CommandType COMMAND_TYPE = CommandType.HELP;

    @Override
    public CommandResult execute(Model model, StateManager stateManager) {
        return new CommandResult(SHOWING_HELP_MESSAGE, COMMAND_TYPE);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }
}
