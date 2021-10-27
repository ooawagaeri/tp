package seedu.mycrm.ui.product;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.mycrm.model.products.Product;
import seedu.mycrm.ui.UiPart;

/**
 * An UI component that displays information of a {@code Product}.
 */
public class ProductCard extends UiPart<Region> {

    private static final String FXML = "ProductListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Product product;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label type;
    @FXML
    private Label manufacturer;
    @FXML
    private Label description;

    /**
     * Creates a {@code ContactCode} with the given {@code Contact} and index to display.
     */
    public ProductCard(Product product, int displayedIndex) {
        super(FXML);
        this.product = product;
        id.setText(displayedIndex + ". ");
        name.setText(product.getName().orElse(""));
        type.setText("Type: " + product.getType().toString());
        manufacturer.setText("Manufacturer: " + product.getManufacturer().toString());
        description.setText("Description: " + product.getDescription().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProductCard)) {
            return false;
        }

        // state check
        ProductCard card = (ProductCard) other;
        return id.getText().equals(card.id.getText())
                && product.equals(card.product);
    }
}
