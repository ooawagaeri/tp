package seedu.address.model.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ProductBuilder;

public class DescriptionTest {
    @Test
    public void equals() {
        Description description = ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION;

        assertTrue(description.equals(description));

        Description descriptionCopy = ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION;
        assertTrue(description.equals(descriptionCopy));

        assertFalse(description.equals(Description.getEmptyDescription()));

        assertFalse(description.equals(1));

        assertFalse(description.equals(null));

        Description differentDescription = ProductBuilder.DEFAULT_PRODUCT_TWO_DESCRIPTION;
        assertFalse(description.equals(differentDescription));
    }
}
