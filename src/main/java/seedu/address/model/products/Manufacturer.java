package seedu.address.model.products;

import static java.util.Objects.requireNonNull;

public class Manufacturer {
    private static final Manufacturer EMPTY_MANUFACTURER = new Manufacturer();
    private final String name;

    private Manufacturer() {
        this.name = null;
    }

    private Manufacturer(String name) {
        this.name = name;
    }

    public static Manufacturer getManufacturer(String name) {
        requireNonNull(name);

        if (name.length() == 0) {
            return EMPTY_MANUFACTURER;
        } else {
            return new Manufacturer(name);
        }
    }

    public static Manufacturer getEmptyManufacturer() {
        return EMPTY_MANUFACTURER;
    }

    protected boolean isEmpty() {
        return this == EMPTY_MANUFACTURER;
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
