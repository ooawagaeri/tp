package seedu.mycrm.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.mycrm.storage.JsonAdaptedProduct.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.mycrm.testutil.Assert.assertThrows;
import static seedu.mycrm.testutil.TypicalProducts.INTEL_CPU;

import org.junit.jupiter.api.Test;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.product.Description;
import seedu.mycrm.model.product.Manufacturer;
import seedu.mycrm.model.product.ProductName;
import seedu.mycrm.model.product.Type;

public class JsonAdaptedProductTest {
    private static final String INVALID_PRODUCT_NAME = "";

    private static final String VALID_PRODUCT_NAME = INTEL_CPU.getName().toString();
    private static final String VALID_DESCRIPTION = INTEL_CPU.getDescription().toString();
    private static final String VALID_TYPE = INTEL_CPU.getType().toString();
    private static final String VALID_MANUFACTURER = INTEL_CPU.getManufacturer().toString();

    @Test
    public void toModelType_validProductDetails_returnsProduct() throws Exception {
        JsonAdaptedProduct product = new JsonAdaptedProduct(INTEL_CPU);
        assertEquals(INTEL_CPU, product.toModelType());
    }

    @Test
    public void toModelType_invalidProductName_throwsAssertionError() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(INVALID_PRODUCT_NAME, VALID_TYPE, VALID_MANUFACTURER, VALID_DESCRIPTION);
        assertThrows(AssertionError.class, product::toModelType);
    }

    @Test
    public void toModelType_nullProductName_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(null, VALID_TYPE, VALID_MANUFACTURER, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ProductName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    };

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_PRODUCT_NAME, null, VALID_MANUFACTURER, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    };

    @Test
    public void toModelType_nullManufacturer_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_PRODUCT_NAME, VALID_TYPE, null, VALID_DESCRIPTION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Manufacturer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    };

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedProduct product =
                new JsonAdaptedProduct(VALID_PRODUCT_NAME, VALID_TYPE, VALID_MANUFACTURER, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    };
}
