package seedu.address.model.products;

import static java.util.Objects.requireNonNull;

public class Type {
    private static final Type EMPTY_TYPE = new Type();
    private final String name;

    private Type() {
        this.name = null;
    }

    private Type(String type) {
        this.name = type;
    }

    public static Type getType(String name) {
        requireNonNull(name);

        if (name.length() == 0) {
            return EMPTY_TYPE;
        } else {
            return new Type(name);
        }
    }

    public static Type getEmptyType() {
        return EMPTY_TYPE;
    }

    protected boolean isEmpty() {
        return this == EMPTY_TYPE;
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
