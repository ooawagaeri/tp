package seedu.mycrm.model.product;

/**
 * Wrapper of product fields.
 */
public interface ProductComponent {
    /** Returns true is the field is empty. */
    boolean isEmpty();

    /** If the field is present, returns the field, otherwise returns {@code alternativeString}. */
    String orElse(String alternativeString);

    /** If the field is present, returns the field, otherwise returns an empty {@code String}. */
    @Override
    String toString();
}
