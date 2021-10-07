package seedu.address.model.products;

import static java.util.Objects.requireNonNull;

/**
 * Represents a product in MyCRM.
 */
public class Product {
    private final String productName;
    private final Type type;
    private final Manufacturer manufacturer;
    private final Description description;

    /**
     * Create a product.
     * @param productName Name of the product.
     */
    public Product(String productName, Type type, Manufacturer manufacturer, Description description) {
        requireNonNull(productName);

        this.productName = productName;
        this.type = type;
        this.manufacturer = manufacturer;
        this.description = description;
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

    public boolean hasDescription() {
        return !this.description.isEmpty();
    }

    public Description getDescription() {
        return this.description;
    }

    /**
     * Returns true if both products have the same name.
     * This defines a weaker notion of equality between two contacts.
     */
    public boolean isSameProduct(Product otherProduct) {
        if (otherProduct == this) {
            return true;
        }

        return otherProduct != null
                && otherProduct.productName.equals(this.productName);
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
                    && p.manufacturer.equals(this.manufacturer)
                    && p.description.equals(this.description);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Product: " + this.productName
                + (this.type.isEmpty() ? "" : " Type: " + this.type)
                + (this.manufacturer.isEmpty() ? "" : " Manufacturer: " + this.manufacturer)
                + (this.description.isEmpty() ? "" : " Description: " + this.description);
    }
}
