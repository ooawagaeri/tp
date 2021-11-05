package seedu.mycrm.logic.parser;

import static seedu.mycrm.logic.commands.ThemeCommand.MESSAGE_THEME_NOT_EXIST;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.mycrm.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.mycrm.ui.ThemeManager.THEME_DARK;
import static seedu.mycrm.ui.ThemeManager.THEME_LIGHT;
import static seedu.mycrm.ui.ThemeManager.hasTheme;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.ThemeCommand;

public class ThemeCommandParserTest {
    @Test
    public void parse_validArgs_success() {
        assertParseSuccess(new ThemeCommandParser(), THEME_DARK, new ThemeCommand(THEME_DARK));
        assertParseSuccess(new ThemeCommandParser(), THEME_LIGHT.toUpperCase(), new ThemeCommand(THEME_LIGHT));
    }

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(new ThemeCommandParser(), getInvalidThemeName(), MESSAGE_THEME_NOT_EXIST);
    }

    private String getInvalidThemeName() {
        String invalidThemeName = THEME_DARK;
        while (hasTheme(invalidThemeName)) {
            invalidThemeName += THEME_DARK;
        }
        return invalidThemeName;
    }
}
