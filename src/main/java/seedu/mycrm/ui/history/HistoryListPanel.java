package seedu.mycrm.ui.history;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.model.history.History;
import seedu.mycrm.ui.UiPart;

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
     * Custom {@code ListCell} that displays the graphics of a {@code Histpru} using a {@code HistoryCard}.
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
