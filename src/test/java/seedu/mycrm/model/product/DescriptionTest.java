package seedu.mycrm.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_DESCRIPTION;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_DESCRIPTION;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    private Description description = DEFAULT_PRODUCT_ONE_DESCRIPTION;

    @Test
    public void getDescription_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Description.getDescription((String) null));
        assertThrows(NullPointerException.class, () -> Description.getDescription((Optional<String>) null));
    }

    @Test
    public void getDescription_emptyContent_returnsEmptyDescription() {
        assertTrue(Description.getDescription("").equals(Description.getEmptyDescription()));
        assertTrue(Description.getDescription(Optional.of("")).equals(Description.getEmptyDescription()));
        assertTrue(Description.getDescription(Optional.<String>empty()).equals(Description.getEmptyDescription()));
    }

    @Test
    public void orElse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> description.orElse(null));
        assertThrows(NullPointerException.class, () -> Description.getEmptyDescription().orElse(null));
    }

    @Test
    public void orElse_emptyDescriptionWithValidArgs_returnsAlternativeString() {
        assertTrue(Description.getEmptyDescription().orElse(DEFAULT_PRODUCT_TWO_DESCRIPTION.toString())
                .equals(DEFAULT_PRODUCT_TWO_DESCRIPTION.toString()));
    }

    @Test
    public void equals() {
        assertTrue(description.equals(description));

        assertTrue(description.equals(DEFAULT_PRODUCT_ONE_DESCRIPTION));

        assertFalse(description.equals(Description.getEmptyDescription()));

        assertFalse(description.equals(1));

        assertFalse(description.equals(null));

        assertFalse(description.equals(DEFAULT_PRODUCT_TWO_DESCRIPTION));
    }
}
