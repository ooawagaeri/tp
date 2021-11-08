package seedu.mycrm.model.product;

import static seedu.mycrm.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a product in MyCRM.
 * Guarantees: immutable, all fields are non-null
 */
public class Product {
    private final ProductName productName;
    private final Type type;
    private final Manufacturer manufacturer;
    private final Description description;

    /**
     * Creates a product with specified fields.
     *
     * @param productName Name of the product. It must not be empty.
     * @param type Type of the product.
     * @param manufacturer Manufacturer of the product.
     * @param description Description of the product.
     */
    public Product(ProductName productName, Type type, Manufacturer manufacturer, Description description) {
        requireAllNonNull(productName, type, manufacturer, description);
        assert !productName.isEmpty() : "Product name is empty.";

        this.productName = productName;
        this.type = type;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    public ProductName getName() {
        return this.productName;
    }

    /** Returns true if type field of the product is not empty. */
    public boolean hasType() {
        return !this.type.isEmpty();
    }

    public Type getType() {
        return this.type;
    }

    /** Returns true if manufacturer field of the product is not empty. */
    public boolean hasManufacturer() {
        return !this.manufacturer.isEmpty();
    }

    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }

    /** Returns true if description field of the product is not empty. */
    public boolean hasDescription() {
        return !this.description.isEmpty();
    }

    public Description getDescription() {
        return this.description;
    }

    /**
     * Returns true if both products have the same name.
     * This defines a weaker notion of equality between two products.
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

    @Override
    public int hashCode() {
        return Objects.hash(productName, type, manufacturer, description);
    }
}
