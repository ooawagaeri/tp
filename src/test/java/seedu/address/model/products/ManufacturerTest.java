package seedu.address.model.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ManufacturerTest {
    @Test
    public void equals() {
        Manufacturer manufacturer = Manufacturer.getManufacturer("GPU");

        assertTrue(manufacturer.equals(manufacturer));

        Manufacturer manufacturerCopy = Manufacturer.getManufacturer("GPU");
        assertTrue(manufacturer.equals(manufacturerCopy));

        assertFalse(manufacturer.equals(Manufacturer.getEmptyManufacturer()));

        assertFalse(manufacturer.equals(1));

        assertFalse(manufacturer.equals(null));

        Manufacturer diffManufacturer = Manufacturer.getManufacturer("CPU");
        assertFalse(manufacturer.equals(diffManufacturer));
    }
}
