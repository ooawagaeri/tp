package seedu.mycrm.ui.history;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.mycrm.model.history.History;
import seedu.mycrm.ui.UiPart;
import seedu.mycrm.ui.contact.ContactCard;

public class HistoryCard extends UiPart<Region> {

    private static final String FXML = "HistoryListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private History history;

    @FXML
    private HBox cardPane;

    @FXML
    private Label command;

    @FXML
    private Label id;

    /**
     * Creates a {@code HistoryCode} with the given {@code History} and index to display.
     */
    public HistoryCard(History history, int displayedIndex) {
        super(FXML);
        this.history = history;
        id.setText(displayedIndex + ". ");
        command.setText(history.toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactCard)) {
            return false;
        }

        // state check
        HistoryCard card = (HistoryCard) other;
        return id.getText().equals(card.id.getText())
                && history.equals(card.history);
    }
}
