package seedu.mycrm.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_NAME;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_NAME;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class ProductNameTest {
    private final ProductName productName = DEFAULT_PRODUCT_ONE_NAME;

    @Test
    public void getName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ProductName.getName((String) null));
        assertThrows(NullPointerException.class, () -> ProductName.getName((Optional<String>) null));
    }

    @Test
    public void getName_emptyContent_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> ProductName.getName(""));
        assertThrows(AssertionError.class, () -> ProductName.getName(Optional.of("")));
        assertThrows(AssertionError.class, () -> ProductName.getName(Optional.<String>empty()));
    }

    @Test
    public void orElse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> productName.orElse(null));
        assertThrows(NullPointerException.class, () -> ProductName.getEmptyName().orElse(null));
    }

    @Test
    public void orElse_emptyProductNameWithValidArgs_returnsAlternativeString() {
        assertTrue(ProductName.getEmptyName().orElse(DEFAULT_PRODUCT_TWO_NAME.toString())
                .equals(DEFAULT_PRODUCT_TWO_NAME.toString()));
    }

    @Test
    public void equals() {
        assertTrue(productName.equals(productName));

        assertTrue(productName.equals(DEFAULT_PRODUCT_ONE_NAME));

        assertFalse(productName.equals(Description.getEmptyDescription()));

        assertFalse(productName.equals(1));

        assertFalse(productName.equals(null));

        assertFalse(productName.equals(DEFAULT_PRODUCT_TWO_NAME));
    }
}
