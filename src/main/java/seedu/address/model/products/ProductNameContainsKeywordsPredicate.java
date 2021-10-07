package seedu.address.model.products;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class ProductNameContainsKeywordsPredicate implements Predicate<Product> {
    private final List<String> keywords;

    public ProductNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Product product) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(product.getName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ProductNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ProductNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
