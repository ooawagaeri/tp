package seedu.mycrm.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_TYPE;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_TYPE;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class TypeTest {
    private Type type = DEFAULT_PRODUCT_ONE_TYPE;

    @Test
    public void getType_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Type.getType((String) null));
        assertThrows(NullPointerException.class, () -> Type.getType((Optional<String>) null));
    }

    @Test
    public void getType_emptyContent_returnsEmptyType() {
        assertTrue(Type.getType("").equals(Type.getEmptyType()));
        assertTrue(Type.getType(Optional.of("")).equals(Type.getEmptyType()));
        assertTrue(Type.getType(Optional.<String>empty()).equals(Type.getEmptyType()));
    }

    @Test
    public void orElse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> type.orElse(null));
        assertThrows(NullPointerException.class, () -> Type.getEmptyType().orElse(null));
    }

    @Test
    public void orElse_emptyTypeWithValidArgs_returnsAlternativeString() {
        assertTrue(Type.getEmptyType().orElse(DEFAULT_PRODUCT_TWO_TYPE.toString())
                .equals(DEFAULT_PRODUCT_TWO_TYPE.toString()));
    }

    @Test
    public void equals() {
        assertTrue(type.equals(type));

        assertTrue(type.equals(DEFAULT_PRODUCT_ONE_TYPE));

        assertFalse(type.equals(Type.getEmptyType()));

        assertFalse(type.equals(1));

        assertFalse(type.equals(null));

        assertFalse(type.equals(DEFAULT_PRODUCT_TWO_TYPE));
    }
}
