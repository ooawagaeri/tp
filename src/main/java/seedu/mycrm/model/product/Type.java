package seedu.mycrm.model.product;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

/**
 * Represents a Product's type.
 * Guarantees: immutable.
 */
public class Type implements ProductComponent {
    private static final Type EMPTY_TYPE = new Type();
    private final String name;

    private Type() {
        this.name = null;
    }

    private Type(String type) {
        this.name = type;
    }

    /** Returns a Type object with the specified {@code name} of the type. */
    public static Type getType(String name) {
        requireNonNull(name);

        if (name.length() == 0) {
            return EMPTY_TYPE;
        } else {
            return new Type(name);
        }
    }

    /** Returns a Type object with name of the type wrapped in {@code nameWrapper}. */
    public static Type getType(Optional<String> nameWrapper) {
        requireNonNull(nameWrapper);

        if (nameWrapper.orElse("").length() == 0) {
            return EMPTY_TYPE;
        } else {
            return new Type(nameWrapper.get());
        }
    }

    /** Returns an empty Type object. */
    public static Type getEmptyType() {
        return EMPTY_TYPE;
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY_TYPE;
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

        if (o instanceof Type && o != EMPTY_TYPE) {
            return ((Type) o).name.equals(this.name);
        }
        return false;
    }
}
