package seedu.mycrm.model.products;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

public class ProductName implements ProductComponent<ProductName> {
    private static final ProductName EMPTY_NAME = new ProductName();

    private String name;

    private ProductName() {
        this.name = null;
    }

    public ProductName(String name) {
        requireNonNull(name);
        assert name.length() > 0;

        this.name = name;
    }

    public static ProductName getEmptyName() {
        return EMPTY_NAME;
    }

    public static ProductName getName(String name) {
        return new ProductName(name);
    }

    public static ProductName getName(Optional<String> name) {
        return new ProductName(name.orElse(""));
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY_NAME;
    }

    @Override
    public String orElse(String alternativeString) {
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
}
