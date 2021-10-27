package seedu.mycrm.ui;

import java.util.logging.Logger;

import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.mycrm.commons.core.GuiSettings;
import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.logic.parser.exceptions.ParseException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    // Urls of theme stylesheets
    private final String darkThemeUrl = getClass().getResource(UiPart.FXML_FILE_FOLDER + "DarkTheme.css")
            .toExternalForm();
    private final String lightThemeUrl = getClass().getResource(UiPart.FXML_FILE_FOLDER + "LightTheme.css")
            .toExternalForm();


    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container

    private ResultDisplay resultDisplay;
    private MainDisplay mainDisplay;
    private SideDisplay sideDisplay;
    private HelpWindow helpWindow;

    // Url of current theme stylesheet
    private String themeUrl;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane mainDisplayPlaceholder;

    @FXML
    private StackPane sideDisplayPlaceholder;

    @FXML
    private StackPane statusBarPlaceholder;


    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        setTheme(logic.getGuiSettings());

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts(HostServices hostServices) {
        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        mainDisplay = new MainDisplay();
        helpWindow.setGetHostController(hostServices);
        mainDisplay.init(logic, hostServices);
        mainDisplayPlaceholder.getChildren().add(mainDisplay.getRoot());

        sideDisplay = new SideDisplay();
        sideDisplay.init(logic);
        sideDisplayPlaceholder.getChildren().add(sideDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getMyCrmFilePath());
        statusBarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(logic, this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Set the Ui theme based on {@code guiSettings}.
     */
    public void setTheme(GuiSettings guiSettings) {
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
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY(), themeUrl);
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Changes Ui to dark theme.
     */
    @FXML
    private void changeToDarkTheme() {
        logger.info("Changing to dark theme.");
        ObservableList<String> styleSheets = primaryStage.getScene().getStylesheets();

        if (!styleSheets.contains(darkThemeUrl)) {
            styleSheets.removeAll(lightThemeUrl);
            styleSheets.add(darkThemeUrl);
            themeUrl = darkThemeUrl;
        }
    }

    /**
     * Changes Ui to light theme.
     */
    @FXML
    private void changeToLightTheme() {
        logger.info("Changing to light theme.");
        ObservableList<String> styleSheets = primaryStage.getScene().getStylesheets();

        if (!styleSheets.contains(lightThemeUrl)) {
            styleSheets.removeAll(darkThemeUrl);
            styleSheets.add(lightThemeUrl);
            themeUrl = lightThemeUrl;
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.mycrm.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            CommandType commandType = commandResult.getCommandType();
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            switch (commandType) {
            case CONTACTS:
            case TEMPLATE:
            case PRODUCTS:
            case HISTORY:
                sideDisplay.switchTab(commandType);
                break;

            case MAIL:
                mainDisplay.showMailList();;
                break;

            case JOBS:
                mainDisplay.showJobList();;
                break;

            case HELP:
                handleHelp();
                break;

            case EXIT:
                handleExit();
                break;

            case COMMON:
                // do nothing
                break;

            default:
                assert false;
            }
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
