package seedu.mycrm.logic.commands.products;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_PRODUCT_DESCRIPTION;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_PRODUCT_MANUFACTURER;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_PRODUCT_NAME;
import static seedu.mycrm.logic.commands.CommandTestUtil.VALID_PRODUCT_TYPE;
import static seedu.mycrm.testutil.TypicalProducts.ASUS_GPU;
import static seedu.mycrm.testutil.TypicalProducts.INTEL_CPU;

import org.junit.jupiter.api.Test;

import seedu.mycrm.logic.commands.products.EditProductCommand.EditProductDescriptor;
import seedu.mycrm.testutil.EditProductDescriptorBuilder;

public class EditProductDescriptorTest {

    @Test
    public void equals() {
        EditProductDescriptor standardDescriptor = new EditProductDescriptorBuilder(INTEL_CPU).build();

        // same values -> returns true
        EditProductDescriptor descriptorWithSameValues = new EditProductDescriptorBuilder(INTEL_CPU).build();
        assertTrue(standardDescriptor.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(standardDescriptor.equals(standardDescriptor));

        // null -> returns false
        assertFalse(standardDescriptor.equals(null));

        // different types -> returns false
        assertFalse(standardDescriptor.equals(5));

        // different values -> returns false
        assertFalse(standardDescriptor.equals(new EditProductDescriptorBuilder(ASUS_GPU).build()));

        // different product name -> returns false
        EditProductDescriptor editedDescriptor = new EditProductDescriptorBuilder(standardDescriptor)
                .withProductName(VALID_PRODUCT_NAME).build();
        assertFalse(standardDescriptor.equals(editedDescriptor));

        // different type -> returns false
        editedDescriptor = new EditProductDescriptorBuilder(standardDescriptor)
                .withManufacturer(VALID_PRODUCT_MANUFACTURER).build();
        assertFalse(standardDescriptor.equals(editedDescriptor));

        // different manufacturer -> returns false
        editedDescriptor = new EditProductDescriptorBuilder(standardDescriptor).withType(VALID_PRODUCT_TYPE).build();
        assertFalse(standardDescriptor.equals(editedDescriptor));

        // different description -> returns false
        editedDescriptor = new EditProductDescriptorBuilder(standardDescriptor)
                .withDescription(VALID_PRODUCT_DESCRIPTION).build();
        assertFalse(standardDescriptor.equals(editedDescriptor));
    }
}
