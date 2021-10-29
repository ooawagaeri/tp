package seedu.mycrm.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.mycrm.logic.commands.ThemeCommand.MESSAGE_THEME_NOT_EXIST;
import static seedu.mycrm.ui.ThemeManager.hasTheme;

import seedu.mycrm.logic.commands.ThemeCommand;
import seedu.mycrm.logic.parser.exceptions.ParseException;

public class ThemeCommandParser implements Parser<ThemeCommand> {

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
