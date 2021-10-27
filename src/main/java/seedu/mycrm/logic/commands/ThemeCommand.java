package seedu.mycrm.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.mycrm.logic.StateManager;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.model.Model;

/**
 * Change theme of Ui.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Change Ui theme.\n"
            + "Example: " + COMMAND_WORD + " THEME_NAME";

    public static final String MESSAGE_SUCCESS = "Change theme to %1$s";

    public static final String MESSAGE_THEME_NOT_EXIST = "This theme does not exist in MyCRM";

    private static final CommandType COMMAND_TYPE = CommandType.THEME;

    private final String themeName;

    public ThemeCommand(String themeName) {
        requireNonNull(themeName);

        this.themeName = themeName;
    }

    @Override
    public CommandResult execute(Model model, StateManager stateManager) throws CommandException {
        return new CommandResult(String.format(MESSAGE_SUCCESS, themeName), COMMAND_TYPE, themeName);
    }

    @Override
    public CommandType getType() {
        return COMMAND_TYPE;
    }

    @Override
    public String toString() {
        return "ThemeCommand: change to " + themeName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o instanceof ThemeCommand) {
            ThemeCommand cmd = (ThemeCommand) o;
            return cmd.themeName.equals(this.themeName);
        }
        return false;
    }
}
