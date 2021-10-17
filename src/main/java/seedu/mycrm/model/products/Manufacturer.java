package seedu.mycrm.model.products;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

public class Manufacturer implements ProductComponent<Manufacturer> {
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

    public static Manufacturer getManufacturer(Optional<String> name) {
        requireNonNull(name);

        if (name.orElse("").length() == 0) {
            return EMPTY_MANUFACTURER;
        } else {
            return new Manufacturer(name.get());
        }
    }

    public static Manufacturer getEmptyManufacturer() {
        return EMPTY_MANUFACTURER;
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY_MANUFACTURER;
    }

    @Override
    public String orElse(String alternativeString) {
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
