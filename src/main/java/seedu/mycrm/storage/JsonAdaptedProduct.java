package seedu.mycrm.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.product.Description;
import seedu.mycrm.model.product.Manufacturer;
import seedu.mycrm.model.product.Product;
import seedu.mycrm.model.product.ProductName;
import seedu.mycrm.model.product.Type;

/**
 * Jackson-friendly version of {@link Product}.
 */
class JsonAdaptedProduct {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Product's %s field is missing!";

    private final String productName;
    private final String type;
    private final String manufacturer;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedTemplate} with the given product details.
     */
    @JsonCreator
    public JsonAdaptedProduct(@JsonProperty("productName") String productName, @JsonProperty("type") String type,
                              @JsonProperty("manufacturer") String manufacturer,
                              @JsonProperty("description") String description) {
        this.productName = productName;
        this.type = type;
        this.manufacturer = manufacturer;
        this.description = description;
    }

    /**
     * Converts a given {@code Product} into this class for Jackson use.
     */
    public JsonAdaptedProduct(Product source) {
        productName = source.getName().toString();
        type = source.getType().toString();
        manufacturer = source.getManufacturer().toString();
        description = source.getDescription().toString();
    }

    /**
     * Converts this Jackson-friendly adapted product object into the model's {@code Product} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted product.
     */
    public Product toModelType() throws IllegalValueException {

        validateName();
        final ProductName modelProductName = ProductName.getName(productName);

        validateType();
        final Type modelType = Type.getType(type);

        validateManufacturer();
        final Manufacturer modelManufacturer = Manufacturer.getManufacturer(manufacturer);

        validateDescription();
        final Description modelDescription = Description.getDescription(description);

        return new Product(modelProductName, modelType, modelManufacturer, modelDescription);
    }

    /**
     * Checks product name of {@code Job} of {@code JsonAdaptedProduct}.
     *
     * @throws IllegalValueException if there are product name constraints violated in the adapted job.
     */
    private void validateName() throws IllegalValueException {
        if (productName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProductName.class.getSimpleName()));
        }
    }

    /**
     * Checks type of {@code Job} of {@code JsonAdaptedProduct}.
     *
     * @throws IllegalValueException if there are type constraints violated in the adapted job.
     */
    private void validateType() throws IllegalValueException {
        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Type.class.getSimpleName()));
        }
    }

    /**
     * Checks manufacturer of {@code Job} of {@code JsonAdaptedProduct}.
     *
     * @throws IllegalValueException if there are manufacturer constraints violated in the adapted job.
     */
    private void validateManufacturer() throws IllegalValueException {
        if (manufacturer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Manufacturer.class.getSimpleName()));
        }
    }

    /**
     * Checks description of {@code Job} of {@code JsonAdaptedProduct}.
     *
     * @throws IllegalValueException if there are description constraints violated in the adapted job.
     */
    private void validateDescription() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
    }

}
