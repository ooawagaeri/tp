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

    public static final String DEFAULT_PRODUCT_THREE_NAME = "SAMSUNG 980 PRO 1TB SSD";
    public static final Type DEFAULT_PRODUCT_THREE_TYPE = Type.getType("Hard disk");
    public static final Manufacturer DEFAULT_PRODUCT_THREE_MANUFACTURER = Manufacturer.getManufacturer("SAMSUNG");
    public static final Description DEFAULT_PRODUCT_THREE_DESCRIPTION = Description.getDescription(
            "Connectivity technology: SATA");


    private String name;
    private Type type;
    private Manufacturer manufacturer;
    private Description description;

    /**
     * Create a product builder with index of default product.
     */
    public ProductBuilder(DefaultProductIndex index) {
        switch (index) {
        case ONE:
            this.name = DEFAULT_PRODUCT_ONE_NAME;
            this.type = DEFAULT_PRODUCT_ONE_TYPE;
            this.manufacturer = DEFAULT_PRODUCT_ONE_MANUFACTURER;
            this.description = DEFAULT_PRODUCT_ONE_DESCRIPTION;
            break;
        case TWO:
            this.name = DEFAULT_PRODUCT_TWO_NAME;
            this.type = DEFAULT_PRODUCT_TWO_TYPE;
            this.manufacturer = DEFAULT_PRODUCT_TWO_MANUFACTURER;
            this.description = DEFAULT_PRODUCT_TWO_DESCRIPTION;
            break;
        case THREE:
            this.name = DEFAULT_PRODUCT_THREE_NAME;
            this.type = DEFAULT_PRODUCT_THREE_TYPE;
            this.manufacturer = DEFAULT_PRODUCT_THREE_MANUFACTURER;
            this.description = DEFAULT_PRODUCT_THREE_DESCRIPTION;
            break;
        default:
            assert false : "Enum not implemented";
        }
    }

    /**
     * Creates a product builder with provided product.
     */
    public ProductBuilder(Product product) {
        this.name = product.getName();
        this.type = product.getType();
        this.manufacturer = product.getManufacturer();
        this.description = product.getDescription();
    }

    public ProductBuilder() {
        this(DefaultProductIndex.ONE);
    }

    /**
     * Sets name field to given name.
     */
    public ProductBuilder withName(String productName) {
        this.name = productName;
        return this;
    }

    /**
     * Initializes type field with given string.
     */
    public ProductBuilder withType(String type) {
        this.type = Type.getType(type);
        return this;
    }

    /**
     * Sets type field to given type.
     */
    public ProductBuilder withType(Type type) {
        this.type = type;
        return this;
    }

    /**
     * Initializes manufacturer field with given manufacturer.
     */
    public ProductBuilder withManufacturer(String manufacturer) {
        this.manufacturer = Manufacturer.getManufacturer(manufacturer);
        return this;
    }

    /**
     * Sets manufacturer field to given manufacturer.
     */
    public ProductBuilder withManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    /**
     * Initializes description field with given description.
     */
    public ProductBuilder withDescription(String description) {
        this.description = Description.getDescription(description);
        return this;
    }

    /**
     * Sets description field to given description.
     */
    public ProductBuilder withDescription(Description description) {
        this.description = description;
        return this;
    }

    public Product build() {
        return new Product(name, type, manufacturer, description);
    }

    public enum DefaultProductIndex { ONE, TWO, THREE };
}
