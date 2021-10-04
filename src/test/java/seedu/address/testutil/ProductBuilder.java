package seedu.address.testutil;

import seedu.address.model.products.Product;

public class ProductBuilder {

    public static final String DEFAULT_PRODUCT_ONE_NAME = "Intel i5-10400F";

    public static final String DEFAULT_PRODUCT_TWO_NAME = "Asus DUAL-GTX1060-O6G";

    private Product toBuild;

    /**
     * Create a product builder with index of default product.
     */
    public ProductBuilder(DefaultProduct index) {
        switch (index) {
        case ONE:
            this.toBuild = new Product(DEFAULT_PRODUCT_ONE_NAME);
            break;
        case TWO:
            this.toBuild = new Product(DEFAULT_PRODUCT_TWO_NAME);
            break;
        default:
            assert false : "Enum not implemented";
        }
    }

    public ProductBuilder() {
        this(DefaultProduct.ONE);
    }

    public Product build() {
        return toBuild;
    }

    public enum DefaultProduct { ONE, TWO };
}
