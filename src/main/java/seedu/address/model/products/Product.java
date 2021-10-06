package seedu.address.model.products;

import static java.util.Objects.requireNonNull;

/**
 * Represents a product in MyCRM.
 */
public class Product {
    private final String productName;
    private final Type type;
    private final Manufacturer manufacturer;

    /**
     * Create a product.
     * @param productName Name of the product.
     */
    public Product(String productName, Type type, Manufacturer manufacturer) {
        requireNonNull(productName);

        this.productName = productName;
        this.type = type;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return this.productName;
    }

    public boolean hasType() {
        return !this.type.isEmpty();
    }

    public Type getType() {
        return this.type;
    }

    public boolean hasManufacturer() {
        return !this.manufacturer.isEmpty();
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (o instanceof Product) {
            Product p = (Product) o;
            return p.productName.equals(this.productName)
                    && p.type.equals(this.type)
                    && p.manufacturer.equals(this.manufacturer);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Product: " + this.productName
                + (this.type.isEmpty() ? "" : " Type: " + this.type)
                + (this.manufacturer.isEmpty() ? "" : " Manufacturer: " + this.manufacturer);
    }
}
