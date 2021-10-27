package seedu.mycrm.ui.report;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.mycrm.ui.UiPart;

public class NumberCard extends UiPart<Region> {
    private static final String FXML = "NumberCard.fxml";

    @FXML
    private HBox cardPane;

    @FXML
    private Label numberMessage;

    /**
     * Creates a {@code NumberCard} with the given {@code message} and {@code number} to display.
     */
    public NumberCard(String message, int number) {
        super(FXML);
        numberMessage.setText(message + number);
    }
}
