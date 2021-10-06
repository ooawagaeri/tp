package seedu.address.testutil;

import seedu.address.model.products.Description;
import seedu.address.model.products.Manufacturer;
import seedu.address.model.products.Product;
import seedu.address.model.products.Type;

public class ProductBuilder {

    public static final String DEFAULT_PRODUCT_ONE_NAME = "Intel i5-10400F";
    public static final Type DEFAULT_PRODUCT_ONE_TYPE = Type.getType("CPU");
    public static final Manufacturer DEFAULT_PRODUCT_ONE_MANUFACTURER = Manufacturer.getManufacturer("Intel");
    public static final Description DEFAULT_PRODUCT_ONE_DESCRIPTION = Description.getDescription("2.90GHz");

    public static final String DEFAULT_PRODUCT_TWO_NAME = "Asus DUAL-GTX1060-O6G";
    public static final Type DEFAULT_PRODUCT_TWO_TYPE = Type.getType("GPU");
    public static final Manufacturer DEFAULT_PRODUCT_TWO_MANUFACTURER = Manufacturer.getManufacturer("Asus");
    public static final Description DEFAULT_PRODUCT_TWO_DESCRIPTION = Description.getDescription("Video output "
            + "interface: DisplayPort, HDMI");

    private Product toBuild;

    /**
     * Create a product builder with index of default product.
     */
    public ProductBuilder(DefaultProductIndex index) {
        switch (index) {
        case ONE:
            this.toBuild = new Product(DEFAULT_PRODUCT_ONE_NAME, DEFAULT_PRODUCT_ONE_TYPE,
                    DEFAULT_PRODUCT_ONE_MANUFACTURER, DEFAULT_PRODUCT_ONE_DESCRIPTION);
            break;
        case TWO:
            this.toBuild = new Product(DEFAULT_PRODUCT_TWO_NAME, DEFAULT_PRODUCT_TWO_TYPE,
                    DEFAULT_PRODUCT_TWO_MANUFACTURER, DEFAULT_PRODUCT_TWO_DESCRIPTION);
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
