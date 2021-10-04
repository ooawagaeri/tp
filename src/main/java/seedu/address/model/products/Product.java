package seedu.address.model.products;

import static java.util.Objects.requireNonNull;

/**
 * Represents a product in MyCRM.
 */
public class Product {
    private final String productName;

    /**
     * Create a product.
     * @param productName Name of the product.
     */
    public Product(String productName) {
        requireNonNull(productName);

        this.productName = productName;
    }

    public String getName() {
        return this.productName;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Product) {
            Product p = (Product) o;
            return p.productName.equals(this.productName);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Product: " + productName;
    }
}
