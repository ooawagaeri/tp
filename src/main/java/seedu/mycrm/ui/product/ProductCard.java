package seedu.mycrm.ui.product;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.ui.UiPart;

/**
 * An UI component that displays information of a {@code Product}.
 */
public class ProductCard extends UiPart<Region> {

    private static final String FXML = "ProductListCard.fxml";

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
     * Creates a {@code ProductCard} object with the given {@code Product} and index to display.
     */
    public ProductCard(Product product, int displayedIndex) {
        super(FXML);
        this.product = product;

        id.setText(displayedIndex + ". ");
        name.setText(product.getName().toString());
        type.setText("Type: " + product.getType());
        manufacturer.setText("Manufacturer: " + product.getManufacturer());
        description.setText("Description: " + product.getDescription());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ProductCard)) {
            return false;
        }

        ProductCard card = (ProductCard) other;
        return id.getText().equals(card.id.getText())
                && product.equals(card.product);
    }
}
