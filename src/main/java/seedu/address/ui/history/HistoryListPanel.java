package seedu.address.ui.history;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.history.History;
import seedu.address.ui.UiPart;

import javafx.scene.layout.Region;
import java.util.logging.Logger;

public class HistoryListPanel extends UiPart<Region> {
    private static final String FXML = "HistoryListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(HistoryListPanel.class);

    @FXML
    private ListView<History> historyListView;

    /**
     * Creates a {@code HistoryListPanel} with the given {@code ObservableList}.
     */
    public HistoryListPanel(ObservableList<History> historyList) {
        super(FXML);
        historyListView.setItems(historyList);
        historyListView.setCellFactory(listView -> new HistoryListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class HistoryListViewCell extends ListCell<History> {
        @Override
        protected void updateItem(History history, boolean empty) {
            super.updateItem(history, empty);

            if (empty || history == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new HistoryCard(history, getIndex() + 1).getRoot());
            }
        }
    }

}
