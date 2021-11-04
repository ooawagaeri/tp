package seedu.mycrm.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.mycrm.testutil.ProductBuilder;

public class ProductNameTest {
    @Test
    public void equals() {
        ProductName productName = ProductBuilder.DEFAULT_PRODUCT_ONE_NAME;

        assertTrue(productName.equals(productName));

        ProductName productNameCopy = ProductBuilder.DEFAULT_PRODUCT_ONE_NAME;
        assertTrue(productName.equals(productNameCopy));

        assertFalse(productName.equals(Description.getEmptyDescription()));

        assertFalse(productName.equals(1));

        assertFalse(productName.equals(null));

        ProductName diffProductName = ProductBuilder.DEFAULT_PRODUCT_TWO_NAME;
        assertFalse(productName.equals(diffProductName));
    }
}
