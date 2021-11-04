package seedu.mycrm.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.THEME, "dark");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback", CommandType.THEME, "dark")));
        assertTrue(new CommandResult("feedback_").equals(new CommandResult("feedback_", CommandType.COMMON)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different", CommandType.THEME, "dark")));

        // different command type value -> returns false
        assertFalse(new CommandResult("_feedback_", CommandType.HELP).equals(
                new CommandResult("_feedback_", CommandType.PRODUCTS)));

        // different theme name -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", CommandType.THEME, "light")));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback", CommandType.JOBS);

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.JOBS).hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different", CommandType.JOBS).hashCode());

        // different command type -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", CommandType.HELP).hashCode());

        // different theme name -> returns different hashcode
        assertNotEquals(new CommandResult("Change", CommandType.THEME, "light").hashCode(),
                new CommandResult("Change", CommandType.THEME, "ligh").hashCode());
    }
}
