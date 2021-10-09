package seedu.address.model.products;

/**
 * Product components are essentially boxes of strings.
 */
public interface ProductComponent<T> {
    boolean isEmpty();

    String orElse(String alternativeString);
}
