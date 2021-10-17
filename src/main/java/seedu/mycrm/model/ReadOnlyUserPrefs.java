package seedu.mycrm.model;

import java.nio.file.Path;

import seedu.mycrm.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getMyCrmFilePath();

}
