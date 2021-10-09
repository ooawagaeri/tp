package seedu.address.model.products;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

public class Description implements ProductComponent<Description> {
    private static final Description EMPTY_DESCRIPTION = new Description();
    private final String contents;

    private Description() {
        this.contents = null;
    }

    private Description(String contents) {
        this.contents = contents;
    }

    public static Description getDescription(String contents) {
        requireNonNull(contents);

        if (contents.length() == 0) {
            return EMPTY_DESCRIPTION;
        } else {
            return new Description(contents);
        }
    }

    public static Description getDescription(Optional<String> contents) {
        requireNonNull(contents);

        if (contents.orElse("").length() == 0) {
            return EMPTY_DESCRIPTION;
        } else {
            return new Description(contents.get());
        }
    }

    public static Description getEmptyDescription() {
        return EMPTY_DESCRIPTION;
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY_DESCRIPTION;
    }

    @Override
    public String orElse(String alternativeString) {
        return this.isEmpty() ? alternativeString : this.contents;
    }

    @Override
    public String toString() {
        return this.isEmpty() ? "" : this.contents;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (o instanceof Description && o != EMPTY_DESCRIPTION) {
            return ((Description) o).contents.equals(this.contents);
        }
        return false;
    }
}
