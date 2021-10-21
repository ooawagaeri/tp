package seedu.mycrm.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.mycrm.commons.exceptions.IllegalValueException;
import seedu.mycrm.model.products.Description;
import seedu.mycrm.model.products.Manufacturer;
import seedu.mycrm.model.products.Product;
import seedu.mycrm.model.products.ProductName;
import seedu.mycrm.model.products.Type;

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

        if (productName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ProductName.class.getSimpleName()));
        }
        final ProductName modelProductName = ProductName.getName(productName);
        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Type.class.getSimpleName()));
        }
        final Type modelType = Type.getType(type);
        if (manufacturer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Manufacturer.class.getSimpleName()));
        }
        final Manufacturer modelManufacturer = Manufacturer.getManufacturer(manufacturer);
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        final Description modelDescription = Description.getDescription(description);

        return new Product(modelProductName, modelType, modelManufacturer, modelDescription);
    }

}
