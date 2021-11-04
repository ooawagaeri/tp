package seedu.mycrm.model.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER;
import static seedu.mycrm.testutil.ProductBuilder.DEFAULT_PRODUCT_TWO_MANUFACTURER;

import java.util.Optional;

import org.junit.jupiter.api.Test;


public class ManufacturerTest {
    private Manufacturer manufacturer = DEFAULT_PRODUCT_ONE_MANUFACTURER;

    @Test
    public void getManufacturer_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Manufacturer.getManufacturer((String) null));
        assertThrows(NullPointerException.class, () -> Manufacturer.getManufacturer((Optional<String>) null));
    }

    @Test
    public void getManufacturer_emptyContent_returnsEmptyManufacturer() {
        assertTrue(Manufacturer.getManufacturer("").equals(Manufacturer.getEmptyManufacturer()));
        assertTrue(Manufacturer.getManufacturer(Optional.of("")).equals(Manufacturer.getEmptyManufacturer()));
        assertTrue(Manufacturer.getManufacturer(Optional.<String>empty()).equals(Manufacturer.getEmptyManufacturer()));
    }

    @Test
    public void orElse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> manufacturer.orElse(null));
        assertThrows(NullPointerException.class, () -> Manufacturer.getEmptyManufacturer().orElse(null));
    }

    @Test
    public void orElse_emptyManufacturerWithValidArgs_returnsAlternativeString() {
        assertTrue(Manufacturer.getEmptyManufacturer().orElse(DEFAULT_PRODUCT_TWO_MANUFACTURER.toString())
                .equals(DEFAULT_PRODUCT_TWO_MANUFACTURER.toString()));
    }

    @Test
    public void equals() {
        assertTrue(manufacturer.equals(manufacturer));

        assertTrue(manufacturer.equals(DEFAULT_PRODUCT_ONE_MANUFACTURER));

        assertFalse(manufacturer.equals(Manufacturer.getEmptyManufacturer()));

        assertFalse(manufacturer.equals(1));

        assertFalse(manufacturer.equals(null));

        assertFalse(manufacturer.equals(DEFAULT_PRODUCT_TWO_MANUFACTURER));
    }
}
