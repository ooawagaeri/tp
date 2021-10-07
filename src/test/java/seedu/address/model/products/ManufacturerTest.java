package seedu.address.model.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ProductBuilder;

public class ManufacturerTest {
    @Test
    public void equals() {
        Manufacturer manufacturer = ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER;

        assertTrue(manufacturer.equals(manufacturer));

        Manufacturer manufacturerCopy = ProductBuilder.DEFAULT_PRODUCT_ONE_MANUFACTURER;
        assertTrue(manufacturer.equals(manufacturerCopy));

        assertFalse(manufacturer.equals(Manufacturer.getEmptyManufacturer()));

        assertFalse(manufacturer.equals(1));

        assertFalse(manufacturer.equals(null));

        Manufacturer diffManufacturer = ProductBuilder.DEFAULT_PRODUCT_TWO_MANUFACTURER;
        assertFalse(manufacturer.equals(diffManufacturer));
    }
}
