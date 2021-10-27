package seedu.mycrm.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    private final CommandType commandType;

    private final String themeName;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser} and {@code commandType},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser, CommandType commandType) {
        assert commandType != CommandType.THEME;

        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = commandType;
        this.themeName = null; // theme name is set to null by default
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, CommandType.COMMON);
    }

    /**
     * Constructs a {@code CommandResult} of theme command with the specified fields.
     */
    public CommandResult(String feedbackToUser, CommandType commandType, String themeName) {
        assert commandType == CommandType.THEME;
        requireNonNull(themeName);

        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.commandType = commandType;
        this.themeName = themeName;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    /**
     * Returns name of desired theme if the instance is created by {@code ThemeCommand}.
     */
    public String getThemeName() {
        return this.themeName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && commandType == otherCommandResult.commandType
                && (themeName == null || themeName.equals(otherCommandResult.themeName));
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, commandType);
    }

}
