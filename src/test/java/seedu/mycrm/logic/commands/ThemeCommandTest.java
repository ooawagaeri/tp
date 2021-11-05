package seedu.mycrm.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.mycrm.logic.commands.ThemeCommand.MESSAGE_SUCCESS;

import org.junit.jupiter.api.Test;

import seedu.mycrm.model.Model;
import seedu.mycrm.model.ModelManager;

public class ThemeCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_changeTheme_success() {
        String themeName = "Kyaaa";
        CommandResult expectedCommandResult = new CommandResult(String.format(MESSAGE_SUCCESS, themeName),
                CommandType.THEME, themeName);
        assertCommandSuccess(new ThemeCommand(themeName), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void type() {
        String themeName = "skadi";
        ThemeCommand command = new ThemeCommand(themeName);
        assertEquals(command.getType(), CommandType.THEME);
    }

    @Test
    public void equals() {
        ThemeCommand firstCommand = new ThemeCommand("first");
        ThemeCommand secondCommand = new ThemeCommand("second");

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ThemeCommand firstCommandCopy = new ThemeCommand("first");
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different contact -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
