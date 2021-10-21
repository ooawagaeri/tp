package seedu.mycrm.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import seedu.mycrm.logic.Logic;
import seedu.mycrm.logic.commands.CommandType;
import seedu.mycrm.ui.contact.ContactListPanel;
import seedu.mycrm.ui.history.HistoryListPanel;
import seedu.mycrm.ui.product.ProductListPanel;
import seedu.mycrm.ui.template.TemplateListPanel;

/**
 * After creating an instance of SideDisplay, should instantly call method fillInnerParts(Logic).
 */
public class SideDisplay extends UiPart<Region> {
    private ContactListPanel contactListPanel;
    private TemplateListPanel templateListPanel;
    private ProductListPanel productListPanel;
    private HistoryListPanel historyListPanel;

    @FXML
    private TabPane sideDisplayPane;

    @FXML
    private Tab contactTab;

    @FXML
    private Tab productTab;

    @FXML
    private Tab templateTab;

    @FXML
    private Tab historyTab;

    @FXML
    private StackPane contactListPanelPlaceholder;

    @FXML
    private StackPane templateListPanelPlaceholder;

    @FXML
    private StackPane productListPanelPlaceholder;

    @FXML
    private StackPane historyListPanelPlaceholder;

    private static final String FXML = "SideDisplay.fxml";

    public SideDisplay() {
        super(FXML);
    }

    public void init(Logic logic) {
        contactListPanel = new ContactListPanel(logic.getFilteredContactList());
        contactListPanelPlaceholder.managedProperty().bind(contactListPanelPlaceholder.visibleProperty());
        contactListPanelPlaceholder.getChildren().add(contactListPanel.getRoot());

        productListPanel = new ProductListPanel(logic.getFilteredProductList());
        productListPanelPlaceholder.managedProperty().bind(productListPanelPlaceholder.visibleProperty());
        productListPanelPlaceholder.getChildren().add(productListPanel.getRoot());

        templateListPanel = new TemplateListPanel(logic.getFilteredTemplateList());
        templateListPanelPlaceholder.managedProperty().bind(templateListPanelPlaceholder.visibleProperty());
        templateListPanelPlaceholder.getChildren().add(templateListPanel.getRoot());

        historyListPanel = new HistoryListPanel(logic.getFilteredHistoryList());
        historyListPanelPlaceholder.managedProperty().bind(historyListPanelPlaceholder.visibleProperty());
        historyListPanelPlaceholder.getChildren().add(historyListPanel.getRoot());
    }

    public void switchTab(CommandType type) {
        switch (type) {
        case CONTACTS:
            switchTab(contactTab);
            break;

        case PRODUCTS:
            switchTab(productTab);
            break;

        case TEMPLATE:
            switchTab(templateTab);
            break;

        case HISTORY:
            switchTab(historyTab);
            break;

        default:
            assert false;
        }
    }

    private void switchTab(Tab tab) {
        requireNonNull(tab);

        sideDisplayPane.getSelectionModel().select(tab);
    }
}
