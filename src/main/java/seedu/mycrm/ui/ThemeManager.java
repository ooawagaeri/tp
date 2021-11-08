package seedu.mycrm.ui;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.mycrm.commons.core.GuiSettings;
import seedu.mycrm.commons.core.LogsCenter;

/**
 * Manages the theme of MyCrm.
 */
public class ThemeManager {
    // Theme names
    public static final String THEME_DARK = "dark";
    public static final String THEME_LIGHT = "light";
    private static final ArrayList<String> themeNames = new ArrayList<>(List.of(THEME_DARK, THEME_LIGHT));

    private final Logger logger = LogsCenter.getLogger(getClass());

    // Urls of theme stylesheets
    private final String darkThemeUrl = getClass().getResource(UiPart.FXML_FILE_FOLDER + "DarkTheme.css")
            .toExternalForm();
    private final String lightThemeUrl = getClass().getResource(UiPart.FXML_FILE_FOLDER + "LightTheme.css")
            .toExternalForm();

    // Stylesheets of root node
    private final ObservableList<String> styleSheets;

    // Url of current theme stylesheet
    private String themeUrl;

    private String themeName;

    public ThemeManager(ObservableList<String> styleSheets) {
        this.styleSheets = styleSheets;
    }

    /**
     * Returns true if a theme with provided name exists.
     */
    public static boolean hasTheme(String themeName) {
        requireNonNull(themeName);

        return themeNames.contains(themeName);
    }

    public String getThemeName() {
        return themeName;
    }

    /**
     * Initializes Ui theme based on {@code guiSettings}.
     */
    protected void initTheme(GuiSettings guiSettings) {
        themeUrl = guiSettings.getThemeUrl() == null
                ? darkThemeUrl // If UserPref did not store theme, set Ui theme to dark by default
                : guiSettings.getThemeUrl();

        if (themeUrl.equals(darkThemeUrl)) {
            changeToDarkTheme();
        } else if (themeUrl.equals(lightThemeUrl)) {
            changeToLightTheme();
        } else {
            // the stored theme url is invalid
            logger.warning("Loaded theme url is invalid. Ui is set to dark theme.");
            changeToDarkTheme();
        }
    }

    /**
     * Returns the url of the latest theme user uses.
     */
    protected String getThemeUrl() {
        return themeUrl;
    }

    /**
     * Changes Ui theme based on provided theme name. Return true if a theme with the provided name exists, false
     * otherwise.
     */
    protected boolean changeTheme(String themeName) {
        switch (themeName) {
        case (THEME_DARK):
            changeToDarkTheme();
            return true;

        case (THEME_LIGHT):
            changeToLightTheme();
            return true;

        default:
            return false;
        }
    }

    /**
     * Changes Ui to dark theme.
     */
    protected void changeToDarkTheme() {
        logger.info("Changing to dark theme.");
        themeName = THEME_DARK;

        if (!styleSheets.contains(darkThemeUrl)) {
            styleSheets.removeAll(lightThemeUrl);
            styleSheets.add(darkThemeUrl);
            themeUrl = darkThemeUrl;
        }
    }

    /**
     * Changes Ui to light theme.
     */
    protected void changeToLightTheme() {
        logger.info("Changing to light theme.");
        themeName = THEME_LIGHT;

        if (!styleSheets.contains(lightThemeUrl)) {
            styleSheets.removeAll(darkThemeUrl);
            styleSheets.add(lightThemeUrl);
            themeUrl = lightThemeUrl;
        }
    }
}
