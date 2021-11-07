package seedu.mycrm.model.product;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

/**
 * Represents a Product's description.
 * Guarantees: immutable.
 */
public class Manufacturer implements ProductComponent {
    private static final Manufacturer EMPTY_MANUFACTURER = new Manufacturer();
    private final String name;

    private Manufacturer() {
        this.name = null;
    }

    private Manufacturer(String name) {
        this.name = name;
    }

    /** Returns a Manufacturer object with the specified {@code name}. */
    public static Manufacturer getManufacturer(String name) {
        requireNonNull(name);

        if (name.length() == 0) {
            return EMPTY_MANUFACTURER;
        } else {
            return new Manufacturer(name);
        }
    }

    /** Returns a Description object with content wrapped in {@code nameWrapper}. */
    public static Manufacturer getManufacturer(Optional<String> nameWrapper) {
        requireNonNull(nameWrapper);

        if (nameWrapper.orElse("").length() == 0) {
            return EMPTY_MANUFACTURER;
        } else {
            return new Manufacturer(nameWrapper.get());
        }
    }

    /** Returns an empty Manufacturer object. */
    public static Manufacturer getEmptyManufacturer() {
        return EMPTY_MANUFACTURER;
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY_MANUFACTURER;
    }

    @Override
    public String orElse(String alternativeString) {
        requireNonNull(alternativeString);
        return this.isEmpty() ? alternativeString : this.name;
    }

    @Override
    public String toString() {
        return this.isEmpty() ? "" : this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (o instanceof Manufacturer && o != EMPTY_MANUFACTURER) {
            return ((Manufacturer) o).name.equals(this.name);
        }
        return false;
    }
}
