package seedu.address.model.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ProductBuilder;

public class TypeTest {
    @Test
    public void equals() {
        Type type = ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE;

        assertTrue(type.equals(type));

        Type typeCopy = ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE;
        assertTrue(type.equals(typeCopy));

        assertFalse(type.equals(Type.getEmptyType()));

        assertFalse(type.equals(1));

        assertFalse(type.equals(null));

        Type differentType = ProductBuilder.DEFAULT_PRODUCT_TWO_TYPE;
        assertFalse(type.equals(differentType));
    }
}
