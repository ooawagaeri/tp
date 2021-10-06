package seedu.address.model.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TypeTest {
    @Test
    public void equals() {
        Type type = Type.getType("GPU");

        assertTrue(type.equals(type));

        Type typeCopy = Type.getType("GPU");
        assertTrue(type.equals(typeCopy));

        assertFalse(type.equals(Type.getEmptyType()));

        assertFalse(type.equals(1));

        assertFalse(type.equals(null));

        Type differentType = Type.getType("CPU");
        assertFalse(type.equals(differentType));
    }
}
