package seedu.mycrm.model.product;

import java.util.List;
import java.util.function.Predicate;

import seedu.mycrm.commons.util.StringUtil;

/**
 * Tests whether a {@code Product}'s {@code ProductName} matches any of the keywords.
 */
public class ProductNameContainsKeywordsPredicate implements Predicate<Product> {
    private final List<String> keywords;

    /** Creates a ProductNameContainsKeywordsPredicate with the specified {@code keywords} */
    public ProductNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Product product) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(product.getName().orElse(""), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProductNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ProductNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
