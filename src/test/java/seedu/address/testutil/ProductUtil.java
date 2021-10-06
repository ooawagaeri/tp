package seedu.address.testutil;

import static seedu.address.logic.commands.products.AddProductCommand.COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_MANUFACTURER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_TYPE;

import seedu.address.model.products.Product;

public class ProductUtil {

    /**
     * Get Add product command string for the given product.
     */
    public static String getAddProductCommand(Product product) {
        return COMMAND_WORD + " " + PREFIX_PRODUCT_NAME + product.getName()
                + (product.hasType() ? " " + PREFIX_PRODUCT_TYPE + product.getType().toString() : "")
                + (product.hasManufacturer()
                    ? " " + PREFIX_PRODUCT_MANUFACTURER + product.getManufacturer().toString()
                    : "")
                + (product.hasDescription()
                    ? " " + PREFIX_PRODUCT_DESCRIPTION + product.getDescription().toString()
                    : "");
    }
}
