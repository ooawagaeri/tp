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
 * The UI component that is responsible for displaying lists of contact, product, template, and history. <br>
 * After creating an instance of SideDisplay, method init(Logic) should be invoked to initialize its inner parts.
 */
public class SideDisplay extends UiPart<Region> {
    private static final String FXML = "SideDisplay.fxml";

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

    public SideDisplay() {
        super(FXML);
    }

    /**
     * Initializes inner parts.
     */
    public void init(Logic logic) {
        ContactListPanel contactListPanel = new ContactListPanel(logic.getFilteredContactList());
        contactListPanelPlaceholder.managedProperty().bind(contactListPanelPlaceholder.visibleProperty());
        contactListPanelPlaceholder.getChildren().add(contactListPanel.getRoot());

        ProductListPanel productListPanel = new ProductListPanel(logic.getFilteredProductList());
        productListPanelPlaceholder.managedProperty().bind(productListPanelPlaceholder.visibleProperty());
        productListPanelPlaceholder.getChildren().add(productListPanel.getRoot());

        TemplateListPanel templateListPanel = new TemplateListPanel(logic.getFilteredTemplateList());
        templateListPanelPlaceholder.managedProperty().bind(templateListPanelPlaceholder.visibleProperty());
        templateListPanelPlaceholder.getChildren().add(templateListPanel.getRoot());

        HistoryListPanel historyListPanel = new HistoryListPanel(logic.getFilteredHistoryList());
        historyListPanelPlaceholder.managedProperty().bind(historyListPanelPlaceholder.visibleProperty());
        historyListPanelPlaceholder.getChildren().add(historyListPanel.getRoot());
    }

    /**
     * Switches tab based on command type.
     */
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
