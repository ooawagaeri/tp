package seedu.mycrm.ui.template;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.mycrm.commons.core.LogsCenter;
import seedu.mycrm.model.mail.Template;
import seedu.mycrm.ui.UiPart;

/**
 * Panel containing the list of templates.
 */
public class TemplateListPanel extends UiPart<Region> {
    private static final String FXML = "TemplateListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TemplateListPanel.class);

    @FXML
    private ListView<Template> templateListView;

    /**
     * Creates a {@code TemplateListPanel} with the given {@code ObservableList}.
     */
    public TemplateListPanel(ObservableList<Template> templateList) {
        super(FXML);
        templateListView.setItems(templateList);
        templateListView.setCellFactory(listView -> new TemplateListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Template} using a {@code TemplateCard}.
     */
    class TemplateListViewCell extends ListCell<Template> {
        @Override
        protected void updateItem(Template template, boolean empty) {
            super.updateItem(template, empty);

            if (empty || template == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TemplateCard(template, getIndex() + 1).getRoot());
            }
        }
    }

}
