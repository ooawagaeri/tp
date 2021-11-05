package seedu.mycrm.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.logic.commands.ThemeCommand.MESSAGE_THEME_NOT_EXIST;
import static seedu.mycrm.ui.ThemeManager.hasTheme;

import seedu.mycrm.logic.commands.ThemeCommand;
import seedu.mycrm.logic.parser.exceptions.ParseException;

/** Parses input arguments and creates a ThemeCommand object. */
public class ThemeCommandParser implements Parser<ThemeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ThemeCommand}
     * and returns a {@code ThemeCommand} object for execution.
     *
     * @throws ParseException if the theme name provided by user does not exist in MyCrm.
     */
    @Override
    public ThemeCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        String themeName = userInput.strip().toLowerCase();
        if (!hasTheme(themeName)) {
            throw new ParseException(MESSAGE_THEME_NOT_EXIST);
        }

        return new ThemeCommand(themeName);
    }
}
