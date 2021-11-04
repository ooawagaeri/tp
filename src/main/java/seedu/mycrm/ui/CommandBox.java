package seedu.mycrm.ui;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.logic.commands.CommandResult;
import seedu.mycrm.logic.commands.exceptions.CommandException;
import seedu.mycrm.logic.parser.exceptions.ParseException;
import seedu.mycrm.model.history.History;


/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private Logic logic;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(Logic logic, CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.logic = logic;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        retrieveHistoryCommand();
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        logic.traceUserInput(new History(commandText));
        //logic.getFilteredHistoryList().add(new History(commandText));
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Retrieves the recent entered command using arrow keys.
     */
    private void retrieveHistoryCommand() {
        commandTextField.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
                    private Integer currentSize = logic.getFilteredHistoryList().size();
                    private Integer recentCommandIndex = currentSize;

                    @Override
                    public void handle(KeyEvent event) {
                        ObservableList<History> histories = logic.getFilteredHistoryList();
                        if (histories.size() != currentSize) {
                            currentSize = histories.size();
                            recentCommandIndex = currentSize;
                        }
                        if (event.getCode() == KeyCode.UP) {
                            if (recentCommandIndex > 0) {
                                recentCommandIndex--;
                            } else {
                                recentCommandIndex = 0;
                            }
                            if (currentSize != 0) {
                                commandTextField.setText(histories.get(recentCommandIndex).toString());
                            }
                        } else if (event.getCode() == KeyCode.DOWN) {
                            if (recentCommandIndex < histories.size() - 1) {
                                recentCommandIndex++;
                            } else {
                                recentCommandIndex = histories.size() - 1;
                            }
                            if (currentSize != 0) {
                                commandTextField.setText(histories.get(recentCommandIndex).toString());
                            }
                        }
                        // Keep the caret at the end of TextField when tracing user input
                        if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
                            commandTextField.positionCaret(commandTextField.getLength());
                        }
                    }
                }
        );


    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.mycrm.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
