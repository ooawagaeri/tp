package seedu.mycrm.model.product;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

/**
 * Represents a Product's name.
 * Guarantees: immutable.
 */
public class ProductName implements ProductComponent {
    private static final ProductName EMPTY_NAME = new ProductName();
    private String name;

    private ProductName() {
        this.name = null;
    }

    /**
     * Constructs a product name instance.
     * @param name Name of the product. It must be a non-empty string.
     */
    public ProductName(String name) {
        requireNonNull(name);
        assert name.length() > 0;

        this.name = name;
    }

    /**
     * Returns a ProductName object with the specified {@code name}.
     *
     * @param name Name of the product. It must be a non-empty string.
     */
    public static ProductName getName(String name) {
        return new ProductName(name);
    }

    /**
     * Returns a Description object with content wrapped in {@code contentWrapper}.
     *
     * @param nameWrapper Wrapper of the product name. It must contains a non-empty String.
     */
    public static ProductName getName(Optional<String> nameWrapper) {
        return new ProductName(nameWrapper.orElse(""));
    }

    /** Returns an empty ProductName object. */
    public static ProductName getEmptyName() {
        return EMPTY_NAME;
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY_NAME;
    }

    @Override
    public String orElse(String alternativeString) {
        requireNonNull(alternativeString);
        return this.isEmpty() ? alternativeString : this.name;
    }

    @Override
    public String toString() {
        return isEmpty() ? "" : this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o instanceof ProductName && o != EMPTY_NAME) {
            return ((ProductName) o).name.equals(this.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
}
