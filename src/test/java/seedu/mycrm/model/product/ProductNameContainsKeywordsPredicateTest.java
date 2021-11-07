package seedu.mycrm.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.mycrm.testutil.ProductBuilder;

public class ProductNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> keywordList1 = Collections.singletonList("first");
        List<String> keywordList2 = Arrays.asList("first", "second");

        ProductNameContainsKeywordsPredicate predicate1 = new ProductNameContainsKeywordsPredicate(keywordList1);
        ProductNameContainsKeywordsPredicate predicate2 = new ProductNameContainsKeywordsPredicate(keywordList2);

        // same object -> returns true
        assertTrue(predicate1.equals(predicate1));

        // same values -> returns true
        ProductNameContainsKeywordsPredicate predicate1Copy = new ProductNameContainsKeywordsPredicate(keywordList1);
        assertTrue(predicate1.equals(predicate1Copy));

        // different types -> returns false
        assertFalse(predicate1.equals(1));

        // null -> returns false
        assertFalse(predicate1.equals(null));

        // different contact -> returns false
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ProductNameContainsKeywordsPredicate predicate = new ProductNameContainsKeywordsPredicate(
                Collections.singletonList("Asus"));
        assertTrue(predicate.test(new ProductBuilder().withName("Asus DUAL-GTX1060-O6G").build()));

        // Multiple keywords
        predicate = new ProductNameContainsKeywordsPredicate(Arrays.asList("Asus", "DUAL-GTX1060-O6G"));
        assertTrue(predicate.test(new ProductBuilder().withName("Asus DUAL-GTX1060-O6G").build()));

        // Only one matching keyword
        predicate = new ProductNameContainsKeywordsPredicate(Arrays.asList("Asus", "DUAL-GTX1060-O6G"));
        assertTrue(predicate.test(new ProductBuilder().withName("Asus i5-10400F").build()));

        // Mixed-case keywords
        predicate = new ProductNameContainsKeywordsPredicate(Arrays.asList("asUs", "DUaL-GtX1060-O6g"));
        assertTrue(predicate.test(new ProductBuilder().withName("Asus DUAL-GTX1060-O6G").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ProductNameContainsKeywordsPredicate predicate = new ProductNameContainsKeywordsPredicate(
                Collections.emptyList());
        assertFalse(predicate.test(new ProductBuilder().withName("Asus").build()));

        // Non-matching keyword
        predicate = new ProductNameContainsKeywordsPredicate(Arrays.asList("Intel"));
        assertFalse(predicate.test(new ProductBuilder().withName("Asus DUAL-GTX1060-O6G").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new ProductNameContainsKeywordsPredicate(Arrays.asList("CPU", "Intel", "2.90GHz"));
        assertFalse(predicate.test(new ProductBuilder().withName("i5-10400F").withType("CPU")
                .withManufacturer("Intel").withDescription("2.90GHz").build()));
    }
}
