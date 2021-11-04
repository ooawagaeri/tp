package seedu.mycrm.model.product;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

/**
 * Represents a Product's description.
 * Guarantees: immutable.
 */
public class Description implements ProductComponent {
    private static final Description EMPTY_DESCRIPTION = new Description();
    private final String contents;

    private Description() {
        this.contents = null;
    }

    private Description(String contents) {
        this.contents = contents;
    }

    /** Returns a Description object with the specified {@code content}. */
    public static Description getDescription(String content) {
        requireNonNull(content);

        if (content.length() == 0) {
            return EMPTY_DESCRIPTION;
        } else {
            return new Description(content);
        }
    }

    /** Returns a Description object with content wrapped in {@code contentWrapper}. */
    public static Description getDescription(Optional<String> contentWrapper) {
        requireNonNull(contentWrapper);

        if (contentWrapper.orElse("").length() == 0) {
            return EMPTY_DESCRIPTION;
        } else {
            return new Description(contentWrapper.get());
        }
    }

    /** Returns an empty Description object. */
    public static Description getEmptyDescription() {
        return EMPTY_DESCRIPTION;
    }

    @Override
    public boolean isEmpty() {
        return this == EMPTY_DESCRIPTION;
    }

    @Override
    public String orElse(String alternativeString) {
        requireNonNull(alternativeString);

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
