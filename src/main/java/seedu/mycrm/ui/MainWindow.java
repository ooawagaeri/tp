package seedu.mycrm.ui;

import java.util.logging.Logger;

import javafx.application.HostServices;
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

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final ThemeManager themeManager;

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container

    private ResultDisplay resultDisplay;
    private MainDisplay mainDisplay;
    private SideDisplay sideDisplay;
    private HelpWindow helpWindow;
    private ReportWindow reportWindow;

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

        // Initialize theme manager
        themeManager = new ThemeManager(primaryStage.getScene().getStylesheets());

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        themeManager.initTheme(logic.getGuiSettings());

        helpWindow = new HelpWindow();

        reportWindow = new ReportWindow(logic);
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
        mainDisplay.init(logic, hostServices);
        mainDisplayPlaceholder.getChildren().add(mainDisplay.getRoot());

        sideDisplay = new SideDisplay();
        sideDisplay.init(logic);
        sideDisplayPlaceholder.getChildren().add(sideDisplay.getRoot());

        helpWindow.setGetHostController(hostServices);

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
                (int) primaryStage.getX(), (int) primaryStage.getY(), themeManager.getThemeUrl());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        reportWindow.hide();
        primaryStage.hide();
    }

    private void handlePrintReport(String commandFlag) {
        reportWindow.fillInnerParts();
        reportWindow.changeTheme(themeManager.getThemeName());
        reportWindow.switchTab(commandFlag);
        if (!reportWindow.isShowing()) {
            reportWindow.show();
        } else {
            reportWindow.focus();
        }
    }

    private void handleExport() {
        reportWindow.fillInnerParts();
        reportWindow.handlePrint();
    }

    /**
     * Changes Ui to dark theme.
     */
    @FXML
    private void changeToDarkTheme() {
        themeManager.changeToDarkTheme();
    }

    /**
     * Changes Ui to light theme.
     */
    @FXML
    private void changeToLightTheme() {
        themeManager.changeToLightTheme();
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
                mainDisplay.showMailList();
                break;

            case JOBS:
                mainDisplay.showJobList();
                break;

            case HELP:
                handleHelp();
                break;

            case EXIT:
                handleExit();
                break;

            case THEME:
                themeManager.changeTheme(commandResult.getThemeName());
                break;

            case REPORT:
                handlePrintReport(commandResult.getCommandFlag());
                break;

            case EXPORT:
                handleExport();
                break;

            case COMMON:
                // do nothing
                break;

            default:
                assert false;
            }

            if (commandType != CommandType.REPORT && commandType != CommandType.EXPORT
                    && reportWindow.isShowing()) {
                // update report window
                reportWindow.updateInnerParts();
                reportWindow.changeTheme(themeManager.getThemeName());
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
