package seedu.address.testutil;

import seedu.address.model.products.Product;
import seedu.address.model.products.Type;

public class ProductBuilder {

    public static final String DEFAULT_PRODUCT_ONE_NAME = "Intel i5-10400F";
    public static final Type DEFAULT_PRODUCT_ONE_TYPE = Type.getType("CPU");

    public static final String DEFAULT_PRODUCT_TWO_NAME = "Asus DUAL-GTX1060-O6G";
    public static final Type DEFAULT_PRODUCT_TWO_TYPE = Type.getType("GPU");

    private Product toBuild;

    /**
     * Create a product builder with index of default product.
     */
    public ProductBuilder(DefaultProductIndex index) {
        switch (index) {
        case ONE:
            this.toBuild = new Product(DEFAULT_PRODUCT_ONE_NAME, DEFAULT_PRODUCT_ONE_TYPE);
            break;
        case TWO:
            this.toBuild = new Product(DEFAULT_PRODUCT_TWO_NAME, DEFAULT_PRODUCT_TWO_TYPE);
            break;
        default:
            assert false : "Enum not implemented";
        }
    }

    public ProductBuilder() {
        this(DefaultProductIndex.ONE);
    }

    public Product build() {
        return toBuild;
    }

    public enum DefaultProductIndex { ONE, TWO };
}
